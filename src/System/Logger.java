package System;

import AssociationAssets.Event;
import AssociationAssets.Game;

import java.util.ArrayList;
import java.util.List;

public class Logger {

// Implement a singleton here
    List<String> log = new ArrayList<>();
    public static Logger getInstance(){
        return new Logger();
    }


    public void exportReport(Game gameToAdd) {
    }

    public void exportReport(int gid, List<Event> events) {
    }

    public void addActionToLogger(String action){
        this.log.add(action);
    }

    public List<String> getLog() {
        return log;
    }
}
