package Users;
import RecommendationSystem.*;
import System.*;
import javafx.util.Pair;
import java.util.List;

/**
 * An administrator is responsible for responding to the various user inquiries
 * and for proper system operation.
 * @ Written by Yuval Ben Eliezer
 */
public class SystemManager extends Fan {

    /**
     * @param userName - Unique user name
     * @param fName - First name of the system manager.
     * @param lName - Last name of the system manager.
     */
    public SystemManager(String userName, String fName, String lName) {
        super(userName, fName, lName);
        Logger.getInstance().addActionToLogger("System Manager created, user name: "+ userName);

    }

    public void closeTeam(){} //useCase 8.1

    public void removeUser(){} //useCase 8.2

    /**
     *  An administrator can view the complaint box
     * @return - List all complaints and who filed them
     *
     * # use case 8.3
     */
    public List<Pair<String, Fan>> getComplains(){
        return Complains.getInstance().getComplain();
    }

    /**
     * An administrator may respond to complaints received from fans
     * @param response -The answer from the administrator
     * @param compain - The complaint and the fan who filed it
     *
     * # use case 8.3
     */
    public void responseOnComplain(String response, Pair<String,Fan> compain){
        Complains.getInstance().responseToComplain(this,compain,response);
    }

    /**
     * An administrator can view system behavior information
     * @return - List all actions done on the system
     *
     * # use case 8.4
     */
    public List<String> getLogInformation(){
        return Logger.getInstance().getLog();
    }

    /**
     * The role of the administrator is to start building the
     * recommendation system model
     * @param model - A recommendation system designed to indicate the chances of a
     *              team winning the game.
     *
     * # use case 8.5
     *
     */
    public void activateRecommendationSystemModel(ComputaionalModel model){
        model.activate();
    }

}