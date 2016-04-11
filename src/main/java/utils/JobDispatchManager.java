package utils;

import java.io.File;

/**
 * Runs a finalized Job XML on NASP.
 * Jobs may be run locally or on remote environments.
 *
 * @author Jean-Paul Labadie
 */
public class JobDispatchManager {

    private static LogManager log;

    public JobDispatchManager(LogManager lm){
        log = lm;
    }

    public void startNewRemoteJob(File nasp_xml, String usrname, String password, String url, int port){

        log.info("Remote Job Requested:");
    }
}
