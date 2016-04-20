package utils;

import com.jcraft.jsch.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * @author Jean-Paul Labadie
 * @date 8/11/2015
 */
public class NetworkManagerTest {

    private static NetworkManager nm;

    @Before
    public void setup() {
        nm = new NetworkManager();
        String usr;
        String pwd;
        System.out.println("Enter your username:");
        usr = "jlabadie";
        System.out.println("Enter your password:");
        pwd = "C00kiemnstr!";

        nm.initSession(usr, pwd, "aspen.tgen.org", 22);
        try {
            nm.openSession();
        }
        catch (Exception e){
            Assert.fail();
        }

    }

    @After
    public void teardown(){
        nm.closeSession();
    }


    @Test
    public void testGetSession() throws Exception {
        Session sess = nm.getSession();

        if(sess == null)
            Assert.fail();

    }

    @Test
    public void testUpload() throws Exception {
        File file = new File(getClass().getClassLoader().getResource("test/NASPInputExample_Aspen.xml").toString());
        nm.upload(file,"/home/jlabadie/test.xml");
    }

    @Test
    public void testDownload() throws Exception {
        //nm.download("/home/jlabadie/NASPInputExample.xml","testdownload.xml");
    }

    @Test
    public void testRunNaspJob() throws Exception {

    }

    private String getInput(){
        String input = null;
        try{
            // open input stream test.txt for reading purpose.
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while (!br.ready()) {
                input = br.readLine();
                System.out.println(input);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return input;
    }
}