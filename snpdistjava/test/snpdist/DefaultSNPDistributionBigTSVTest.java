package snpdist;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
public class DefaultSNPDistributionBigTSVTest {

    private static DefaultSNPDistribution snpd;

    @Before
    public void setUp() throws Exception {

        File input = new File(getClass().getResource("/bestsnp_big.tsv").getPath());
        snpd = new DefaultSNPDistribution(input);

    }

    @Test
    public void testComplete() throws Exception {

        ArrayList<String> output = snpd.getCompleteSNPDistribution(5000, 2500);
        snpd.exportResultsToCSV(output, "big_results", true);

    }
}