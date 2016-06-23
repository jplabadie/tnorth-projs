package snpdist;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 6/17/16.
 *
 * @Author jlabadie
 */

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
public class DefaultSNPDistributionSimpleTest {

    private static DefaultSNPDistribution snpd;

    @Before
    public void setUp() throws Exception {

        File input = new File(getClass().getResource("/bestsnp.tsv").getPath());
        snpd = new DefaultSNPDistribution(input);

    }

    @Test
    public void testComplete() throws Exception {

        ArrayList<String> output = snpd.getCompleteSNPDistribution(1000, 500);
        snpd.exportResultsToCSV(output, "simple_results_complete", true);

    }

    @Test
    public void testAllIndv() throws Exception {

        ArrayList<ArrayList<String>> output = snpd.getAllSampleSNPDistributionParallel( 1000,500 );


        snpd.exportResultsToCSV(output.get(0), "simple_results_indv", true);

    }



    @Test
    public void testAggregate() throws Exception {

        ArrayList<String> output = snpd.getAggregateSNPDistribution(1000,500,true);
        snpd.exportResultsToCSV(output, "simple_results_agg", true);

    }
}