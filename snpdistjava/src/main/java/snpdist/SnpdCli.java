package snpdist;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import joptsimple.util.DateConverter;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static java.io.File.pathSeparatorChar;
import static java.util.Arrays.*;

import static joptsimple.util.DateConverter.*;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 6/1/16.
 *
 * @author jlabadie
 */
public class SnpdCli {

    private static File input;
    private static File output;

    public static void main(String[] args) throws IOException {

        File output;
        File input;
        ArrayList<String> chosen_samples;
        int window_size;
        int step_size;
        boolean overwrite=false;

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
                        "MUST be followed by a path representing where to write the output as a CSV." )
                        .withRequiredArg().ofType( String.class )
                        .describedAs( "output/path/name" );

                acceptsAll( asList( "im", "individualmultiple" ), "Will output the distributions for multiple specific " +
                        "samples. MUST be followed by a list of samples, separated by commas, or as a range using a " +
                        "colon. The final argument MUST be a path representing where to write the output as a CSV." )
                        .withRequiredArg().ofType( String.class )
                        .describedAs( "samplename1, samplename2, ... , output/path/name" );

                acceptsAll( asList( "n", "numerical" ), "Will alert the program to expect samples to referenced by" +
                        "their position in the list of samples, rather than their name. ONLY works when using the" +
                        "'im' or 'individualmultiple' option.")
                        .availableIf( "im" );

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

                acceptsAll( asList( "h", "?", "help", "info" ), "Displays these help details." ).forHelp();
            }
        };
        parser.posixlyCorrect(true);
        parser.printHelpOn(System.out);

        OptionSet opts = parser.parse(args);

        if(opts.has( "c" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        if(opts.has( "i" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        if(opts.has( "im" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }if(opts.has( "a" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }

        if(opts.has( "w" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        else{

        }
        if(opts.has( "s" )){
            try{
                output = new File((String)opts.valueOf( "c" ));
            }
            catch (Exception e){
                System.out.println( "Non-valid output file name, location, or permissions." );
            }
        }
        else{

        }

        if(opts.has("ow")){

        }

    }
}
