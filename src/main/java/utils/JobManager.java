package utils;

import java.io.File;

/**
 * Runs a finalized Job XML on NASP.
 * Jobs may be run locally or on remote environments.
 *
 * @author Jean-Paul Labadie
 */
public class JobManager {

    private static LogManager log = LogManager.getInstance();
    private static JobSaveLoadManager jslm = JobSaveLoadManager.getInstance();

    private JobManager(){

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
        JobRecord dc = new JobRecord(usrname,url, port,remote_path,remote_path );
        log.logJob(dc);

//        net_mgr.initSession(usrname,password,url,port);
//        net_mgr.openSession();
//
//        net_mgr.upload(nasp_xml,remote_path);
//
//        net_mgr.runNaspJob(remote_path);
    }
}
