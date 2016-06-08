package utils;

/**
 * Simple object for holding details regarding a job submission
 *
 * @author Jean-Paul Labadie
 */
public class DispatchConfiguration {
    private String username;
    private String timestamp;
    private String server;
    private int port;
    private String xml_path;

    public DispatchConfiguration(String username, String server, String timestamp, int port, String xml_path) {
        this.username = username;
        this.server = server;
        this.port = port;
        this.xml_path = xml_path;
        this.timestamp = timestamp;
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
        return xml_path;
    }

    public void setXmlPath(String xml_path) {
        this.xml_path = xml_path;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
