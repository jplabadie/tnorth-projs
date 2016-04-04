package utils;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Vector;

/**
 * @author Jean-Paul Labadie
 */
public class NetworkManager {

    private LogManager log;
    private Session session;
    private JSch jsch;
    private ChannelSftp sftp_channel;
    private ChannelExec exec_channel;
    private java.io.InputStream in;
    private java.io.PrintStream out;

    /**
     *
     */
    public NetworkManager() {
        LogManager log = new LogManager();
        JSch.setLogger(log);
        jsch = new JSch();
    }

    /**
     *
     * @param username the username for the remote server
     * @param password the password used for the remote server with given username
     * @param url the url of the remote server
     * @param port the port of the remote server (typically 22 for ssh)
     */
    public void initSession(String username, String password, String url, int port) {
        try {
            session = jsch.getSession(username, url, port);
            session.setUserInfo(new UserInfo() {
                public String getPassphrase() {
                    return password;
                }

                public String getPassword() {
                    return password;
                }

                public boolean promptPassword(String s) {
                    return true;
                }

                public boolean promptPassphrase(String s) {
                    return true;
                }

                public boolean promptYesNo(String s) {
                    return true;
                }

                public void showMessage(String s) {

                }
            });

            //jsch.addIdentity("C:\\Users\\Jean-Paul\\.ssh\\id_rsa");

        } catch (JSchException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Start the session, and open relevant channels.
     * Should be called after initSession()
     */
    public void openSession() throws JSchException {

        session.connect();

        sftp_channel = (ChannelSftp)session.openChannel("sftp");
        exec_channel = (ChannelExec)session.openChannel("exec");


        if (sftp_channel != null) {
            sftp_channel.connect();
        }
        if (exec_channel != null) {
            exec_channel.connect();
        }

        in = System.in;
        out = System.out;
    }

    /**
     * Close all channels and exit the session
     */
    public void closeSession(){

        if(session.isConnected()) {

            sftp_channel.disconnect();
            exec_channel.disconnect();

            sftp_channel = null;
            exec_channel = null;

            try {
                in.close();
                in = null;
            } catch (IOException e) {
                e.printStackTrace();
            }

            out.close();
            out = null;

            session.disconnect();
        }
    }

    /**
     *
     * @return the current session object
     */
    public Session getSession(){
        return session;
    }

    /**
     *
     * @param file the File to be uploaded
     * @param abs_remote_path the absolute path to the file on the remote machine
     */
    public void upload(File file, String abs_remote_path){

        if (sftp_channel == null){
            log.error("Error: SFTP Channel is null. Cannot upload.");
            return;
        }
        else if(file == null){
            log.error("Error: File for SFTP Upload is null. Cannot upload.");
            return;
        }
        try {
            testDirExists(abs_remote_path);
        }
        catch (IOException e){
            log.error("Error: Connection failed or remote path is not a valid path for SFTP Upload. Cannot upload.");
            return;
        }

        try{
            if(abs_remote_path != "") {
                sftp_channel.cd("/"); //start from root
                sftp_channel.cd(abs_remote_path); //cd to the absolute directory
                log.info("cd " + abs_remote_path);
                System.out.println("cd " + abs_remote_path + ": Failed. Insufficient Permissions?");
            }
        }
        catch (SftpException e){
            //the directory cannot be visited
            log.error("cd " + abs_remote_path +": Failed. Insufficient Permissions?");
            System.out.println("cd " + abs_remote_path +": Failed. Insufficient Permissions?");
            log.error(e.getMessage());
        }

        try {

            try {
                sftp_channel.put(new FileInputStream(file), file.getName());
            } catch (FileNotFoundException e) {
                log.error(e.getMessage());
                System.out.println("Could not load File given into FileInputStream ");
            }

        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param abs_remote_path the absolute path to the file/dir on the remote machine
     * @param abs_local_path the absoulte path to the local directory
     */
    public void download(String abs_remote_path, String abs_local_path) {
        try {
            int fileCount = 0;
            sftp_channel.lcd(abs_local_path);
            log.info("lcd " + sftp_channel.lpwd());

            // Get a listing of the remote directory
            @SuppressWarnings("unchecked")
            Vector<ChannelSftp.LsEntry> list = sftp_channel.ls(".");
            log.info("ls .");

            // iterate through objects in list, identifying specific file names
            for (ChannelSftp.LsEntry oListItem : list) {
                // output each item from directory listing for logs
                log.info(oListItem.toString());

                // If it is a file (not a directory)
                if (!oListItem.getAttrs().isDir()) {

                    // Grab the remote file ([remote filename], [local path/filename to write file to])
                    log.info("get " + oListItem.getFilename());
                    sftp_channel.get(oListItem.getFilename(), oListItem.getFilename());  // while testing, disable this or all of your test files will be grabbed

                    fileCount++;

                    // Delete remote file
                    //c.rm(oListItem.getFilename());  // Deleting remote files is not required in every situation.
                }
            }

            // Report files grabbed to log
            if (fileCount == 0) {
                log.info("Found no new files to grab.");
            } else {
                log.info("Retrieved " + fileCount + " new files.");
            }
        } catch(SftpException e) {
            log.warn(e.toString());
        } finally {
            // disconnect session.  If this is not done, the job will hang and leave log files locked
            session.disconnect();
            log.info("Session Closed");
        }
    }

    /**
     *
     * @param job_XML_abs_path the absolute path to the XML job file on the remote server
     */
    public void runNaspJob(String job_XML_abs_path) {

        InputStream exec_in;
        try {
            assert exec_channel != null;
            exec_in = exec_channel.getInputStream();
            exec_channel.setCommand("module load nasp"); //start the nasp tool
            log.info("module load nasp");
            exec_channel.setCommand("module load tnorth"); //start the tnorth tool [what does this do??]
            log.info("module load tnorth");
            exec_channel.setCommand("nasp --config " + job_XML_abs_path); //run nasp with the xml
            log.info("nasp --config " + job_XML_abs_path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDirectoryStructure(String remote_dir_path){

    }

    /**
     *  Tests to see if a specified file exists and is a file on the remote server
     *
     * @param remote_file_abs_path the absolute path to the file on the remote server
     */
    private void testFileExists(String remote_file_abs_path) throws IOException {

        InputStream exec_in;
        assert exec_channel != null;
        exec_in = exec_channel.getInputStream();
        exec_channel.setCommand("test -f " + remote_file_abs_path);

        if (exec_in.read() != 1){
            System.out.println("The file does not exist or is not a file.");
        }
    }

    /**
     *  Tests to see if a specified directory exists and is a directory on the remote server
     *
     * @param remote_dir_abs_path the absolute path to the file on the remote server
     */
    private void testDirExists(String remote_dir_abs_path) throws IOException {
        int exec_status;

        assert exec_channel != null;
        exec_status = exec_channel.getExitStatus();
        exec_channel.setCommand("test -d " + remote_dir_abs_path);

        if (exec_status != -1){
            System.out.println("The directory does not exist or is not a directory.");
        }


    }

}



