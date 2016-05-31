import java.io.File;
import java.util.ArrayList;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/31/16.
 *
 * @author jlabadie
 */
public class RunMain {
    public static void main(String[] args){

        char mode;
        String ref_path;
        String out_path;
        int window_size;
        int step_size;

        try {
            mode = args[0].charAt(0);
            ref_path = args[1];
            out_path = args[2];
            window_size = new Integer(args[3]);
            step_size = new Integer(args[4]);

            File input = new File(ref_path);
            DefaultSNPDistribution dsd = new DefaultSNPDistribution(input);

            if(mode == 'c'){
                dsd.exportResultsToCSV(dsd.getCompleteSNPDistribution(window_size,step_size),
                out_path,
                false);
            }
            else if(mode ==)

        }catch (Exception e){

        }
    }
}
