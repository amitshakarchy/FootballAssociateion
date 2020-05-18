package ServiceControllers;

import AssociationAssets.*;
import PoliciesAndAlgorithms.*;
import Users.*;
import System.*;
import java.util.HashMap;
import java.util.Scanner;

public class RefereeController {

    Referee referee;
    Search searcher;

    public RefereeController(String username, String firstName, String lastName,EReferee training) {
        this.referee = new Referee(username, firstName, lastName, training);
        searcher = new Search();
    }


    /**
     * UC 10.1
     */
    public  void setTraining(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert referee's training: \n" +
                "    VAR\n" +
                "    MAIN\n" +
                "    ASSISTANT\n");
        EReferee training = EReferee.valueOf(input.nextLine());
        referee.setTraining(training);
    }

    /**
     * UC 10.2
     */
    public void viewScheduledGames(){
        for (Game g:referee.viewAssignedGames()) {
            System.out.println(g.toString());
            System.out.println("+--------+++--------+");
        }
    }

    /**
     * UC 10.3
     */
    public void updateEventInAssignedGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        System.out.println("choose an event by its number");
        int i=0;
        for (Event event : game.getEvents()) {
            System.out.println(i+". "+event.getDate()+", "+ event.getTime()+","+" type:" + event.getEventType() +": "+event.getDescription());
        }
        int index= input.nextInt();
        System.out.println("choose an event type");
        System.out.println("Insert referee's training: \n    OFFSIDE,\n" +
                "    GOAL,\n" +
                "    FOUL,\n" +
                "    REDCARD,\n" +
                "    YELLOWCARD,\n" +
                "    INJURY,\n" +
                "    PLAYERREPLACEMENT");
        EEventType eventType = EEventType.valueOf(input.nextLine());
        System.out.println("Insert your description");
        String description= input.nextLine();
        try {
            referee.updateEventToAssignedGame(game.getGID(),index,eventType,description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Event was modified successfully");
    }

    /**
     * UC 10.3
     */
    public void removeEventFromAssignedGame(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        System.out.println("choose an event by its number");
        int i=0;
        for (Event event : game.getEvents()) {
            System.out.println(i+". "+event.getDate()+", "+ event.getTime()+","+" type:" + event.getEventType() +": "+event.getDescription());
        }
        int index= input.nextInt();
        try {
            referee.removeEventFromAssignedGame(game.getGID(),index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Event was removed successfully");
    }

    /**
     * UC 10.4
     */
    public void editEventsAfterGameOver(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        System.out.println("choose an event by its number");
        int i=0;
        for (Event event : game.getEvents()) {
            System.out.println(i+". "+event.getDate()+", "+ event.getTime()+","+" type:" + event.getEventType() +": "+event.getDescription());
        }
        int index= input.nextInt();
        System.out.println("choose an event type");
        System.out.println("Insert referee's training: \n    OFFSIDE,\n" +
                "    GOAL,\n" +
                "    FOUL,\n" +
                "    REDCARD,\n" +
                "    YELLOWCARD,\n" +
                "    INJURY,\n" +
                "    PLAYERREPLACEMENT");
        EEventType eventType = EEventType.valueOf(input.nextLine());
        System.out.println("Insert your description");
        String description= input.nextLine();
        referee.editEventsAfterGameOver(game.getGID(),index,eventType,description);
        System.out.println("Event was modified successfully");
    }

    /**
     * UC 10.4
     */
    public void removeEventsAfterGameOver(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        System.out.println("choose an event by its number");
        int i=0;
        for (Event event : game.getEvents()) {
            System.out.println(i+". "+event.getDate()+", "+ event.getTime()+","+" type:" + event.getEventType() +": "+event.getDescription());
        }
        int index= input.nextInt();
        referee.removeEventsAfterGameOver(game.getGID(),index);
        System.out.println("Event was removed successfully");
    }

    /**
     * UC 10.4
     */
    public void exportReport(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        //referee.exportReport(game.getGID());
    }
}
