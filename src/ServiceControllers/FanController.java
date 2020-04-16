package ServiceControllers;

import AssociationAssets.*;
import Users.*;
import System.*;

import java.util.List;
import java.util.Scanner;

public class FanController {

    Fan fan;
    Search searcher;

    public FanController(Fan fan) {

        searcher = new Search();
        this.fan = fan;
    }

    /**
     * UC 3.1
     */
    public void logOut() {
        Scanner input = new Scanner(System.in);
        if (fan.getStatus() == EStatus.OFFLINE)
            System.out.println("Goodbye!");
        else System.out.println("Ho no! Something bad is going on in here");
    }

    /**
     * UC 3.2
     */
    public void subscribePersonalPage() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert a player's username");
        String username = input.nextLine();
        Player p = (Player) searcher.getUserByUserName(username);
        fan.subscribePersonalPage(p.getMyPage());
        System.out.println("Subscribed! :)");
    }

    /**
     * UC 3.3
     */
    public void subscribeGames() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert a league's name");
        League league = searcher.getLeagueByLeagueName(input.nextLine());
        if (league == null) {
            System.out.println("No such a league! Bye now. Leave me alone, Alon");
            return;
        }
        System.out.println("choose a year out of the following:");
        for (String year : league.getSeasonBinders().keySet()) {
            System.out.print(year + "; ");
        }
        Season season = searcher.getSeasonByYear(input.nextLine());
        if (season == null) {
            System.out.println("No such a season! Bye now. Leave me alone, Alon");
            return;
        }
        System.out.println("choose a game by its ID");
        for (Game game : league.getGames(season.getYear()).values()) {
            System.out.println("Game's ID: " + game.getGID() + " - Date: " + game.getDate() + "; ");
        }
        Game game = searcher.getGameByGameID(input.nextInt());
        fan.subscribeGames(game);
        System.out.println("Subscribed! :)");
    }

    /**
     * UC 3.4
     */
    public void submitComplain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert copmlain here");
        String complain = input.nextLine();
        while (complain.equals("")) {
            System.out.println("Cannot send an empty message. Insert ypu complain here");
            complain = input.nextLine();
        }
        fan.submitComplain(complain);
        System.out.println("Complain was submitted successfully");
    }

    /**
     * UC 3.5
     */
    public void viewSearchHistory() {
        List<String> history = fan.getSearchHistory();
        for (int i = 0; i < history.size(); i++) {
            System.out.println(history.get(i));
        }
    }

    /**
     * UC 3.6
     */
    public void viewDetails() {
        System.out.println(fan.toString());
    }

    /**
     * UC 3.6
     */
    public void editDetails() {
        Scanner input = new Scanner(System.in);
        System.out.println("insert first name");
        String firstName = input.nextLine();
        System.out.println("insert last name");
        String lastName = input.nextLine();

        fan.setfName(firstName);
        fan.setlName(lastName);

        if (fan.getfName().equals(firstName) && fan.getlName().equals(lastName))
            System.out.println("Name was edited");
        else System.out.println("Oops! try again");
    }
}
