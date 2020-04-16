package Users;
import AssociationAssets.EEventType;
import AssociationAssets.Event;
import AssociationAssets.Game;
import System.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A football referee is a soccer game referee who enforces the rules of the game set
 * in the football game constitution.
 *
 * @ Written by Yuval Ben Eliezer
 */
public class Referee extends Fan {
    private List<Game> myGames;
    private EReferee training;

    /**
     * @param userName - Unique user name
     * @param fName - First name of the referee.
     * @param lName - Last name of the referee.
     * @param training - Referee type of training. It could be:
     *                 VAR,MAIN, ASSISTANT
     */
    public Referee(String userName, String fName, String lName, EReferee training) {
        super(userName, fName, lName);
        this.myGames = new ArrayList<>();
        this.training = training;
        Logger.getInstance().addActionToLogger("Referee created, user name: "+ userName);

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

    /**
     * A football referee can watch all the games he is supposed to judge
     * # use case 10.2
     * @return A list of all the games the referee needs to judge
     */
    public List<Game> viewAssignedGames(){
        return getMyGames();
    }

    public void addGame(Game game){
        if(game!= null){
            this.myGames.add(game);
        }
    }

    /**
     *The referee can add an event during a game in which he is a referee
     * @param gameID - Game ID
     * @param eventType - The type of event the referee wants to add
     * @param description - Verbal description of the event
     *
     * # use case 10.3
     */
    public void addEventToAssignedGame(int gameID, EEventType eventType, String description){
        Game gameToAdd = getGame(gameID);
        if(gameToAdd != null ){
            if(gameToAdd.isUpdatable(2)) {
                gameToAdd.addEvent(eventType, description);
            }
        }
    }

    /**
     *The referee can edit the event during the match he is judging
     * @param gameID -  Game ID
     * @param eventIndex - The index of the event the referee wants to edit
     * @param eventType - The type of event the referee wants to update
     * @param description - Verbal description of the event
     *
     * # use case 10.3
     */
    public void updateEventToAssignedGame(int gameID,int eventIndex, EEventType eventType, String description){
        Game gameToAdd = getGame(gameID);
        if(gameToAdd != null && eventIndex!= -1 ){
            if(gameToAdd.isUpdatable(2)) {
                gameToAdd.editEvent(eventIndex, eventType, description);
            }
        }
    }

    /**
     *The referee can delete the event during the match he is judging
     * @param gameID -  Game ID
     * @param eventIndex - The index of the event the referee wants to remove
     *
     * # use case 10.3
     */
    public void removeEventFromAssignedGame(int gameID,int eventIndex){
        Game gameToAdd = getGame(gameID);
        if(gameToAdd != null && eventIndex!= -1){
            if(gameToAdd.isUpdatable(2)) {
                gameToAdd.removeEvent(eventIndex);
            }
        }
    }

    /**
     *The referee can hold events after 5 hours of the match being played
     * @param gameID -  Game ID
     * @param eventIndex - The index of the event the referee wants to edit
     * @param eventType - The type of event the referee wants to update
     * @param description - Verbal description of the event
     *
     * # use case 10.4
     */
    public void editEventsAfterGameOver(int gameID,int eventIndex, EEventType eventType, String description){
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = getGame(gameID);
            if (gameToAdd != null && gameToAdd.getMain().getUserName().equals(this.getUserName()) && eventIndex!= -1) {
                if(gameToAdd.isUpdatable(7)) {
                    gameToAdd.editEvent(eventIndex, eventType, description);
                }
            }
        }
    } //10.4

    /**
     *A Referee can delete events after 5 hours from the end of the match in which he is a Referee
     * @param gameID -  Game ID
     * @param eventIndex - The index of the event the referee wants to remove
     *
     * # use case 10.4
     */
    public void removeEventsAfterGameOver(int gameID,int eventIndex){
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = null;
            gameToAdd = getGame(gameID);
            if (gameToAdd != null && gameToAdd.getMain().getUserName().equals(this.getUserName())&& eventIndex!= -1){
                if(gameToAdd.isUpdatable(7)){
                    gameToAdd.removeEvent(eventIndex);
                }
            }
        }
    }

    /**
     *A main referee can produce a game report 5 hours after it has ended
     * @param gameID -  Game ID
     *
     * # use case 10.4
     */
    public void exportReport(int gameID){//10.4
        if(this.training.equals(EReferee.MAIN)){
            Game gameToAdd = getGame(gameID);
            if (gameToAdd != null) {
                if(gameToAdd.isUpdatable(7)) {
                    Logger.getInstance().exportReport(gameToAdd.getGID(),gameToAdd.getEvents());
                }
            }
        }
    }

    /**
     *This function get a game ID and searches for the game in the referee's games
     * @param gameID -  Game ID
     * @return The game we are looking for
     */
    private Game getGame(int gameID) {
        for (Game game :
                myGames) {
            if (game.getGID() == gameID) {
                return game;
            }
        }
        return null;
    }

    /**
     *When the referee wants to delete or edit an event he needs to index the event
     * @param gameID -  Game ID
     * @param eventType - The type of event
     * @param description - Verbal description of the event
     * @return The index of the event the referee wants to edit.
     *          return "-1" if the event does not exist
     */
    public int getIndexOfEvent(int gameID, EEventType eventType, String description){
        if(eventType == null || description == null || gameID < 0 ){
            return -1;
        }
        Game gameToAdd = getGame(gameID);
        int res =-1;
        if(gameToAdd != null) {
            List<Event> eventList = gameToAdd.getEvents();
            for (Event e :
                    eventList) {
                if (e.getDescription().equals(description) && e.getEventType().equals(eventType)) {
                    res = eventList.indexOf(e);
                }
            }
        }
        return res;
    }


    /**
     * Yarin's request
     */
    public void LoginInvitation(String userName , String pass){
        FootballSystem.getInstance().login(userName,pass);
    }
}
