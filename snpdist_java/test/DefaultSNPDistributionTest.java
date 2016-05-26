import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

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
    public void getSNPDistribution() throws Exception {
        System.out.println(snpd.getSNPDistribution(1000,1000));
    }

}