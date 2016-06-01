import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
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

    private int snp_position_field_index = 0; // index of snp position in tab-delimited input File
    private int reference_field_index = 0; // index of reference nucleotide in input File
    private int snp_call_field_index = 0; // index of snp total in input File
    private int sample_count = 0; // a count of the unique samples found in the input (begins at 1)

    private ArrayList<String> column_names = new ArrayList<>();
    private ArrayList<String> sample_names = new ArrayList<>();
    private ArrayList<String[]> snapshot = new ArrayList<>();
    private int[] snapshot_index;

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
    private void init(File snp_matrix_tsv) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(snp_matrix_tsv));

            String[] line_fields = br.readLine().split("\t");
            int count = 0;
            for (String x : line_fields) {
                switch (x) {
                    case "#SNPcall":
                        snp_call_field_index = count;
                        break;
                    case "Reference":
                        reference_field_index = count;
                        break;
                    case "Position":
                        snp_position_field_index = count;
                        break;
                }
                column_names.add(x);
                count++;
            }
        sample_count = snp_call_field_index - reference_field_index-1;

        String line;

        while((line = br.readLine()) != null){
            String[] temp = new String[sample_count+3];
            line_fields = line.split("\t");
            temp[POS] = line_fields[snp_position_field_index];
            temp[REF] = line_fields[reference_field_index];
            temp[TOT] = line_fields[snp_call_field_index];

            System.arraycopy(line_fields, reference_field_index + 1, temp,
                    TOT+1, snp_call_field_index - (reference_field_index + 1));
            snapshot.add(temp);
        }

        snapshot_index = new int[snapshot.size()];

        int i = 0;
        for(String[] x : snapshot){
            snapshot_index[i++] = new Integer(x[POS]);
        }

        Collections.addAll(sample_names, getSampleNames().split(","));
    }

    /**
     *
     * @return int representing the position (in the contig) of the last SNP
     */
    int getLastSNPIndex()  {

        String largest = snapshot.get(snapshot.size()-1)[POS];
        return new Integer(largest);
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
    void exportResultsToCSV(ArrayList<String> results, String path, boolean overwrite) throws IOException {
        List<String> lines = new ArrayList<>();
        String header = "fromPos,toPos,AggregateDist,";
        header += getSampleNames();
        lines.add(header);
        lines.addAll(results);

        if(!path.contains(".csv"))
            path+=".csv";
        Path file = Paths.get(path);

        if(Files.exists(file)){
            if(overwrite) {
                Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.TRUNCATE_EXISTING);
            }
            else
                throw new IOException("File already exists, and overwrite tag set to false! (try a different name)");
        }
        else
            Files.write(file, lines, Charset.forName("UTF-8"));
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
    ArrayList<String> getAggregateSNPDistribution(int window_size, int step_size){
        ArrayList<String> output = new ArrayList<>();

        int true_max = getLastSNPIndex();
        boolean no_slide = false;
        if(window_size==step_size) no_slide = true;

        int start_pos=0;
        int ss_index;
        int ss_step_index=0;
        boolean step_index_set;
        int end_pos;

        int range_total = 0;

        while(start_pos+window_size < true_max){
            end_pos = start_pos+window_size;
            ss_index = ss_step_index;
            step_index_set = false;
            for(;snapshot_index[ss_index] <= end_pos; ss_index++){
                int tot = new Integer(snapshot.get(ss_index)[TOT]);
                if(tot > 1)
                    range_total++ ;
                if(no_slide)
                    ss_step_index++;
                else if(snapshot_index[ss_index]>=start_pos+step_size && !step_index_set) {
                    ss_step_index = ss_index;
                    step_index_set = true;
                }
            }
            String out = start_pos + ","+end_pos+","+range_total;
            output.add(out);
            range_total=0;

            start_pos += step_size;
        }
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
    ArrayList<String> getMultiSampleSNPDistribution(int window_size, int step_size, ArrayList<String> samples, boolean numerical){

        int[] samps = new int[samples.size()];
        int samps_index = 0;
        ArrayList<String> output;

        if(numerical){
            for(String samp : samples){
                if (samp.contains(":")) {
                    int temp1 = new Integer(samp.substring(0,samp.indexOf(":")));
                    int temp2 = new Integer(samp.substring(samp.indexOf(":"),samp.length()));

                    for(int i = temp1; i <= temp2; i++){
                        samps[samps_index++] = i;
                    }
                }
                else{
                    samps[samps_index++] = new Integer(samp);
                }
            }
        }
        else{
            for(String samp : samples){
                samps[samps_index++]= sample_names.indexOf(samp);
            }
        }

        try{
            output = getIndividualSamplesSNPDistribution(window_size,step_size,samps[0],false);
            for(int indv : samps){
                if(indv == samps[0])
                    continue; //we already have the first sample loaded, skip it
                else{
                    ArrayList<String> indv_output =
                            getIndividualSamplesSNPDistribution(window_size,step_size,indv, true);

                    int indv_index = 0;
                    for(String line : output){
                        line += ","+indv_output.get(indv_index++); //append each indvidual
                    }

                }
            }
        }
        catch (Exception e){

        }
        return new ArrayList<>(); //TODO:This method is incomplete, should return multiple sample distributions

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
     * @param no_meta_data if true, output will only consist of SNP count, without headers etc
     * @return return a Generic ArrayList as output, with each element representing distribution data for a single sample.
     * @throws IOException
     */
    ArrayList<String> getIndividualSamplesSNPDistribution(int window_size, int step_size, int sample_field, boolean no_meta_data) throws IOException {

        if(sample_field<=0 || sample_field > sample_count)
            throw new IndexOutOfBoundsException("No such sample. Refer to a sample between 1 and "+sample_count);

        ArrayList<String> output = new ArrayList<>();

        int true_max = getLastSNPIndex();
        boolean no_slide = false;
        if(window_size==step_size) no_slide = true;

        int start_pos=0;
        int ss_index;
        int ss_step_index=0;
        boolean step_index_set;
        int end_pos;

        String out; // used to create formatted output per each line

        int range_total = 0;
        while(start_pos+window_size < true_max){
            end_pos = start_pos+window_size;
            ss_index = ss_step_index;
            step_index_set = false;
            for(;snapshot_index[ss_index] <= end_pos; ss_index++){
                String sample = snapshot.get(ss_index)[sample_field+TOT];
                String reference = snapshot.get(ss_index)[REF];
                if(!sample.equals(reference))
                    range_total++ ;
                if(no_slide)
                    ss_step_index++;
                else if(snapshot_index[ss_index]>=start_pos+step_size && !step_index_set) {
                    ss_step_index = ss_index;
                    step_index_set = true;
                }
            }

            if(no_meta_data) // don't include window position information per line
                out = "" + range_total;
            else
                out = start_pos + ","+end_pos+","+range_total;

            output.add(out);
            range_total=0;

            start_pos += step_size;
        }
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
    ArrayList<String> getCompleteSNPDistribution(int window_size, int step_size) throws IOException{

        ArrayList<String> agg_dist = getAggregateSNPDistribution(window_size,step_size);
        ArrayList<ArrayList<String>> snp_dists = new ArrayList<>();
        ArrayList<String> snp_dist;
        for(int i = 1; i <= sample_count; i++){
            snp_dist = getIndividualSamplesSNPDistribution(window_size,step_size,i, false);
            snp_dists.add(snp_dist);
        }

        int index = 0;
        for(ArrayList<String> snp_list : snp_dists){
            for (String out_dist : agg_dist) {

                String temp = snp_list.get(index);
                temp = temp.substring(temp.lastIndexOf(','),temp.length());
                out_dist+=temp;
                agg_dist.set(index,out_dist);

                index++;
            }
            index=0;
        }

        return agg_dist;
    }
}

