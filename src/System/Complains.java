package System;
import Users.Fan;
import Users.SystemManager;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

public class Complains {

    private List<Pair<String, Fan>> complain = new ArrayList<>();
    private static Complains ourInstance = new Complains();

    public static Complains getInstance() {
        return ourInstance;
    }

    private Complains() {
    }


    public void addComplain(String complain, Fan fan) {
        this.complain.add(new Pair<>(complain,fan));
    }

    public void removeComplain(Pair<String,Fan> complain) {
        this.complain.remove(complain);
    }

    public void responseToComplain(SystemManager systemManager,Pair<String,Fan> complain, String response ){
        complain.getValue().getResponseForComplain(systemManager,complain.getKey(),response);
        removeComplain(complain);
    }

    public List<Pair<String, Fan>> getComplain() {
        return complain;
    }

    public void setComplain(List<Pair<String, Fan>> complain) {
        this.complain = complain;
    }
}