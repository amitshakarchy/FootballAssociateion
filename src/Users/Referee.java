package Users;
import System.*;


public class Referee extends AUser {

    ETraining training;
    Logger logger; // singleton

    public Referee(int UID, String fName, String lName) {
        super(UID, fName, lName);
    }


    public void getAssignedGames(){} //10.2

    public void addEventToAssignedGame(){} //10.3

    public void editEvents(){} //10.4

    public void exportReport(){//10.4

        //TODO: Use the class "Logger" (FootballSystem package) in order to create a report.

    }
}
