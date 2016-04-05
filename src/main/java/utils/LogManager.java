package utils;

import com.jcraft.jsch.Logger;

import java.util.Hashtable;

/**
 *
 * Manages basic logging via the Jsch Logger interface for SSH SFTP and EXEC actions
 *
 * @author Jean-Paul Labadie
 */
public class LogManager implements Logger{

    private static Hashtable<Integer, String> name=new Hashtable<>();

    /**
     * Define log types in the constructor
     */
    static{
        name.put(DEBUG, "DEBUG: ");
        name.put(INFO, "INFO: ");
        name.put(WARN, "WARN: ");
        name.put(ERROR, "ERROR: ");
        name.put(FATAL, "FATAL: ");
    }

    /**
     * Check what level of logging has been enabled.
     * !!! Unimplemented !!!
     * @param level level of logging to check if enabled
     * @return true if logging at that level is enabled, false otherwise
     */
    public boolean isEnabled(int level){
        return true;
    }

    /**
     * Logs a given message at the specified level (of those defined in the constructor).
     * @param level the log level (type) {DEBUG,INFO,WARN,ERROR,FATAL}
     * @param message the log message for the action being logged
     */
    public void log(int level, String message){
        System.err.print(name.get(new Integer(level))); //echo the message to System.err
        System.err.println(message);
    }

    /**
     * Wrapper for INFO logs
     * @param message log message for INFO events
     */
    public void info(String message){
        log(INFO,message);
    }

    /**
     * Wrapper for WARN logs
     * @param message log message for WARN events
     */
    public void warn(String message){
        log(WARN,message);
    }

    /**
     * Wrapper for ERROR logs
     * @param message log messages for ERROR events
     */
    public void error(String message){
        log(ERROR,message);
    }

}
