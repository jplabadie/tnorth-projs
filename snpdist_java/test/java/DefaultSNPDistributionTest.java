import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Project tnorth-projs.
 * Created by jlabadie on 5/25/16.
 *
 * @author jlabadie
 */
public class DefaultSNPDistributionTest {

    private DefaultSNPDistribution snpd;
    private String sample_names_reference = "";
    private ArrayList<String> agg_sliding_test_reference = new ArrayList<>();
    private ArrayList<String> agg_nonsliding_test_reference = new ArrayList<>();
    private ArrayList<String> indv_sampl1_nonsliding_test_reference = new ArrayList<>();
    private ArrayList<String> indv_sampl1_sliding_test_reference = new ArrayList<>();
    private ArrayList<String> indv_sampl35_nonsliding_test_reference = new ArrayList<>();
    private ArrayList<String> indv_sampl35_sliding_test_reference = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        File snp_matrix = new File(getClass().getResource("bestsnp.tsv").getPath());
        snpd = new DefaultSNPDistribution(snp_matrix);

        //load reference sample names data from file
        BufferedReader br = new BufferedReader(new FileReader(
                new File(getClass().getResource("sample_names.csv").getPath())));

        //initialize reference sample names data from saved csv
        while(br.ready()){
            sample_names_reference=br.readLine();
        }

        //load reference aggregate results with sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("agg_win1000_step500_results.csv").getPath()));

        //initialize reference aggregate results with sliding window data from saved csv
        while(br.ready()){
            agg_sliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                new File(getClass().getResource("agg_win1000_step1000_results.csv").getPath())));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            agg_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                new File(getClass().getResource("indv_samp1_win1000_step1000_results.csv").getPath())));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl1_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                new File(getClass().getResource("indv_samp1_win1000_step500_results.csv").getPath())));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl1_sliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                new File(getClass().getResource("indv_samp35_win1000_step1000_results.csv").getPath())));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl35_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                new File(getClass().getResource("indv_samp35_win1000_step500_results.csv").getPath())));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl35_sliding_test_reference.add(br.readLine());
        }
    }

    @Test
    public void testGetLastSNPIndex() throws Exception {
        Assert.assertEquals(snpd.getLastSNPIndex(),1750724);
    }

    @Test
    public void testWriteResultsToCSV() throws Exception {
        //ArrayList<String> temp =snpd.getCompleteSNPDistribution(1000,1000);
        //snpd.exportResultsToCSV(temp, "results",true);
    }

    @Test
    public void testGetSampleNames() throws Exception {
        Assert.assertEquals(snpd.getSampleNames(),sample_names_reference);
    }


    @Test
    public void testAggregateSNPDistributionWithoutSlide() throws Exception {
        ArrayList<String> temp = snpd.getAggregateSNPDistribution(1000,1000);

        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),agg_nonsliding_test_reference.get(i));
        }
    }

    @Test
    public void testAggregateSNPDistributionWithSlide() throws Exception {
        ArrayList<String> temp = snpd.getAggregateSNPDistribution(1000,500);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),agg_sliding_test_reference.get(i));
        }
    }


    @Test
    public void testIndividualSamplesNum1SNPDistributionNoSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSamplesSNPDistribution(1000,1000,1, false);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl1_nonsliding_test_reference.get(i));
        }
    }

    @Test
    public void testIndividualSamplesNum1SNPDistributionSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSamplesSNPDistribution(1000,500,1, false);

        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl1_sliding_test_reference.get(i));
        }
    }

    @Test
    public void testIndividualSamplesNum35SNPDistributionNoSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSamplesSNPDistribution(1000,1000,1, false);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl35_nonsliding_test_reference.get(i));
        }
    }

    @Test
    public void testIndividualSamplesNum35SNPDistributionSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSamplesSNPDistribution(1000,500,1, false);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl35_sliding_test_reference.get(i));
        }
    }

    @Test
    public void getMultiSampleSNPDistributionNoSlide() throws Exception {

        ArrayList<String> samples = new ArrayList<>();
        samples.add("06-299002_S31_L001"); // #9
        samples.add("03-2525_S26_L001"); // #2
        samples.add("06-2950_S32_L001"); // #8

        ArrayList<String> output = snpd.getMultiSampleSNPDistribution(1000,1000,samples,false);
    }

    @Test
    public void getMultiSampleSNPDistributionSlide() throws Exception {

        ArrayList<String> samples = new ArrayList<>();
        samples.add("03-2454_S25_L001"); // #1
        samples.add("03-2525_S26_L001"); // #2
        samples.add("06-2950_S32_L001"); // #8

        ArrayList<String> output = snpd.getMultiSampleSNPDistribution(1000,500,samples,false);
    }

    @Test
    public void getMultiSampleSNPDistributionNoSlideNumerical() throws Exception {

        ArrayList<String> samples = new ArrayList<>();
        samples.add("1");
        samples.add("5");
        samples.add("35");
        ArrayList<String> output = snpd.getMultiSampleSNPDistribution(1000,1000,samples,true);
    }

    @Test
    public void getMultiSampleSNPDistributionSlideNumerical() throws Exception {

        ArrayList<String> samples = new ArrayList<>();
        samples.add("1");
        samples.add("33");
        samples.add("35");
        ArrayList<String> output = snpd.getMultiSampleSNPDistribution(1000,500,samples,true);
    }

    @Test
    public void getMultiSampleSNPDistributionSlideNumericalRange() throws Exception {

        ArrayList<String> samples = new ArrayList<>();
        samples.add("1:5");
        samples.add("32:35");
        ArrayList<String> output = snpd.getMultiSampleSNPDistribution(1000,500,samples,true);
    }

}