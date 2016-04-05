package utils;

import com.pastdev.jsch.DefaultSessionFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * Provides a Java FileSystem which is defined by calls through SSH to a remote service
 * Primarily intended as a source of Files/Paths for the custom file/directory browser
 *
 * @author Jean-Paul Labadie
 */
public class RemoteFileSystemManager {

    private FileSystem sshfs;

    /**
     *
     * @param usrname a valid username on the remote service
     * @param pass the password for the username given
     * @param hosturl the url of the remote service
     * @param port the port to be used for ssh (typically = 22)
     * @throws URISyntaxException
     * @throws IOException
     */
    public void init(String usrname, String pass, String hosturl, int port) throws URISyntaxException, IOException {
        String str = "";

        DefaultSessionFactory dsf = new DefaultSessionFactory(usrname,hosturl,port);
        dsf.setPassword(pass);

        Map<String, Object> environment = new HashMap<String, Object>();
        environment.put( "defaultSessionFactory", dsf );

        //Start the session URI looking in the user's home directory
        URI uri = new URI( "ssh.unix://" + dsf.getUsername() + "@" + dsf.getHostname() + ":"
                + dsf.getPort() + "/home/" +dsf.getUsername() );

        sshfs = FileSystems.newFileSystem( uri, environment );
    }

    /**
     *
     * @param requested_path the path on the remote machine to explore
     * @return an iterator of Paths found in the remote directory
     * @throws IOException
     */
    public DirectoryStream<Path> getDirectory(String requested_path) throws IOException {

        if(sshfs == null || !sshfs.isOpen()){
            throw new ClosedDirectoryStreamException();
        }

        Path path = sshfs.getPath( requested_path ); // refers to an absolute remote path
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
           return ds;
        }
    }
}
