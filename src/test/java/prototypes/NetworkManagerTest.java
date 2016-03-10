package prototypes;

import com.jcraft.jsch.Session;
import org.junit.Test;

/**
 * @author Jean-Paul Labadie
 * @date 8/11/2015
 */
public class NetworkManagerTest {

    @Test
    public void testConnect() {

        NetworkManager nm = new NetworkManager();
        Session sess = nm.getSession();

        if (sess != null) {
            System.out.println("Session is not null!");
            System.out.println(sess.getHost());
            if (sess.isConnected()) {
                System.out.println("Connected!");
            }
        }
    }
}