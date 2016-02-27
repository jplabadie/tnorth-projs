package prototypes;

import xmlsources.JobParameters;
import xmlsources.ObjectFactory;

import java.io.File;

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

        temp.jaxbXMLToObject(new File(getClass().getClassLoader().getResource("NaspInputExample_Aspen.xml").getFile()));

        ObjectFactory of = new ObjectFactory();
        JobParameters jp = of.createJobParameters();
        jp.setMemRequested("10");
        jp.setName("Yo");
        jp.setNumCPUs("4");
        jp.setWalltime("100");

        temp.jaxbObjectToXML(jp,"outputjob");



    }
}