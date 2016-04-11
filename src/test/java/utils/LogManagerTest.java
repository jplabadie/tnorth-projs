package utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import xmlbinds.NaspInputData;
import xmlbinds.ObjectFactory;

/**
 * Unit tests for the LogManager utility class
 *
 * @author Jean-Paul Labadie
 */
public class LogManagerTest {
    private static LogManager lm;

    @Before
    public void setUp() throws Exception {
        lm = new LogManager();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testIsEnabled() throws Exception {

    }

    @Test
    public void testLog() throws Exception {

    }

    @Test
    public void testInfo() throws Exception {
        lm.info("Testing Info");
    }

    @Test
    public void testWarn() throws Exception {

    }

    @Test
    public void testError() throws Exception {

    }

    @Test
    public void testLogJob() throws Exception {
        ObjectFactory of = new ObjectFactory();
        NaspInputData nid = of.createNaspInputDataType();
        DispatchConfiguration dc = new DispatchConfiguration("Bob","TestJob","aspen.tgen.org"
                ,22,nid, "/home/Bob");
        lm.logJob(dc);

    }
}