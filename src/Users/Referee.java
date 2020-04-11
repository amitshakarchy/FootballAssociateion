package Users;
import AssociationAssets.Game;
import System.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Referee extends AUser {

    ETraining training;
    Logger logger; // singleton
    List<Game> myGames;
    EReferee role;

    public Referee(String UID, String fName, String lName, ETraining training, Logger logger,EReferee role) {
        super(UID, fName, lName);
        this.training = training;
        this.logger = logger;
        this.myGames = new ArrayList<>();
        this.role = role;
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

    public void addEventToAssignedGame(Date dateOfTheGame){

    } //10.3

    public void updateEventToAssignedGame(){} //10.3

    public void editEvents(){} //10.4

    public void exportReport(){//10.4

        //TODO: Use the class "Logger" (FootballSystem package) in order to create a report.

    }

    @Override
    public String viewProfile() {
        return null;
    }

    @Override
    public EStatus getStatus() {
        return null;
    }

    @Override
    public void setStatus(EStatus status) {

    }
}
