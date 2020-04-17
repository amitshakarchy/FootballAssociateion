package ServiceControllers;

import AssociationAssets.*;
import Budget.TeamBudget;
import PoliciesAndAlgorithms.*;
import Users.*;
import System.*;
import java.util.HashMap;
import java.util.Scanner;


public class RepresentativeController {

    RepresentativeFootballAssociation rep;
    Search searcher;

    public RepresentativeController(String username, String firstName, String lastName) {
        this.rep = new RepresentativeFootballAssociation(username, firstName, lastName);
        searcher = new Search();
    }

    /**
     * UC 9.1
     */
    public void defineNewLeague() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        System.out.println("Insert first season's year");
        String year = input.nextLine();
        System.out.println("Choose score-table-policy by its number:\n" +
                "1. policy 1\n" +
                "2. policy 2 ");
        ScoreTablePolicy sp = null;
        switch (input.nextInt()) {
            case 1:
                sp = new ScoreTablePolicy1();
                break;
            case 2:
                sp = new ScoreTablePolicy2();
                break;
        }
        System.out.println("Choose score-table-policy by its number:\n" +
                "1. Heuristic 1\n" +
                "2. Simple 2 ");
        String  gp = null;
        switch (input.nextInt()) {
            case 1:
                gp = "Heuristic";
                break;
            case 2:
                gp = "Simple";
                break;
        }
        rep.addNewLeague(name, new HashMap<>(), new HashMap<>(), sp, new SimpleGamesAssigningPolicy(), year);
        System.out.println("A new league was defined with no games and teams, yet.");
    }


    /**
     * UC 9.2
     */
    public void addSeasonToLeague() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert League name");
        String name = input.nextLine();
        League league = searcher.getLeagueByLeagueName(name);
        System.out.println("Insert first season's year");
        String year = input.nextLine();

        rep.setSeasonToLeague(league, year, new HashMap<>(), new HashMap<>());
        System.out.println("A new season was added with no games and teams, yet.");
    }

    /**
     * UC 9.3
     */
    public void nominateReferee() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert referee's first name");
        String firstName = input.nextLine();
        System.out.println("Insert referee's last name");
        String lastName = input.nextLine();
        System.out.println("Insert referee's training: \n" +
                "    VAR\n" +
                "    MAIN\n" +
                "    ASSISTANT\n");
        EReferee role = EReferee.valueOf(input.nextLine());
        rep.nominateReferee(firstName, lastName, role);
        System.out.println("A new referee was nominated");
    }

    /**
     * UC 9.3
     */
    public void removeReferee() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert referee's username name");
        Referee ref = (Referee) searcher.getUserByUserName(input.nextLine());
        rep.removeReferee(ref);
        System.out.println("Referee " + ref.getfName() + " " + ref.getlName() + " was removed");
    }

    /**
     * UC 9.4
     */
    public void assignReferees() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert the main referee's username name");
        Referee mainRef = (Referee) searcher.getUserByUserName(input.nextLine());
        System.out.println("Insert the side referee's username name");
        Referee side1Ref = (Referee) searcher.getUserByUserName(input.nextLine());
        System.out.println("Insert the side referee's username name");
        Referee side2Ref = (Referee) searcher.getUserByUserName(input.nextLine());

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

        try {
            rep.assignReferees(mainRef, side1Ref, side2Ref, game);
        } catch (Exception e) {
            System.out.println("Something went wrong...");
        }
    }

    /**
     * UC 9.5
     */
    //TODO: find it!

    /**
     * UC 9.6
     */
    public void SetGamesAssigningPolicy() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose score-table-policy by its number:\n" +
                "1. Heuristic 1\n" +
                "2. Simple 2 ");
        GamesAssigningPolicy gp = null;
        switch (input.nextInt()) {
            case 1:
                gp = new HeuristicGamesAssigningPolicy();
                break;
            case 2:
                gp = new SimpleGamesAssigningPolicy();
                break;
        }
        rep.SetGamesAssigningPolicy("Simple");
        System.out.println("Policy was modified successfully");
    }

    /**
     * UC 9.7
     */
    public void activateGamesAssigning() {
        rep.activateGamesAssigning();
        System.out.println("Policy was executed successfully");
    }

    /**
     * UC 9.8
     */
    public void setTeamBudgetControlRules() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert a threshold ( 0<x<1 )");
        double threshold = input.nextDouble();

        rep.setTeamBudgetControlRules(t, season, threshold,new TeamBudget(t,season));
        System.out.println("threshold was modified successfully.");
    }

    /**
     * UC 9.9
     */
    public void setAssociationBudgetTotoIntakes() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert the Toto's intakes");
        double intakes = input.nextDouble();
        rep.setAssociationBudgetTutuIntakes(intakes);
    }
}
