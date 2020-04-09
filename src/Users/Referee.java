package Users;
import AssociationAssets.Game;
import System.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Referee extends AUser {

    Logger  logger; // singleton
    List<Game> myGames;
    EReferee training;


    public Referee(String UID, String fName, String lName, EReferee training) {
        super(UID, fName, lName);
        this.logger = Logger.getInstance();
        this.myGames = new ArrayList<>();
        this.training = training;
    }

    public List<Game> getMyGames() {
        return myGames;
    }

    public void setMyGames(List<Game> myGames) {
        if(myGames != null) {
            this.myGames = myGames;
        }
    }

    public EReferee getTraining() {
        return training;
    }

    public void setTraining(EReferee training) {
        if(training != null) {
            this.training = training;
        }
    }

    public void viewAssignedGames(){
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
}
