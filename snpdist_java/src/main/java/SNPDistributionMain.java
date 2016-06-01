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
public class SNPDistributionMain {

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
                    "\nUse the -help option for syntax guidance.");
        }

        OptionParser parser = new OptionParser() {
            {
                accepts( "c" ).withRequiredArg().ofType( String.class )
                        .describedAs( "complete - gives an aggregate distribution and distributions for each sample.\n" +
                                "Must be followed by a path representing where to write the output as a CSV." );

                accepts( "i" ).withRequiredArg().ofType( String.class )
                        .describedAs( "individual - gives distributions for a single sample.\n" +
                                "Must be followed by a path representing where to write the output as a CSV." );

                accepts( "im" ).withRequiredArg().ofType( String.class )
                        .describedAs( "individual multiple - gives the distribution for multiple specific samples.\n" +
                                "Must be followed by a list of samples, separated by commas, or as a range using a colon\n" +
                                "Must then be followed by a path representing where to write the output as a CSV." );

                accepts( "n" , "by number - alerts the program to expect samples to referenced by\n" +
                        "their position in the list of samples, rather than their name." ).availableIf( "im" );

                accepts( "a" ).withRequiredArg().ofType( String.class )
                        .describedAs( "aggregate - gives the distribution across all samples summed\n" +
                                "Must be followed by a path representing where to write the output as a CSV." );

                accepts( "w" ).withRequiredArg().ofType( Integer.class )
                    .describedAs( "window size - specify the window size for surveying SNP distribution.\n" +
                            "Must be followed by a sane non-zero value. Defaults to 1000." );

                accepts( "s" ).withRequiredArg().ofType( Integer.class )
                        .describedAs( "step size - specify the step size for the survey window.\n" +
                                "If step size = window size, the window does not have a sliding behavior.\n" +
                                "Must be followed by a sane non-zero value. Defaults to 1000." );

                accepts( "ow" ).withRequiredArg().ofType(String.class)
                        .describedAs("over-write - attempts to overwrite a file at the given output path.\n" +
                                "Must be followed by the string 'true' as a confirmation." );

                acceptsAll( asList( "h", "?", "help", "info" ), "help" ).forHelp();

                acceptsAll( asList( "cp", "classpath" ) ).withRequiredArg()
                        .describedAs( "path1" + pathSeparatorChar + "path2:..." )
                        .ofType( File.class )
                        .withValuesSeparatedBy( pathSeparatorChar );
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
