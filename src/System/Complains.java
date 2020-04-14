package System;
import Users.Fan;
import Users.SystemManager;
import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;

/**
 * This class constitutes an Administrator Inquiry Box for improper information
 * @ Written by Yuval Ben Eliezer
 */
public class Complains {

    private List<Pair<String, Fan>> complain = new ArrayList<>();
    private static Complains ourInstance = new Complains();

    public static Complains getInstance() {
        return ourInstance;
    }

    private Complains() {
    }

    /**
     *With this function a fan submits a complaint to the complaints system
     *
     * @param complain -The complaint the fan makes
     * @param fan - The fan who files the complaint
     */
    public void addComplain(String complain, Fan fan) {
        this.complain.add(new Pair<>(complain,fan));
    }

    /**
     *This function allows you to delete a complaint from the system
     *
     * @param complain - The complaint the system manager want to remove
     */
    private void removeComplain(Pair<String,Fan> complain) {
        this.complain.remove(complain);
    }

    /**
     *This function allows you to respond to a complaint
     * After answering the complaint, the complaint is deleted from the system
     *
     * @param systemManager - The manager who answers the complaint
     * @param complain - The complaint
     * @param response -Answer to complaint
     */
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