import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.util.DateConverter;

import java.io.File;
import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        OptionParser parser = new OptionParser() {
            {
                accepts( "c" ).withRequiredArg().ofType( Integer.class )
                        .describedAs( "complete" ).defaultsTo( 1 );
                accepts( "i" ).withOptionalArg().ofType( Double.class )
                        .describedAs( "individual" );
                accepts( "a", "agg" ).withRequiredArg().required()
                        .withValuesConvertedBy( datePattern( "MM/dd/yy" ) );
                accepts( "output-file" ).withOptionalArg().ofType( File.class )
                        .describedAs( "file" );
                acceptsAll( asList( "h", "?" ), "help" ).forHelp();
                acceptsAll( asList( "cp", "classpath" ) ).withRequiredArg()
                        .describedAs( "path1" + pathSeparatorChar + "path2:..." )
                        .ofType( File.class )
                        .withValuesSeparatedBy( pathSeparatorChar );
            }
        };
        parser.posixlyCorrect(true);
        parser.printHelpOn(System.out);

        OptionSet opts = parser.parse(args);

        opts.
    }
}
