package utils;

import com.jcraft.jsch.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 *
 * Manages basic logging via the Jsch Logger interface for SSH SFTP and EXEC actions
 *
 * @author Jean-Paul Labadie
 */
public class LogManager implements Logger{

    private static FileHandler fh;
    private static Hashtable<Integer, String> name=new Hashtable<>();

    /**
     * Define log types
     */
    static{
        name.put(DEBUG, "DEBUG");
        name.put(INFO, "INFO");
        name.put(WARN, "WARN");
        name.put(ERROR, "ERROR");
        name.put(FATAL, "FATAL");
    }

    public LogManager(){

        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("out\\"+"log-"+ getDate()+".txt",true);


            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            this.info("Logger initialized.");

        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
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

        message = getTimestamp() + " :- " + message;

        LogRecord record = new LogRecord(Level.parse(name.get(level)),message);
        fh.publish(record);
        System.err.print(name.get(new Integer(level))+": "); //echo the message to System.err
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

    @SuppressWarnings("unchecked")
    public void logJob(DispatchConfiguration dc) throws IOException {
        JSONObject obj = new JSONObject();
        obj.put("User Name", dc.getUsername());
        obj.put("Job Name", dc.getJobname());

        JSONArray company = new JSONArray();
        company.add("Compnay: eBay");
        company.add("Compnay: Paypal");
        company.add("Compnay: Google");
        obj.put("Company List", company);

        // try-with-resources statement based on post comment below :)
        try (FileWriter file = new FileWriter("/out\\joblog"+getTimestamp()+".txt")) {
            file.write(obj.toJSONString());
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + obj);
        }
    }

    /**
     *
     * @return the current time as a formatted string
     */
    private String getTimestamp(){

        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy-HH:mm:ss");

        return formatter.format(date);
    }

    private String getDate(){

        Date date = new Date(System.currentTimeMillis());
        DateFormat formatter = new SimpleDateFormat("dd-MMM-YYYY");

        return formatter.format(date);
    }

}
