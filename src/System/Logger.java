package System;

import AssociationAssets.Event;
import AssociationAssets.Game;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * All system operations are kept in this class for tracking and error tracking
 * Singleton
 * @ Written by Yuval Ben Eliezer
 */
public class Logger {

// Implement a singleton here
    List<String> log = new ArrayList<>();
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
        getLog().add(action);
    }

    public List<String> getLog() {
        return log;
    }
}
