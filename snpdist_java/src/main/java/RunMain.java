import joptsimple.OptionParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/31/16.
 *
 * @author jlabadie
 */
public class RunMain {
    public static void main(String[] args) {

        /**
         * modes:
         * c = complete, aggregate dist + all indv samples dist
         * i = individual, each listed sample dist
         * a = aggregate, only the aggregate dist
         * d = default, aggregate only, window and step = 1000
         */
        char mode = '\0';
        String ref_path = "";
        String out_path = "";
        int window_size = 0;
        int step_size = 0;
        ArrayList<String> indv_samples = new ArrayList<>();

        String previous = "";
        for (String x : args) {

            if (mode == '\0' && x.equalsIgnoreCase("-C")) {
                mode = 'c';
                continue;
            } else if (mode == '\0' && x.equalsIgnoreCase("-A")) {
                mode = 'a';
                continue;
            } else if (mode == '\0' && x.equalsIgnoreCase("-I")) {
                mode = 'i';
                continue;
            }

            if (ref_path.equals("") && x.contains(".") || previous.equalsIgnoreCase("-R")) {
                ref_path = x;
                continue;
            } else if (out_path.equals("") && x.contains(".") || previous.equalsIgnoreCase("-O")) {
                out_path = x;
                continue;
            }

            if (window_size == 0 || previous.equalsIgnoreCase("-W")) {
                try {
                    window_size = new Integer(x);
                    continue;
                } catch (Exception e) {
                    System.out.println("Bad window size input");
                }
            } else if (step_size == 0 || previous.equalsIgnoreCase("-S")) {
                try {
                    step_size = new Integer(x);
                    continue;
                } catch (Exception e) {
                    System.out.println("Bad step size input");
                }
            }

            if(mode == 'i' ){
                indv_samples.add(x);
            }

            previous = x;
        }

        if(mode == '\0')// if mode unset, default to 'd'
            mode = 'd';
        if(ref_path.equals("") || out_path.equals("")){
            System.out.println("A reference and output path must be provided.");
            System.exit(1);
        }

        try {
            File input = new File(ref_path);
            DefaultSNPDistribution dsd = new DefaultSNPDistribution(input);
            ArrayList<String> output;

            if(mode =='d' && window_size>0 && step_size>0) {
                output = dsd.getAggregateSNPDistribution(window_size, step_size);
                dsd.exportResultsToCSV(output,out_path,false);
            }
            else if(mode == 'c'){
                output = dsd.getCompleteSNPDistribution(window_size,step_size);
            }
            else if (mode=='i'){
                //TODO: this main method will likely be dumped, hiding output errors here
                //output = dsd.getMultiSampleSNPDistribution(window_size,step_size,indv_samples);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}