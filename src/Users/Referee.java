package Users;
import AssociationAssets.EEventType;
import AssociationAssets.Game;
import System.*;
import java.util.ArrayList;
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

    public List<Game> viewAssignedGames(){
        return getMyGames();
    } //10.2

    public void addEventToAssignedGame(String gameID, EEventType eventType, String description){
        Game gameToAdd = null;
        gameToAdd = getGame(gameID, gameToAdd);
        // TODO: 09/04/2020 check time - during the game
        if(gameToAdd != null ){
            gameToAdd.addEvent(eventType,description);
        }
    } //10.3

    public void updateEventToAssignedGame(String gameID,int eventIndex, EEventType eventType, String description){
        Game gameToAdd = null;
        gameToAdd = getGame(gameID, gameToAdd);
        // TODO: 09/04/2020 check time - during the game
        if(gameToAdd != null ){
            gameToAdd.editEvent(eventIndex,eventType,description);
        }
    } //10.3

    public void removeEventFromAssignedGame(String gameID,int eventIndex){
        Game gameToAdd = null;
        gameToAdd = getGame(gameID, gameToAdd);
        // TODO: 09/04/2020 check time - during the game
        if(gameToAdd != null ){
            gameToAdd.removeEvent(eventIndex);
        }
    } //10.3

    public void editEventsAfterGameOver(String gameID,int eventIndex, EEventType eventType, String description){
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = null;
            gameToAdd = getGame(gameID, gameToAdd);
            // TODO: 09/04/2020 check time - until 5 H after the game
            if (gameToAdd != null) {
                gameToAdd.editEvent(eventIndex, eventType, description);
            }
        }
    } //10.4

    public void removeEventsAfterGameOver(String gameID,int eventIndex){
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = null;
            gameToAdd = getGame(gameID, gameToAdd);
            // TODO: 09/04/2020 check time - until 5 H after the game
            if (gameToAdd != null){
                gameToAdd.removeEvent(eventIndex);
            }
        }
    } //10.4

    public void exportReport(String gameID){//10.4
        //TODO: Use the class "Logger" (FootballSystem package) in order to create a report.
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = null;
            gameToAdd = getGame(gameID, gameToAdd);
            if (gameToAdd != null) {
                // TODO: 09/04/2020 find what is written in the report
                this.logger.exportReport(gameToAdd);
            }
        }
    }

    private Game getGame(String gameID, Game gameToAdd) {
        for (Game game :
                myGames) {
            if (game.getGID().equals(gameID)) {
                gameToAdd = game;
            }
        }
        return gameToAdd;
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
