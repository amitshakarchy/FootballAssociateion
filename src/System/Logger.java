package System;
import AssociationAssets.Event;
import javafx.util.Pair;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * All system operations are kept in this class for tracking and error tracking
 * Singleton
 * @ Written by Yuval Ben Eliezer
 */
public class Logger {

// Implement a singleton here
    List<String> actionLog = new ArrayList<>();



    List<String> errorLog = new ArrayList<>();
    List<Pair<Integer,List<Event>>> report = new ArrayList<>();
    private static Logger single_instance = null;

    public static Logger getInstance()
    {
        if (single_instance == null)
            single_instance = new Logger();

        return single_instance;
    }
    private Logger()
    {

    }

    /**
     *  Through the Log main referee can generate reports on games
     * @param gid - game id
     * @param events - List of events that happened in a game
     */
    public void exportReport(int gid, List<Event> events) {
        if(events!= null){
            this.report.add(new Pair<>(gid,events));
        }
    }

    /**
     * Using this function you can add actions that occurred in the system to the logger
     * @param action - The action we want to add
     */
    public void addActionToLogger(String action){
        getActionLog().add(action);
    }

    /**
     * Using this function you can add errors that occurred in the system to the error logger
     * @param action - The action we want to add
     */
    public void addErrorToLogger(String action){
        getErrorActionLog().add(action);
    }

    private Collection<String> getErrorActionLog() {
        return errorLog;
    }

    public List<String> getActionLog() {
        return actionLog;
    }

    public List<String> getErrorLog() {
        return errorLog;
    }

    public void WriteActionLoggerToFile(File filepath) {
        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(actionLog);
            objectOut.close();
            fileOut.close();
            System.out.println("The Action Logger was successfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void WriteErrorLoggerToFile(File filepath) {
        try {

            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(errorLog);
            objectOut.close();
            fileOut.close();
            System.out.println("The Error Logger was successfully written to a file");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
