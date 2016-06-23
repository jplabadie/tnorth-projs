package snpdist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.*;

/**
 * Gathers SNP Density information from NASP output 'best-snps.tsv'
 *
 * TODO: There are some bad-code smells. Chiefly, some magic-numbers usage.
 *
 * Project: SNP Density Script
 * Git-Hub repository: tnorth-projs.
 * Created by: jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
class DefaultSNPDistribution {

    private int POS = 0; // index of snp position in snapshot (arbitrary)
    private int REF = 1; // index of reference nucleotide in snapshot (arbitrary)
    private int TOT = 2; // index of snp total in snapshot (arbitrary)
    private int CONTIG = 3; // index of Contig name position in contig snapshot

    private int snp_position_field_index = 0; // index of snp position in tab-delimited input File
    private int reference_field_index = 1; // index of reference nucleotide in input File
    private int snp_call_field_index = 0; // index of snp total in input File
    private int contig_name_field_index = 0; // index of contig name position in input file

    private int sample_count = 0; // a count of the unique samples found in the input (begins at 1)

    private ArrayList<String> column_names = new ArrayList<>();
    private ArrayList<String> sample_names = new ArrayList<>();

    private ArrayList<ArrayList<String[]>> contigs = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> contigs_index = new ArrayList<>();

    /**
     *
     * @param snp_matrix_tsv expects a tab-delimited best-SNPs output from NASP
     */
    DefaultSNPDistribution(File snp_matrix_tsv) {
        try {
            init(snp_matrix_tsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <h>Initializer Method</h>
     * Private initializer method. Accepts a NASP Best-SNPs formatted, tab-delimited input file.
     * <br/>
     * @param snp_matrix_tsv the input best-snps matrix, a tab delimited text file from NASP output
     * @throws IOException when there is an error opening the input file
     */
    private void init( File snp_matrix_tsv ) throws IOException {

        BufferedReader br = new BufferedReader( new FileReader( snp_matrix_tsv ) );

            String[] line_fields = br.readLine().split( "\t" );
            int count = 0;
            for ( String field : line_fields ) {
                switch ( field ) {
                    case "#SNPcall":
                        snp_call_field_index = count;
                        break;
                    case "Reference":
                        reference_field_index = count;
                        break;
                    case "Position":
                        snp_position_field_index = count;
                        break;
                    case "Contig":
                        contig_name_field_index = count;
                        break;
                }
                column_names.add( field );
                count++;
            }
        sample_count = snp_call_field_index - reference_field_index - 1;

        String line;
        String contig = "";

        ArrayList<String[]> snapshot = new ArrayList<>();
        ArrayList<Integer> index = new ArrayList<>();

        while(( line = br.readLine() ) != null ){

            String[] temp = new String[sample_count + 4]; //TODO: replace magic number
            line_fields = line.split( "\t" );
            temp[POS] = line_fields[snp_position_field_index];
            temp[REF] = line_fields[reference_field_index];
            temp[TOT] = line_fields[snp_call_field_index];
            temp[CONTIG] = line_fields[contig_name_field_index];

            System.arraycopy( line_fields, reference_field_index + 1, temp,
                    CONTIG + 1, snp_call_field_index - ( reference_field_index + 1 ));

            if( contig.isEmpty() ){
                contig = temp[CONTIG];
                index.add( new Integer( temp[POS] ) );
            }
            if ( contig.equals( temp[CONTIG] ) ){
                snapshot.add( temp );
                index.add( new Integer(temp[POS] ) );
            }
            else{
                contig = temp[CONTIG];

                ArrayList<String[]> copy_contig = new ArrayList<>();
                copy_contig.addAll( snapshot );
                contigs.add( copy_contig );
                snapshot.clear();
                snapshot.add( temp );

                ArrayList<Integer> copy_index = new ArrayList<>();
                copy_index.addAll(index);
                contigs_index.add( copy_index );
                index.clear();
                index.add(new Integer(temp[POS]));
            }
        }

        if( !snapshot.isEmpty() ){ // put the last lines and index into our data structures
            contigs.add(snapshot);
            contigs_index.add(index);
        }

        Collections.addAll(sample_names, getSampleNames().split(","));
    }

    /**
     * Returns a comma-delimited string of sample names
     *
     * @return a list of sample names, separated in original order by commas
     */
    String getSampleNames() {

        String output="";

        for(int i = reference_field_index +1; i < snp_call_field_index; i++)
        {
            if(i>reference_field_index+1)
                output += ',';
            output += column_names.get(i);
        }
        return output;
    }

    /**
     * Writes the given ArrayList of Strings (formatted by commas) to a CSV.
     *
     * @param results the results ArrayList containing comma-delimited strings representing lines of output
     * @param path the desired path for this output CSV
     * @param overwrite if true, identical files will potentially be overwritten, if false, collisions will throw an exception
     * @throws IOException if a file writing error occurs due to path errors, permissions, or collisions
     */
    void exportResultsToCSV(ArrayList<String> results, String path, boolean overwrite) throws IOException{

        if(!path.contains(".csv"))
            path+=".csv";
        Path csvfile = Paths.get(path);

        writeTextToFile(results, csvfile, overwrite);
    }

    /**
     * Writes the given ArrayList of Strings (formatted by commas) to a TSV.
     *
     * Note: This method is more expensive than the CSV call, as commas are laboriously replaced with tabs on each line
     *
     * @param results the results ArrayList containing comma-delimited strings representing lines of output
     * @param path the desired path for this output CSV
     * @param overwrite if true, identical files will potentially be overwritten, if false, collisions will throw an exception
     * @throws IOException if a file writing error occurs due to path errors, permissions, or collisions
     */
    void exportResultsToTSV(ArrayList<String> results, String path, boolean overwrite) throws IOException{

        if(!path.contains(".tsv"))
            path+=".tsv";
        Path tsvfile = Paths.get(path);

        for(int line_index=0; line_index <results.size(); line_index++){
            String line = results.get(line_index);
            line = line.replaceAll(",","\t");
            results.set(line_index,line);
        }

        writeTextToFile(results,tsvfile,overwrite);
    }

    /**
     * Private Helper to write an ArrayList of Strings to disk
     *
     * @param text an ArrayList of type String representing lines of text
     * @param path a Path object representing the desired path to write the file to
     * @param overwrite if true, truncate and overwrite any previous file at the given path
     * @throws IOException if there was an error writing to disk due to space, bad path, permissions, etc
     */
    private void writeTextToFile( ArrayList<String> text, Path path, boolean overwrite) throws IOException{

        if(overwrite) {
            Files.write(path, text, Charset.forName("UTF-8"));
        }
        else
            Files.write(path, text, Charset.forName("UTF-8"), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    /**
     *
     * @param window_size the window size (the range of sample positions to check)
     * @param step_size the amount of positions to move the window each iteration
     * @return an ArrayList representing lines of comma-delimited output
     */
    ArrayList<String> getAggregateSNPDistribution(int window_size, int step_size, boolean include_header){

        ArrayList<String> output = new ArrayList<>();
        for( ArrayList<String[]> contig : contigs){
            output.addAll( getAggregateSNPDistribution( window_size,step_size,contig ));
        }
        if( include_header ) {
            String header = "fromPos,toPos,Contig,SumSNPDistribution";
            output.add(0, header);
        }
        return output;
    }


    /**
     * <h>Gives SNP Distribution data for the aggregate sum of samples</h>
     * Creates an ArrayList of Strings. Each Element represents a line of output.
     * <br/>
     * The first line is a header which represents each column of data below it.
     * Each subsequent line presents, in order: start_position, end_position, and total SNPS from all samples which had more than 1 SNP
     * in the window.
     * <br/>
     * This output is typically passed to the exportToCSV method for output parsing.
     * <br/>
     *
     * @param window_size the window size for sampling
     * @param step_size the step size for moving the window, if >= window_size, the window is non-sliding
     * @return an ArrayList representing lines of comma-delimited output
     */
    private ArrayList<String> getAggregateSNPDistribution(int window_size, int step_size, ArrayList<String[]> contig){

        ArrayList<String> output = new ArrayList<>();
        String name = contig.get(0)[CONTIG];

        int cur_index = 0;
        int step_index = 0;
        boolean step_set ;

        int final_index = contig.size();
        int final_pos = new Integer( contig.get((contig.size() - 1 ))[POS] );

        int win_start_pos = 0;
        int win_end_pos ;
        int step_pos = 0 ;
        int cur_pos = 0 ;

        int total = 0;

        while( cur_pos + step_size <= final_pos){

            win_start_pos = step_pos;
            win_end_pos = win_start_pos + window_size;
            step_pos = win_start_pos + step_size;
            total = 0;
            step_set = false;
            cur_pos = new Integer( contig.get( step_index )[POS] );

            for (cur_index = step_index; cur_pos < win_end_pos && cur_index <= final_index; cur_index++) {
                cur_pos = new Integer(contig.get(cur_index)[POS]);
                int agg = new Integer(contig.get(cur_index)[TOT]);

                if (agg > 1 && cur_pos > win_start_pos)
                    total++;
                if (cur_pos >= step_pos && !step_set) {
                    step_index = cur_index;
                    step_set = true;
                }
                try {
                    cur_pos = new Integer(contig.get(cur_index + 1)[POS]);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

            String out = name + "," + win_start_pos + "," + win_end_pos + "," + total;
            output.add( out );
        }

        total = 0;
        for(cur_index = cur_index; cur_index < final_index; cur_index++){
            int agg = new Integer(contig.get(cur_index)[TOT]);
            if ( agg > 1)
                total++;
        }
        String out = name + "," + step_pos + "," + final_pos + "," + total;
        output.add(out);

        return output;

    }

    /**
     * <h>Gives SNP Distribution data for a given list of samples</h>
     * Creates an ArrayList of Strings. Each Element represents a line of output.
     * <br/>
     * The first line is a header which represents each column of data below it.
     * Each subsequent line presents, in order: start_position, end_position,
     * and an absolute count of SNPS for each sample found in the sample_list given.
     * <br/>
     * This output is typically passed to the exportToCSV method for output parsing.
     * <br/>
     *
     * @param window_size the window size to use
     * @param step_size the stepping size to use, if step_size < window_size, the window will 'slide'
     * @param samples an ArrayList of Strings representing the samples we are interested in
     * @return return a Generic ArrayList as output, with each element representing distribution data for a single sample.
     *
     */
    ArrayList<String> getMultiSampleSNPDistribution(int window_size, int step_size, ArrayList<String> samples,
                                                    boolean numerical){

        ArrayList<Integer> samps = new ArrayList<>();
        ArrayList<String> output = new ArrayList<>();
        String header = "fromPos,toPos";

        if(numerical){
            for(String samp : samples){
                if (samp.contains(":")) {
                    int temp1 = new Integer(samp.substring(0,samp.indexOf(":")));
                    int temp2 = new Integer(samp.substring(samp.indexOf(":")+1,samp.length()));

                    for(int i = temp1; i <= temp2; i++){
                        samps.add(i);
                        header += ","+sample_names.get(i-1);
                    }
                }
                else{
                    samps.add(new Integer(samp));
                }
            }
        }
        else{
            for(String samp : samples){
                int temp = sample_names.indexOf(samp)+1;
                samps.add(temp);
                header += ","+temp+":"+samp;
            }
        }

        try{
            for(int indv : samps){
                if(indv == samps.get(0)){
                    output = getIndividualSampleSNPDistribution(window_size,step_size,samps.get(0),false,false);
                }
                else{
                    ArrayList<String> indv_output =
                            getIndividualSampleSNPDistribution(window_size,step_size,indv,true,false);

                    for(int index = 0; index < output.size(); index++){
                        String line = output.get(index); // get the output that already exists
                        line += ","+indv_output.get(index); // append each sample's snp total
                        output.set(index,line);
                    }
                }
            }

            output.add( 0, header );
            return output;
        }
        catch (Exception e){
            System.out.println("There was an error in finding the distribution for multiple samples.");
            return null;
        }
    }

    /**
     *
     * @param sample_name get the 'position' of a sample name in the array of names (taken in-order)
     * @return an int representing the position of the sample name
     */
    private int getSamplePosition( String sample_name){

        return sample_names.indexOf(sample_name);
    }

    /**
     *
     * @param window_size the window size (the range of sample positions to check)
     * @param step_size the amount of positions to move the window each iteration
     * @return an ArrayList representing lines of comma-delimited output
     * @throws IOException
     */
    public ArrayList<String> getIndividualSampleSNPDistribution( int window_size, int step_size, String sample ) throws IOException {

        int sample_index = getSamplePosition(sample);

        if(sample_index >= 0){
            return getIndividualSampleSNPDistribution(window_size, step_size, sample_index, true, false);
        } else{
            throw new NoSuchElementException(" Failed to run > There is no sample named: " + sample);
        }
    }

    /**
     *
     * @param window_size the window size (the range of sample positions to check)
     * @param step_size the amount of positions to move the window each iteration
     * @return an ArrayList representing lines of comma-delimited output
     * @throws IOException
     */
    public ArrayList<String> getIndividualSampleSNPDistribution(int window_size, int step_size, int sample,
                                                                boolean include_meta, boolean include_header) throws IOException {

        ArrayList<String> output = new ArrayList<>();
        for(ArrayList<String[]> contig : contigs) {
            output.addAll(getIndividualSampleSNPDistribution(window_size, step_size, contig, sample));
        }
        if(include_header) {
            String header = "fromPos,toPos,Contig,SumSNPDistribution";
            output.add(0, header);
        }
        if(include_meta){
            for(String line : output){
                // TODO: handle meta-data true state
            }
        }
        return output;
    }

    /**
     * <h>Gives SNP Distribution data for a single sample</h>
     * Creates an ArrayList of Strings. Each Element represents a line of output.
     * <br/>
     * The first line is a header which represents each column of data below it.
     * Each subsequent line presents, in order: start_position, end_position,
     * and an absolute count of SNPS found inside the given window for the given sample.
     * <br/>
     * This output is typically passed to the exportToCSV method for output parsing.
     * <br/>
     *
     * @param window_size the window size to use
     * @param step_size the stepping size to use, if step_size < window_size, the window will 'slide'
     * @param sample_field refers to a sample by its position in the list of samples (1:sample_count)
     * @return return a Generic ArrayList as output, with each element representing distribution data for a single sample.
     * @throws IOException
     */
    ArrayList<String> getIndividualSampleSNPDistribution(int window_size, int step_size, ArrayList<String[]> contig,
                                                         int sample_field) throws IOException {

        ArrayList<String> output = new ArrayList<>();
        String name = contig.get(0)[CONTIG];

        int cur_index = 0;
        int step_index = 0;
        boolean step_set ;

        int final_index = contig.size();
        int final_pos = new Integer( contig.get((contig.size() - 1 ))[POS] );

        int win_start_pos = 0;
        int win_end_pos ;
        int step_pos = 0 ;
        int cur_pos = 0 ;

        int total;

        while( cur_pos + step_size <= final_pos){

            win_start_pos = step_pos;
            win_end_pos = win_start_pos + window_size;
            step_pos = win_start_pos + step_size;
            total = 0;
            step_set = false;
            cur_pos = new Integer( contig.get( step_index )[POS] );

            for (cur_index = step_index; cur_pos < win_end_pos && cur_index <= final_index; cur_index++) {

                String[] line = contig.get(cur_index);
                cur_pos = new Integer(line[POS]);
                String cur_samp = line[ CONTIG + sample_field];
                String cur_ref = line[REF];
                if ( !cur_ref.equals(cur_samp) && cur_pos > win_start_pos )
                    total++;
                if (cur_pos >= step_pos && !step_set) {
                    step_index = cur_index;
                    step_set = true;
                }
                try {
                    cur_pos = new Integer(contig.get(cur_index + 1)[POS]);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }

            String out = name +","+win_start_pos + "," + win_end_pos + "," + total;
            output.add( out );
        }
        total = 0;
        for(cur_index = cur_index; cur_index < final_index; cur_index++){
            String[] line = contig.get(cur_index);
            cur_pos = new Integer(line[POS]);
            String cur_samp = line[ CONTIG + sample_field];
            String cur_ref = line[REF];
            if ( !cur_ref.equals(cur_samp) && cur_pos > win_start_pos )
                total++;
        }
        String out = name+","+step_pos + "," + final_pos + "," + total;
        output.add(out);

        return output;
    }

    /**
     *
     * @param window_size the window size to use for grouping positions
     * @param step_size the step size to use. When step size is less than window size, the window will slide
     * @param sample the number of the sample to run SNP distribution analysis on
     * @return a Callable method with return type matching (as ArrayList of Strings)
     */
    Callable<ArrayList<String>> getCallableIndividualSamples(int window_size, int step_size, int sample){

        return () -> getIndividualSampleSNPDistribution( window_size, step_size, sample, false, false );
    }

    /**
     *
     * @param window_size the window size to use
     * @param step_size the step size to use. When step size is less than window size, the window will slide
     * @return an ArrayList of ArrayLists of strings, where the inner ArrayList
     * represents the distribution return for a single sample
     * @throws InterruptedException
     */
    ArrayList<ArrayList<String>> getAllSampleSNPDistributionParallel ( int window_size, int step_size ) throws InterruptedException {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<ArrayList<String>>> callables = new ArrayList<>();
        for( int i = 1; i <= sample_count; i++) {
            callables.add( getCallableIndividualSamples(window_size, step_size, i) );
        }

        ArrayList<ArrayList<String>> output = new ArrayList<>();
        executor.invokeAll( callables )
                .stream()
                .map(future -> {
                    try {
                        return future.get();
                    }
                    catch ( Exception e ) {
                        throw new IllegalStateException(e);
                    }
                })
                .forEachOrdered( output::add );

        return output;
    }

    /**
     * <h>Gives a complete output of all SNP Distribution data</h>
     * Creates an ArrayList of Strings. Each Element represents a line of output.
     * <br/>
     * The first line is a header which represents each column of data below it.
     * Each subsequent line presents, in order: start_position, end_position, total SNPS from all samples which had more than 1 SNP
     * in the window, and an absolute count of SNPS for each sample (in order).
     * <br/>
     * This output is typically passed to the exportToCSV method for output parsing.
     * <br/>
     *
     * @param window_size the window size to use
     * @param step_size the stepping size to use, if step_size < window_size, the window will 'slide'
     * @return return a Generic ArrayList as output, with each element representing distribution data for a single sample.
     * @throws IOException
     */
    ArrayList<String> getCompleteSNPDistribution( int window_size, int step_size ) throws IOException{

        String header = "fromPos,toPos,AggregateDist,";
        header += getSampleNames();

        ArrayList<String> agg_dist = getAggregateSNPDistribution( window_size, step_size, false );
        ArrayList<ArrayList<String>> snp_lists = new ArrayList<>();

        System.out.println( agg_dist.size() );

        try {
            snp_lists = getAllSampleSNPDistributionParallel( window_size , step_size );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }

        int index = 0;

        for( ArrayList<String> list : snp_lists ){
           for( String line : list ){
               if(index>=agg_dist.size()) break;
               String temp1 = agg_dist.get( index );
               String temp2 = line.substring( line.lastIndexOf( ',' ), line.length() );
               temp1 += temp2;
               agg_dist.set( index++,temp1 );
           }
            index = 0;
        }
        agg_dist.add( 0, header );
        return agg_dist;
    }
}

