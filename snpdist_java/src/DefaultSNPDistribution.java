import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
public class DefaultSNPDistribution {

    private File snp_matrix;
    private int snp_position_field = 0;
    private int snp_call_field = 0;
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
                    snp_call_field=count;
                else if (x.equals("Position"))
                    snp_position_field=count;
                count++;
            }
        System.out.println(snp_position_field + ":"+snp_call_field);
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

        String largest = "";
        String[] line_fields;
        br.readLine(); //ignore the first line, which is essentially a header

        for(String line; (line = br.readLine()) != null; ) {
            if(!br.ready()){
                System.out.println(line);
                line_fields = line.split("\t");
                largest = line_fields[snp_position_field];
            }
        }
        resetBufferedReader();
        return new Integer(largest);
    }

    private String getSNPSFromLine(String[] line_fields){
        String output = "";
        for(int i = 1; i <= snp_call_field; i++)
        {
            output+= line_fields[i];
        }
        System.out.println(output);
        return output;
    }


    public ArrayList<String> getSNPDistribution(int window_size, int step_size) throws IOException {
        ArrayList<String> output = new ArrayList<String>();
        int last_snp_index = getLastSNPIndex();

        br.readLine(); //ignore the first line, which is essentially a header
        String[] line_fields;

        int range_min = 1;
        int range_max = window_size;
        int snp_position;
        int snp_count = 0;
        int temp_total = 0;
        String line = "";

        while ( (line = br.readLine()) != null ) {
            line_fields = line.split("\t");
            snp_count = new Integer(line_fields[snp_call_field]);
            snp_position = new Integer(line_fields[snp_position_field]);

            if (snp_count > 1) {
                if(snp_position > range_max){
                    output.add( range_min + "," + range_max +","+temp_total);
                    System.out.println(output.get(output.size()-1));
                    range_min += step_size;
                    range_max = range_min+window_size;
                    temp_total = 1;
                }
                else {
                    temp_total++;
                }
            }
        }

        resetBufferedReader();
        return output;
    }
}
