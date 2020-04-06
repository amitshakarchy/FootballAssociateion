package Users;
import AssociationAssets.Game;
import System.*;

import java.util.ArrayList;
import java.util.List;


public class Referee extends AUser {

    ETraining training;
    Logger logger; // singleton
    List<Game> myGames;

    public Referee(String UID, String fName, String lName, ETraining training, Logger logger) {
        super(UID, fName, lName);
        this.training = training;
        this.logger = logger;
        this.myGames = new ArrayList<>();
    }

    public ETraining getTraining() {
        return training;
    }

    public void setTraining(ETraining training) {
        this.training = training;
    }

    public void getAssignedGames(){
        if(this.myGames.size() == 0){
            System.out.println("You haven't selected a game yet");
        }
        else{
            for (Game game:
                 myGames) {
                System.out.println(game);
            }
        }
    } //10.2

    public void addEventToAssignedGame(){

    } //10.3

    public void updateEventToAssignedGame(){} //10.3

    public void editEvents(){} //10.4

    public void exportReport(){//10.4

        //TODO: Use the class "Logger" (FootballSystem package) in order to create a report.

    }
}
