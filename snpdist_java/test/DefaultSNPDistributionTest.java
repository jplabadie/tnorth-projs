import org.junit.Assert;
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
public class DefaultSNPDistributionTest {
    private DefaultSNPDistribution snpd;

    @Before
    public void setUp() throws Exception {
        File snp_matrix = new File(getClass().getResource("bestsnp.tsv").getPath());
        snpd = new DefaultSNPDistribution(snp_matrix);
    }

    @Test
    public void getLastSNPIndex() throws Exception {
        Assert.assertEquals(snpd.getLastSNPIndex(),1750724);
    }

    @Test
    public void writeResultsToCSV() throws Exception {
        //ArrayList<String> temp =snpd.getCompleteSNPDistribution(1000,1000);
        //snpd.exportResultsToCSV(temp, "results",true);
    }

    @Test
    public void getSamples() throws Exception {
        System.out.println(snpd.getSampleNames());
    }


    @Test
    public void getAggregateSNPDistribution() throws Exception {
        ArrayList<String> temp = snpd.getAggregateSNPDistribution(1000,1000);
        System.out.println("---Aggregate Distribution Data----");
        for(String x : temp){
            System.out.println(x);
        }
    }

    @Test
    public void getIndividualSamplesSNPDistribution() throws Exception {
       // ArrayList<String> temp = snpd.getIndividualSamplesSNPDistribution(1000,1000,26);
       // System.out.println("---Individual Distribution Data----");

       // for (String aTemp : temp) {
       //     System.out.println(aTemp);
      //  }
    }

    @Test
    public void getCompleteSNPDistribution() throws Exception{
       // ArrayList<String> temp = snpd.getCompleteSNPDistribution(1000,1000);
       // System.out.println("---Complete Distribution Data----");
       // for (String aTemp : temp) {
       //     System.out.println(aTemp);
       // }

    }


}