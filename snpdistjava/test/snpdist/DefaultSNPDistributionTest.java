package snpdist;

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
        File snp_matrix = new File(getClass().getResource("/bestsnp.tsv").getFile());
        snpd = new DefaultSNPDistribution(snp_matrix);

        //load reference sample names data from file
        BufferedReader br = new BufferedReader(new FileReader(
                getClass().getResource("/sample_names.csv").getPath()));

        //initialize reference sample names data from saved csv
        while(br.ready()){
            sample_names_reference=br.readLine();
        }

        //load reference aggregate results with sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/agg_win1000_step500_results.csv").getPath()));

        //initialize reference aggregate results with sliding window data from saved csv
        while(br.ready()){
            agg_sliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/agg_win1000_step1000_results.csv").getPath()));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            agg_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/indv_samp1_win1000_step1000_results.csv").getPath()));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl1_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/indv_samp1_win1000_step500_results.csv").getPath()));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl1_sliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/indv_samp35_win1000_step1000_results.csv").getPath()));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl35_nonsliding_test_reference.add(br.readLine());
        }

        //load reference aggregate results sans sliding window from file
        br = new BufferedReader(new FileReader(
                getClass().getResource("/indv_samp35_win1000_step500_results.csv").getPath()));

        //initialize reference aggregate results sans sliding window data from saved csv
        while(br.ready()){
            indv_sampl35_sliding_test_reference.add(br.readLine());
        }
    }

    @Test
    public void testGetLastSNPIndex() throws Exception {
        Assert.assertEquals(snpd.getLastSNPIndex(),1750724);
    }

//    @Test
//    public void testWriteResultsToCSV() throws Exception {
//        ArrayList<String> csvlines = new ArrayList<>();
//        csvlines.add("a,b,c,d,e,f,g,h,i,j,k");
//        csvlines.add("1,2,3,4,5,6,7,8,9,10");
//        snpd.exportResultsToCSV(csvlines,"/testres/test_csv",false);
//        File csvpath = new File(getClass().getResource("/testres/test_csv.csv").getPath());
//
//        Assert.assertEquals(true,csvpath.exists());
//
//        ArrayList<String> csvlines_load = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new FileReader(
//                getClass().getResource("/testres/test_csv.csv").getPath()));
//        while(br.ready()){
//            String line = br.readLine();
//            csvlines_load.add(line);
//        }
//        for(int i = 0; i < csvlines.size(); i++) {
//            Assert.assertEquals(csvlines.get(i), csvlines_load.get(i));
//        }
//
//    }
//
//    @Test
//    public void testWriteResultsToTSV() throws Exception {
//        ArrayList<String> tsvlines = new ArrayList<>();
//        tsvlines.add("a\tb\tc\td\te\tf\tg\th\ti\tj\tk");
//        tsvlines.add("1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
//        snpd.exportResultsToCSV(tsvlines,"/testres/test_tsv",false);
//        File tsvpath = new File(getClass().getResource("/testres/test_tsv.tsv").getPath());
//
//        Assert.assertEquals(true,tsvpath.exists());
//
//        ArrayList<String> tsvlines_load = new ArrayList<>();
//        BufferedReader br = new BufferedReader(new FileReader(
//                getClass().getResource("test_tsv.tsv").getPath()));
//        while(br.ready()){
//            String line = br.readLine();
//            tsvlines_load.add(line);
//        }
//        for(int i = 0; i < tsvlines.size(); i++) {
//            Assert.assertEquals(tsvlines.get(i), tsvlines_load.get(i));
//        }
//
//    }

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

//    @Test
//    public void testAggregateSNPDistributionWithSlide() throws Exception {
//        ArrayList<String> temp = snpd.getAggregateSNPDistribution(1000,500);
//        for(int i = 0; i < temp.size(); i++){
//            Assert.assertEquals(temp.get(i),agg_sliding_test_reference.get(i));
//        }
//    }


    @Test
    public void testIndividualSamplesNum1SNPDistributionNoSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSampleSNPDistribution(1000,1000,1,false,true);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl1_nonsliding_test_reference.get(i));
        }
    }

//    @Test
//    public void testIndividualSamplesNum1SNPDistributionSlide() throws Exception {
//        ArrayList<String> temp = snpd.getIndividualSampleSNPDistribution(1000,500,1, false,true);
//
//        for(int i = 0; i < temp.size(); i++){
//            Assert.assertEquals(temp.get(i),indv_sampl1_sliding_test_reference.get(i));
//        }
//    }

    @Test
    public void testIndividualSamplesNum35SNPDistributionNoSlide() throws Exception {
        ArrayList<String> temp = snpd.getIndividualSampleSNPDistribution(1000,1000,1, false,true);
        for(int i = 0; i < temp.size(); i++){
            Assert.assertEquals(temp.get(i),indv_sampl35_nonsliding_test_reference.get(i));
        }
    }

//    @Test
//    public void testIndividualSamplesNum35SNPDistributionSlide() throws Exception {
//        ArrayList<String> temp = snpd.getIndividualSampleSNPDistribution(1000,500,1, false, true);
//        for(int i = 0; i < temp.size(); i++){
//            Assert.assertEquals(temp.get(i),indv_sampl35_sliding_test_reference.get(i));
//        }
//    }

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