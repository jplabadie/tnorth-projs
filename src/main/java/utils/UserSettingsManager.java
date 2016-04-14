package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Managers user details and default settings
 *
 * @author Jean-Paul Labadie
 */
public class UserSettingsManager {

    private static final UserSettingsManager instance = new UserSettingsManager();
    private static final LogManager log = LogManager.getInstance();

    private static JSONObject general_settings;
    private static JSONObject remote_settings;

    private static String local_save_dir = "";

    private static String username;

    private UserSettingsManager(){
        remote_settings = readSettings(local_save_dir+"remote_settings.json");
        general_settings = readSettings(local_save_dir+"general_settings.json");
        if (general_settings != null) {
            username = (String) general_settings.get("Username");
        }
    }

    /**
     *
     * @return
     */
    public static UserSettingsManager getInstance(){
        return instance;
    }

    /**
     *
     * @return
     */
    public static JSONObject getCurrentRemoteSettings(){
        if (!remote_settings.containsKey("Current Remote"))
        {
            log.error("Failed to set Current Remote: specified remote settings not found");
            return null;
        }
        log.info("Current Remote Settings successfully returned.");
        return (JSONObject) remote_settings.get("Current Remote");
    }

    /**
     * Sets the currently chosen remote settings.
     * Changes are made to the runtime environment and to saved configurations.
     *
     * @param settings_name the remote settings name to try and use as current remote
     */
    @SuppressWarnings("unchecked")
    public static void setCurrentRemote(String settings_name){

        if(settings_name == null) {
            log.error("Failed to set Current Remote: given remote settings name was null");
            return;
        }
        else if (!remote_settings.containsKey(settings_name))
        {
            log.error("Failed to set Current Remote: specified remote settings not found");
            return;
        }

        general_settings.put("Current Remote",settings_name);
        writeSettings(local_save_dir+"general_settings.json",general_settings);
        log.info("Current Remote Settings successfully modified in runtime and local media: " + settings_name);
    }

    /**
     * Adds a new remote settings JSON to the runtime and to local media.
     * These saved settings can be used to quickly change target remote hosts in the UI.
     *
     * @param settings_name the name given to the settings, to be used as a key
     * @param usrname the user name for the given remote host
     * @param url the remote address of the remote host
     * @param port the port to use when connecting to the remote host
     * @param jobmngr the name of the jobmngr system
     * @param remote_dirs an array of Strings representing remote directories to display in the UI
     */
    @SuppressWarnings("unchecked")
    public static void addRemoteSettings(String settings_name, String usrname, String url, int port,
                                         String jobmngr, String[] remote_dirs){
        JSONObject json = new JSONObject();
        json.put("Remote Settings Name", settings_name);
        json.put("User Name", usrname);
        json.put("URL", url);
        json.put("Port", port);
        json.put("Job Manager", jobmngr);

        JSONArray dirs = new JSONArray();
            dirs.add(remote_dirs);

        json.put("Remote Directories", dirs);

        remote_settings.put(settings_name,json);
        writeSettings(local_save_dir+"remote_settings.json",remote_settings);
        log.info("Remote Settings successfully added to runtime and local media: " + settings_name);
    }

    /**
     * Removes saved Remote Settings from the runtime and from saved JSON
     *
     * @param settings_name the name of the remote settings to be removed, if found
     */
    public static void removeRemoteSettings(String settings_name){

        if(settings_name == null){
            log.error("Failed to remove Remote Settings: given path was null");
            return;
        }

        try{
            JSONObject json = (JSONObject) remote_settings.remove(settings_name);
            assert json != null;
        }
        catch (Exception e){
            log.error("Failed to remove Remote Settings: given path was null");
            return;
        }

        writeSettings(local_save_dir+"remote_settings.json",remote_settings);
        log.info("Remote Settings successfully removed: " + settings_name);
    }

    /**
     * @param path the local path to write to
     * @param json the json to write to the local media
     */
    private static void writeSettings(String path, JSONObject json){

        if(path == null){
            log.error("Failed to save Settings to local file: given path was null");
            return;
        }

        try (FileWriter fw = new FileWriter(path)) {
            fw.write(json.toJSONString());
            log.info("Settings successfully saved to local file: " + path);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("Failed to save Settings to local file: " + path + "\nReason:\n" + e.getMessage());
        }
    }

    /**
     * Loads saved settings JSONObjects from the local disk
     * @return the loaded settings as a JSONObject, or null
     */
    private static JSONObject readSettings(String path){

        if(path == null){
            log.error("Failed to load Settings from local file: given path was null");
            return null;
        }
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader(local_save_dir));
            log.info("Settings successfully loaded from local file: " + path);
            return (JSONObject) obj;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Failed to load Settings from local file: " + path + "\nReason:\n" + e.getMessage());
        }
        return null;
    }

    /**
     *
     * @return the local username
     */
    public static String getUsername() {
        return username;
    }

    public static String getCurrentServerUrl() {

        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            return (String) current.get("URL");
        }
        return "";
    }
}
