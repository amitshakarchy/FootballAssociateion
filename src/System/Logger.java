package System;

import AssociationAssets.Event;
import AssociationAssets.Game;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
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

    public void exportReport(int gid, List<Event> events) {
        if(events!= null){
            this.report.add(new Pair<>(gid,events));
        }
    }

    public void addActionToLogger(String action){
        getLog().add(action);
    }

    public List<String> getLog() {
        return log;
    }
}
