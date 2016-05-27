import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
public class DefaultSNPDistribution {

    private int POS = 0;
    private int REF = 1;
    private int TOT = 2;

    private File snp_matrix;
    private int snp_position_field_index = 0;
    private int reference_field_index = 0;
    private int snp_call_field_index = 0;
    private int sample_count = 0;

    private ArrayList<String> column_names = new ArrayList<>();
    private ArrayList<String[]> snapshot = new ArrayList<>();
    private BufferedReader br;

    public DefaultSNPDistribution(File snp_matrix_tsv) {
        try {
            init(snp_matrix_tsv);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DefaultSNPDistribution(String path_local_snp_matrix_tsv){
        File temp = new File(path_local_snp_matrix_tsv);
        try {
            init(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DefaultSNPDistribution(){}

    private void init(File snp_matrix_tsv) throws IOException {
        snp_matrix = snp_matrix_tsv;

        br = new BufferedReader(new FileReader(snp_matrix));

            String[] line_fields = br.readLine().split("\t");
            int count = 0;
            for (String x : line_fields) {
                if (x.equals("#SNPcall"))
                    snp_call_field_index =count;
                else if(x.equals("Reference"))
                    reference_field_index = count;
                else if (x.equals("Position"))
                    snp_position_field_index =count;
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

            for(int i=reference_field_index+2; i<snp_call_field_index;i++){
                temp[i] = line_fields[i];
            }
            snapshot.add(temp);
        }

        resetBufferedReader();
    }

    private void resetBufferedReader(){
        try {
            br = new BufferedReader(new FileReader(snp_matrix));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    public int getLastSNPIndex() throws IOException {

        String largest = snapshot.get(snapshot.size()-1)[POS];
        return new Integer(largest);
    }

    public String getSampleNames() throws IOException {

        String output="";

        for(int i = reference_field_index +1; i < snp_call_field_index; i++)
        {
            output += column_names.get(i)+',';
        }

        return output;
    }

    public void exportResultsToCSV(ArrayList<String> results, String path, boolean overwrite) throws IOException {
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


    public ArrayList<String> getAggregateSNPDistribution(int window_size, int step_size) throws IOException {
        ArrayList<String> output = new ArrayList<>();

        int range_min = 1;
        int range_max = 1+window_size;

        int snp_count;
        int snp_position;
        int window_total=0;
        int step_total=0;

        for ( String[] line : snapshot) {
            System.out.println(line[TOT]);
            snp_count = new Integer(line[TOT]);
            snp_position = new Integer(line[POS]);

            while(snp_position>range_max){
                output.add( range_min + "," + range_max + "," + window_total);
                range_min += step_size;
                range_max = range_min + window_size;
            }

            if(snp_position >= range_min && snp_position<range_max){
                output.add( range_min + "," + range_max + "," + window_total+step_total);
                range_min += step_size;
                range_max = range_min + window_size;
                window_total = step_total = 0;
            }

            if (snp_count > 1) {
                window_total++;
                if(snp_position >= range_min+step_size)
                    step_total++;
            }
        }
        return output;
    }

    public ArrayList<String> getIndividualSamplesSNPDistribution(int window_size, int step_size, int sample_field) throws IOException {

        if(sample_field<=0 || sample_field > sample_count)
            throw new IndexOutOfBoundsException("No such sample. Refer to a sample between 1 and "+sample_count);

        ArrayList<String> output = new ArrayList<>();
        String output_line = "";

        br.readLine(); //ignore the first line, which is essentially a header
        String[] line_fields;

        int range_min = 1;
        int range_max = window_size;
        int snp_position;
        char sample;
        char reference;
        int temp_total = 0;
        String line = "";

        while ( (line = br.readLine()) != null ) {
            line_fields = line.split("\t");

            snp_position = new Integer(line_fields[snp_position_field_index]);
            reference = line_fields[1].charAt(0);
            sample = line_fields[sample_field+1].charAt(0);

            if (snp_position > range_max) {
                output_line =range_min+","+range_max+","+ temp_total;
                output.add(output_line);
                range_min += step_size;
                range_max = range_min + window_size;
                temp_total = 0;
                while(snp_position > range_max){
                    output.add( range_min + "," + range_max + "," + temp_total);
                    range_min += step_size;
                    range_max = range_min + window_size;
                }
            }
            if (sample != reference) {
                temp_total++;
            }
        }

        resetBufferedReader();
        return output;
    }

    public ArrayList<String> getCompleteSNPDistribution(int window_size, int step_size) throws IOException{

        ArrayList<String> agg_dist = getAggregateSNPDistribution(window_size,step_size);
        ArrayList<ArrayList<String>> snp_dists = new ArrayList<>();
        ArrayList<String> snp_dist;
        for(int i = 1; i <= sample_count; i++){
            snp_dist = getIndividualSamplesSNPDistribution(window_size,step_size,i);
            snp_dists.add(snp_dist);
        }

        System.out.println("$#$"+sample_count);

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
