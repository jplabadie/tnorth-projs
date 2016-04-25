package utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Manages user details and default settings
 *
 * @author Jean-Paul Labadie
 */
public class UserSettingsManager {

    private static UserSettingsManager instance;
    private static LogManager log;

    private static JSONObject general_settings;
    private static JSONObject remote_settings;

    private static String general_config_dir ;
    private static String usr_config_dir;
    private static String remote_config_dir;
    private static String local_save_dir;

    private static String username;

    private UserSettingsManager(){

        log = LogManager.getInstance();

        general_config_dir = new File(getClass().getResource("/configs/general_settings.json").getPath()).toString();
        remote_config_dir = new File(getClass().getResource("/configs/remote_settings.json").getPath()).toString();
        System.out.println(general_config_dir);

        general_settings = readSettings(general_config_dir);

        if (general_settings != null) {
            usr_config_dir = (String)general_settings.get("usrsettingsdir");
            local_save_dir = (String)general_settings.get("localsavedir");
        }

        remote_settings = readSettings(remote_config_dir);
        if( remote_settings != null){

        }
    }

    /**
     *
     * @return UserSettingsManager Singleton
     */
    public static UserSettingsManager getInstance(){
        if(instance == null)
            instance = new UserSettingsManager();
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
        String current_remote_id = (String) remote_settings.get("Current Remote");
        return (JSONObject) remote_settings.get(current_remote_id);
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
        writeSettings(general_config_dir +"general_settings.json",general_settings);
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

        for(String dir : remote_dirs){
            dirs.add(dir);
        }
        json.put("Remote Directories", dirs);

        remote_settings.put(settings_name,json);
        writeSettings(remote_config_dir ,remote_settings);
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

        writeSettings(remote_config_dir ,remote_settings);
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
            Object obj = parser.parse(new FileReader(path));
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

        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            return (String) current.get("User Name");
        }
        return "";
    }

    /**
     *
     * @param new_username
     */
    public static void setUsername(String new_username) {
        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            current.put("User Name", new_username);
        }
    }

    /**
     *
     * @return
     */
    public static String getCurrentServerUrl() {

        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            return (String) current.get("URL");
        }
        return "";
    }

    /**
     *
     * @return
     */
    public static String getDefaultLocalSaveDir() {
        return local_save_dir;
    }

    /**
     *
     * @param new_dir
     */
    public static void setDefaultLocalSaveDir(String new_dir) {
        local_save_dir = new_dir;
    }

    /**
     *
     * @return
     */
    public static Integer getCurrentServerPort() {
        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            return ((Long)current.get("Port")).intValue();
        }
        return null;

    }

    /**
     *
     * @param port
     */
    public static void setCurrentServerPort(int port) {
    }

    public static String getDefaultRemoteRoot() {

        JSONObject current = getCurrentRemoteSettings();

        if (current != null) {
            JSONArray rem_dirs = (JSONArray) current.get("Remote Directories");
            return (String)rem_dirs.get(0);
        }
        return null;
    }
}
