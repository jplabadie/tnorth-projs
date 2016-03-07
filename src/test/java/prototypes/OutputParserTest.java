package prototypes;

import naspxml.NaspInputData;

import java.io.File;

/**
 * @author Jean-Paul Labadie
 */
public class OutputParserTest {

    private static OutputParser tempparser;

    @org.junit.BeforeClass
    public static void onlyOnce(){
        tempparser = new OutputParser();
    }

    @org.junit.Test
    public void testAddElement() throws Exception {


    }

    @org.junit.Test
    public void testCreateOutputXML() throws Exception {
        NaspInputData naspInputData = null;
        try {

            File nip = new File(getClass().getClassLoader().getResource("NaspInputExample_Aspen.xml").getPath());

            naspInputData = tempparser.jaxbXMLToObject(nip);

        }
        catch (NullPointerException e){
            e.printStackTrace();
        }
        if(naspInputData != null) {
            System.out.println(naspInputData.getExternalApplications().getAligner().get(0).getName());
        }

    }
}