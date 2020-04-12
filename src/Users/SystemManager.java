package Users;
import RecommendationSystem.*;
import System.*;
import javafx.util.Pair;
import java.util.List;

/**
 * @ Written by Yuval Ben Eliezer
 */
public class SystemManager extends Fan {

    public SystemManager(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    public void closeTeam(){} //useCase 8.1

    public void removeUser(){} //useCase 8.2

    public List<Pair<String, Fan>> getComplains(){
        return Complains.getInstance().getComplain();
    } //useCase 8.3

    public void responseOnComplain(String response, Pair<String,Fan> compain){
        Complains.getInstance().responseToComplain(this,compain,response);
    } //useCase 8.3

    public List<String> getLogInformation(){
        return Logger.getInstance().getLog();
    } //useCase 8.4

    public void activateRecommendationSystemModel(ComputaionalModel model){
        model.activate();
    } //useCase 8.5

}