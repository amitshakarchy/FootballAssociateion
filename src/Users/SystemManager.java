package Users;
import RecommendationSystem.*;

public class SystemManager extends AUser {

    public SystemManager(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    public void closeTeam(){} //useCase 8.1

    public void removeUser(){} //useCase 8.2

    public void getComplains(){} //useCase 8.3

    public void responseOnComplain(){} //useCase 8.3

    public void getLogInformation(){} //useCase 8.4

    public void activateRecommendationSystemModel(ComputaionalModel model){} //useCase 8.5
}