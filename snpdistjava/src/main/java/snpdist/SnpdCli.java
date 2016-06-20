package snpdist;

import joptsimple.OptionParser;
import joptsimple.OptionSet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 6/1/16.
 *
 * @author jlabadie
 */
public class SnpdCli {

    private static DefaultSNPDistribution snpd;
    private static ArrayList<String> results;

    public static void main(String[] args) throws IOException {

        File input = null;
        ArrayList<String> chosen_samples = new ArrayList<>();
        int window_size = 1000;
        int step_size = 1000;
        boolean overwrite = false;
        String output;

        boolean numerical = false;

        try{
            String input_nasp_bestsnps_tsv_path = args[0];
            input = new File(input_nasp_bestsnps_tsv_path);
        }
        catch (Exception e){
            System.out.println("Failed: Expected a path to a best-snps.tsv in the first argument field. " +
                    "Use the -help option for syntax guidance.");
        }

        OptionParser parser = new OptionParser() {
            {
                acceptsAll( asList( "c", "complete" ), "Will output aggregate distributions AND distributions for " +
                        "each sample. MUST be followed by a path representing where to write the output as a CSV." +
                        "This is the DEFAULT behavior." )
                        .withRequiredArg().ofType( String.class )
                        .describedAs( "output/path/name" );

                acceptsAll( asList( "i", "individual" ), " Will output distributions for a single sample. " +
                        "MUST be a single sample name or sample number (if using -n) followed by a space."+
                        "The space MUST be followed by a path representing where to write the output as a CSV." )
                        .withRequiredArg().ofType( String.class )
                        .describedAs( "sample output/path/name" );

                acceptsAll( asList( "n", "numerical" ), "Will alert the program to expect samples to referenced by" +
                        "their position in the list of samples, rather than their name. ONLY works when using the" +
                        "'i', 'individual' option.")
                        .availableIf( "i" );

                acceptsAll( asList( "a", "aggregate" ), "Will output the aggregate distribution across all samples." +
                        "MUST be followed by a path representing where to write the output as a CSV.")
                        .withRequiredArg().ofType( String.class )
                        .describedAs( "output/path/name" );

                acceptsAll( asList("w", "window"), "Allows the window size to be specified for surveying SNP " +
                        "distribution. REQUIRED if 's' or 'step' option is included in arguments." +
                        "MUST be followed by a sane non-zero value. Defaults to 1000." )
                        .withRequiredArg().ofType( Integer.class )
                        .describedAs( "0...n" );

                acceptsAll( asList("s", "step" ), "Allows the step size to be specified for the survey window." +
                        "If step size = window size, the window will not have a sliding behavior." +
                        "REQUIRED if 'w' or 'window' option is included in arguments." +
                        "MUST be followed by a sane non-zero value. Defaults to 1000." )
                        .requiredIf("w", "window")
                        .withRequiredArg().ofType( Integer.class )
                        .describedAs( "0...n" );

                acceptsAll( asList("ow", "overwrite" ), "Will attempt to overwrite a file at the given output path." +
                        "MUST be followed by the string 'true' as a confirmation.")
                        .withRequiredArg().ofType(Boolean.class)
                        .describedAs("true/false");

                acceptsAll( asList( "h", "?", "help", "info" ), "Displays these help details." ).forHelp();

            }
        };

        String[] arguments = new String[args.length-1];
        System.arraycopy(args, 1, arguments, 0, args.length - 1);

        OptionSet opts = parser.parse(arguments);

        if (opts.has("h") || opts.has("help") || opts.has("?")){
            parser.printHelpOn(System.out);
            System.exit(0);
        }

        if(input != null) {
            try {
                snpd = new DefaultSNPDistribution(input);
            } catch (Exception fnf) {
                System.out.println("Failed to load input file.");
                parser.printHelpOn(System.out);
                System.exit(1);
            }
        }

        if(opts.has("n") || opts.has("numerical")){
            numerical = true;
        }

        if(opts.has("ow") || opts.has("overwrite")){
            if(opts.valueOf( "ow" ).equals("true"))
                overwrite = true;
        }

        if(opts.has( "w" )){
            try{
                window_size = (int) opts.valueOf( "w" );
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }

        if(opts.has( "s" )){
            try{
                step_size = (int) opts.valueOf( "s" );
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }

        if(opts.has( "c" ) || opts.has( "complete" )){
            try{
                output = (String) opts.valueOf( "c" );

                results = snpd.getCompleteSNPDistribution(window_size,step_size);
                snpd.exportResultsToCSV(results, output, overwrite);
                System.out.println("Success!");
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        else if(opts.has( "i" ) || opts.has( "individual" )){
            try {

                List<?> temp = opts.valuesOf("i");


                for (Object aTemp : temp) {
                    System.out.println(aTemp);
                }
                output = (String) temp.get(1);

                if(numerical){
                    results = snpd.getIndividualSampleSNPDistribution(window_size,step_size,(Integer) temp.get(0));
                    snpd.exportResultsToCSV(results, output,overwrite);
                }
                else {

                    results = snpd.getIndividualSampleSNPDistribution(window_size,step_size,(String) temp.get(0));
                    snpd.exportResultsToCSV(results, output,overwrite);
                }
                System.out.println("Success!");
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        else if(opts.has( "a" ) || opts.has( "aggregate" )){
            try{
                output = (String)opts.valueOf( "a" );
                results = snpd.getAggregateSNPDistribution(window_size,step_size);
                snpd.exportResultsToCSV(results, output, overwrite);
                System.out.println("Success!");
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }

    }
}
