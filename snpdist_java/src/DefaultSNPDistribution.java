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

    private File snp_matrix; // tab-delimited NASP input File, usage is largely deprecated

    private int snp_position_field_index = 0; // index of snp position in tab-delimited input File
    private int reference_field_index = 0; // index of reference nucleotide in input File
    private int snp_call_field_index = 0; // index of snp total in input File
    private int sample_count = 0; // a count of the unique samples found in the input (begins at 1)

    private ArrayList<String> column_names = new ArrayList<>();
    private ArrayList<String> sample_names = new ArrayList<>();
    private ArrayList<String[]> snapshot = new ArrayList<>();
    private int[] snapshot_index;
    private BufferedReader br;

    DefaultSNPDistribution(File snp_matrix_tsv) {
        try {
            init(snp_matrix_tsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param snp_matrix_tsv
     * @throws IOException
     */
    private void init(File snp_matrix_tsv) throws IOException {
        snp_matrix = snp_matrix_tsv;

        br = new BufferedReader(new FileReader(snp_matrix));

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

        resetBufferedReader();
    }

    /**
     * Largely deprecated by current implementation and the init() function
     */
    private void resetBufferedReader(){
        try {
            br = new BufferedReader(new FileReader(snp_matrix));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return int representing the position (in the contig) of the last SNP
     * @throws IOException
     */
    int getLastSNPIndex() throws IOException {

        String largest = snapshot.get(snapshot.size()-1)[POS];
        return new Integer(largest);
    }

    /**
     *
     * @return
     * @throws IOException
     */
    String getSampleNames() throws IOException {

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
     *
     * @param results
     * @param path
     * @param overwrite
     * @throws IOException
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
     *
     * @param window_size
     * @param step_size
     * @return
     * @throws IOException
     */
    ArrayList<String> getAggregateSNPDistribution(int window_size, int step_size) throws IOException {
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
     *
     * @param window_size
     * @param step_size
     * @param samples
     * @return
     */
    ArrayList<String> getIndividualSamplesSNPDistribution(int window_size,int step_size, ArrayList<String> samples){

        int[] samps = new int[samples.size()];

        try{
            for(int i=0; i< samples.size(); i++){
                samps[i] = new Integer(samples.get(i));
            }


        }
        catch (Exception e){

            for(String x: samples){
                for(int i=0; i <sample_names.size(); i++){

                }
            }
        }
        return new ArrayList<>(); //TODO:This method is incomplete, should return multiple sample distributions

    }

    /**
     *
     * @param window_size
     * @param step_size
     * @param sample_field
     * @return
     * @throws IOException
     */
    ArrayList<String> getIndividualSamplesSNPDistribution(int window_size, int step_size, int sample_field) throws IOException {

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
            String out = start_pos + ","+end_pos+","+range_total;
            output.add(out);
            range_total=0;

            start_pos += step_size;
        }
        return output;
    }

    /**
     *
     * @param window_size
     * @param step_size
     * @return
     * @throws IOException
     */
    ArrayList<String> getCompleteSNPDistribution(int window_size, int step_size) throws IOException{

        ArrayList<String> agg_dist = getAggregateSNPDistribution(window_size,step_size);
        ArrayList<ArrayList<String>> snp_dists = new ArrayList<>();
        ArrayList<String> snp_dist;
        for(int i = 1; i <= sample_count; i++){
            snp_dist = getIndividualSamplesSNPDistribution(window_size,step_size,i);
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

