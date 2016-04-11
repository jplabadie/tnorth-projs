package utils;

import xmlbinds.NaspInputData;

import java.net.URL;
import java.nio.file.Path;

/**
 * Simple object for holding details regarding a job submission
 *
 * @author Jean-Paul Labadie
 */
public class DispatchConfiguration {
    private String username;
    private String jobname;
    private URL server;
    private int port;
    private NaspInputData data;
    private String xml_name;
    private Path xml_path;

    public DispatchConfiguration(String username, String jobname, URL server, int port, NaspInputData data, String xml_name, Path xml_path) {
        this.username = username;
        this.server = server;
        this.port = port;
        this.data = data;
        this.xml_name = xml_name;
        this.xml_path = xml_path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public URL getServer() {
        return server;
    }

    public void setServer(URL server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public NaspInputData getData() {
        return data;
    }

    public void setData(NaspInputData data) {
        this.data = data;
    }

    public String getXml_name() {
        return xml_name;
    }

    public void setXml_name(String xml_name) {
        this.xml_name = xml_name;
    }

    public Path getXml_path() {
        return xml_path;
    }

    public void setXml_path(Path xml_path) {
        this.xml_path = xml_path;
    }

    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }
}
