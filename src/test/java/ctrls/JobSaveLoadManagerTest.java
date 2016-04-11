package ctrls;

import utils.JobSaveLoadManager;
import xmlbinds.NaspInputData;

import java.io.File;

/**
 * @author Jean-Paul Labadie
 */
public class JobSaveLoadManagerTest {

    private static JobSaveLoadManager tempparser;

    @org.junit.BeforeClass
    public static void onlyOnce(){
        tempparser = new JobSaveLoadManager();
    }

    @org.junit.Test
    public void testAddElement() throws Exception {


    }

    @org.junit.Test
    public void testCreateOutputXML() throws Exception {
        NaspInputData naspInputData = null;
        try {

            File nip = new File(getClass().getClassLoader().getResource("test/NaspInputExample_Aspen.xml").getPath());

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