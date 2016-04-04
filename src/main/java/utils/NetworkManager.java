package utils;

import com.jcraft.jsch.*;

import java.io.*;

/**
 * @author Jean-Paul Labadie
 */
public class NetworkManager {

    private UserInfo usrinfo;
    private Session session;
    private JSch jsch;


    public NetworkManager() {
        jsch = new JSch();

        try {
            session = jsch.getSession("jlabadie", "aspen.tgen.org", 22);
            usrinfo = new UserInfo() {
                public String getPassphrase() {
                    return "C00kiemnstr!";
                }

                public String getPassword() {
                    return "C00kiemnstr!";
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
            };

            //jsch.addIdentity("C:\\Users\\Jean-Paul\\.ssh\\id_rsa");
            //session.setConfig("StrictHostKeyChecking","no");
            session.setUserInfo(usrinfo);
            try {
                session.connect();
            } catch (JSchException e) {
                e.printStackTrace();
            }

            Channel sftpchannel = null;
            Channel execchannel = null;
            try {
                sftpchannel = session.openChannel("sftp");
                execchannel = session.openChannel("exec");
            } catch (JSchException e) {
                e.printStackTrace();
            }
            try {
                if (sftpchannel != null) {
                    sftpchannel.connect();
                }
                if (execchannel != null){
                    execchannel.connect();
                }
            } catch (JSchException e) {
                e.printStackTrace();
            }
            ChannelSftp sftp_chan = (ChannelSftp) sftpchannel;
            ChannelExec exec_chan = (ChannelExec) execchannel;

            java.io.InputStream in = System.in;
            java.io.PrintStream out = System.out;

            java.util.Vector cmd = null;
            try {
                assert sftp_chan != null;
                File jobxml =  new File(getClass().getClassLoader().getResource("NaspInputExample_Aspen.xml").getPath());
                try {
                    sftp_chan.put(new FileInputStream(jobxml), jobxml.getName());
                } catch (FileNotFoundException e) {
                    System.out.println("Could not load File given into FileInputStream ");
                }
                cmd = sftp_chan.ls("/home/jlabadie");

            } catch (SftpException e) {
                e.printStackTrace();
            }
            InputStream exec_in;
            try{
                assert exec_chan != null;
                exec_in = exec_chan.getInputStream();
                exec_chan.setCommand("module load nasp");
                exec_chan.setCommand("module load tnorth");
                exec_chan.setCommand("nasp /home/jlabadie/NaspInputExample_Aspen.xml");

            } catch (IOException e) {
                e.printStackTrace();
            }
            if (cmd != null) {
                for (int ii = 0; ii < cmd.size(); ii++) {
                    out.println(cmd.elementAt(ii).toString());
                    Object obj = cmd.elementAt(ii);
                    if (obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry) {
                        out.println(((com.jcraft.jsch.ChannelSftp.LsEntry) obj).getLongname());
                    }
                }
            }
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public Session getSession(){
        return session;
    }
}
