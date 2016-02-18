package prototypes;

/**
 * @author Jean-Paul Labadie
 */
public class OutputParserTest {

    private static OutputParser temp;

    @org.junit.BeforeClass
    public static void onlyOnce(){
        temp = new OutputParser();
    }

    @org.junit.Test
    public void testAddElement() throws Exception {


    }

    @org.junit.Test
    public void testCreateOutputXML() throws Exception {
        temp.createOutputXML();
    }
}