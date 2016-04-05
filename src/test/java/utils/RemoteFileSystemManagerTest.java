package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.DirectoryStream;
import java.nio.file.Path;

/**
 *
 * Unit and integration tests for the RemoteFileSystemManager class
 *
 * @author Jean-Paul Labadie
 */
public class RemoteFileSystemManagerTest {

    private static RemoteFileSystemManager rfsm;

    @BeforeClass
    public static void init() throws Exception{
        rfsm = new RemoteFileSystemManager();
        rfsm.init("usrname", "password", "aspen.tgen.org", 22);
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInit() throws Exception {
    }

    @Test
    public void testGetDirectory() throws Exception {

        DirectoryStream<Path> paths = rfsm.getDirectory("/scratch");

        for(Path x : paths){
            System.out.println(x.toString());
        }
    }
}