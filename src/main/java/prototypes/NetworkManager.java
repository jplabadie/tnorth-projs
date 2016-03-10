package prototypes;

import com.jcraft.jsch.*;

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
                    return "TGeN&1217";
                }

                public String getPassword() {
                    return "TGeN&1217";
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

            Channel channel = null;
            try {
                channel = session.openChannel("sftp");
            } catch (JSchException e) {
                e.printStackTrace();
            }
            try {
                if (channel != null) {
                    channel.connect();
                }
            } catch (JSchException e) {
                e.printStackTrace();
            }
            ChannelSftp c = (ChannelSftp) channel;

            java.io.InputStream in = System.in;
            java.io.PrintStream out = System.out;

            java.util.Vector cmd = null;
            try {
                cmd = c.ls("/");
            } catch (SftpException e) {
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
