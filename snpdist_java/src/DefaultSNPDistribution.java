import java.io.*;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @Author jlabadie
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


    public String getSNPDistribution(int window_size, int step_size) throws IOException {
        String outputs = "";


        int last_snp_index = getLastSNPIndex();

        br.readLine(); //ignore the first line, which is essentially a header
        String[] line_fields;
        int temp_total = 0;
        int temp_pos;
        int temp_calls = 0;

        for (int i = 0; i < last_snp_index - window_size; i += window_size) {
            for (String line; (line = br.readLine()) != null; ) {
                line_fields = line.split("\t");
                temp_calls = new Integer(line_fields[snp_call_field]);
                temp_pos = new Integer(line_fields[snp_position_field]);
                System.out.print("!"+temp_calls);


                    if (temp_calls > 1)
                        temp_total+=temp_calls;


            }
            outputs += i + " : " + (i+window_size) +" #"+temp_total+"\n";
            temp_total=0;

        }
        resetBufferedReader();
        return outputs;
    }
}
