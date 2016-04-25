package utils;

/**
 * Simple object for holding details regarding a job submission
 *
 * @author Jean-Paul Labadie
 */
public class JobRecord {
    private String username;
    private String server;
    private int port;
    private String remote_xml_path;

    private String local_xml_path;

    private String start_timestamp;
    private String end_timestamp;
    private boolean completed;

    public JobRecord(String username, String server, int port, String remote_xml_path, String local_xml_path) {
        this.username = username;
        this.server = server;
        this.port = port;
        this.remote_xml_path = remote_xml_path;
        this.local_xml_path = local_xml_path;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getXmlPath() {
        return remote_xml_path;
    }

    public void setXmlPath(String xml_path) {
        this.remote_xml_path = xml_path;
    }

    public String getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(String start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public String getEnd_timestamp() {
        return end_timestamp;
    }

    public void setEnd_timestamp(String end_timestamp) {
        this.end_timestamp = end_timestamp;
    }

    public String getLocal_xml_path() {
        return local_xml_path;
    }

    public void setLocal_xml_path(String local_xml_path) {
        this.local_xml_path = local_xml_path;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
