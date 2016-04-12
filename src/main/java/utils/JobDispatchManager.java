package utils;

import com.jcraft.jsch.JSchException;

import java.io.File;
import java.io.IOException;

/**
 * Runs a finalized Job XML on NASP.
 * Jobs may be run locally or on remote environments.
 *
 * @author Jean-Paul Labadie
 */
public class JobDispatchManager {

    private static LogManager log = LogManager.getInstance();
    private static JobDispatchManager instance = new JobDispatchManager();
    private NetworkManager net_mgr = new NetworkManager();

    private JobDispatchManager(){

    }

    /**
     *
     * @return JobDispatchManager Singleton
     */
    public static JobDispatchManager getInstance(){
        return instance;
    }

    /**
     *
     * @param nasp_xml the prepared NASP XML
     * @param usrname the username for the remote server
     * @param password the password for the remote server with given username
     * @param url the url of the remote server as a String
     * @param port the port of the remote server
     * @param remote_path the directory in which to save the uploaded xml on the remote server
     */
    public void startNewRemoteJob(File nasp_xml, String usrname, String password, String url, int port, String remote_path){

        log.info("Remote Job Requested:");
        DispatchConfiguration dc = new DispatchConfiguration(usrname,url, LogManager.getTimestamp(),port,remote_path);
        log.logJob(dc);

        net_mgr.initSession(usrname,password,url,port);
        try {
            net_mgr.openSession();
        } catch (JSchException | IOException e) {
            log.error("Failed to open Session.");
            e.printStackTrace();
        }

        net_mgr.upload(nasp_xml,remote_path);

        net_mgr.runNaspJob(remote_path);
    }
}
