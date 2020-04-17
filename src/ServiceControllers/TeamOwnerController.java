package ServiceControllers;

import AssociationAssets.*;
import Users.*;
import System.*;

import java.text.DateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;


public class TeamOwnerController {

    TeamOwner owner;
    Search searcher;

    public TeamOwnerController(String username, String firstName, String lastName) {
        this.owner = new TeamOwner(username, firstName, lastName);
        searcher = new Search();
    }


    //region UC 6.1

    /**
     * UC 6.1
     */
    public void addPlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new player");
        String username = input.nextLine();
        System.out.println("Insert password for the new player");
        String password = input.nextLine();
        System.out.println("Insert player's first name");
        String firstName = input.nextLine();
        System.out.println("Insert player's last name");
        String lastName = input.nextLine();
        System.out.println("Insert player's birth date. format: dd-mm-yyyy");
        Date birthday = java.sql.Date.valueOf(input.nextLine());
        System.out.println("Insert player's Role: \n" +
                "    GoalKeeper\n" +
                "    Defender\n" +
                "    Forward\n" +
                "    Midfielder");
        EPlayerRole role = EPlayerRole.valueOf(input.nextLine());

        if (owner.addPlayer(t, season, username, password, firstName, lastName, birthday, role))
            System.out.println("Player was added successfully :) ");
        else System.out.println("Something went wrong... Try again");
    }

    /**
     * UC 6.1
     */
    public void removePlayer() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the player");
        String username = input.nextLine();

        owner.removePlayer(t, season, username);
        System.out.println("Player was removed successfully");
    }

    /**
     * UC 6.1
     */
    public void addCoach() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new coach");
        String username = input.nextLine();
        System.out.println("Insert password for the new coach");
        String password = input.nextLine();
        System.out.println("Insert coach's first name");
        String firstName = input.nextLine();
        System.out.println("Insert coach's last name");
        String lastName = input.nextLine();
        System.out.println("Insert coach's training: \n" +
                "CDiploma,\n" +
                "    UEFAA,\n" +
                "    UEFAB,\n" +
                "    UEFAPro");
        ETraining training = ETraining.valueOf(input.nextLine());
        System.out.println("Insert coach's role: \n    " +
                "GoalkeeperCoach,\n" +
                "    HeadCoach,\n" +
                "    AssistantCoach,\n" +
                "    YouthCoach");
        ECoachRole role = ECoachRole.valueOf(input.nextLine());

        if (owner.addCoach(t, season, username, password, firstName, lastName, training, role))
            System.out.println("coach was added successfully :) ");
        else System.out.println("Something went wrong... Try again");
    }

    /**
     * UC 6.1
     */
    public void removeCoach() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the coach");
        String username = input.nextLine();

        owner.removeCoach(t, season, username);
        System.out.println("Coach was removed successfully");
    }


    /**
     * UC 6.1
     */
    public void addTeamManager() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new team manager");
        String username = input.nextLine();
        System.out.println("Insert password for the new team manager");
        String password = input.nextLine();
        System.out.println("Insert team manager's first name");
        String firstName = input.nextLine();
        System.out.println("Insert team manager's last name");
        String lastName = input.nextLine();

        if (owner.addTeamManager(t, season, username, password, firstName, lastName))
            System.out.println("team manager was added successfully :) ");
        else System.out.println("Something went wrong... Try again");

    }



    /**
     * UC 6.1
     */
    public void addField() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert Field's name");
        String name = input.nextLine();
        System.out.println("Insert Field's city");
        String city = input.nextLine();
        System.out.println("Insert Field's capacity");
        int capacity = input.nextInt();

        if (owner.addField(t, season, name, city, capacity))
            System.out.println("coach was added successfully :) ");
        else System.out.println("Something went wrong... Try again");

    }

    /**
     * UC 6.1
     */
    public void removeField() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert field name");
        String fieldName = input.nextLine();

        owner.removeField(t, season, fieldName);
        System.out.println("Coach was removed successfully");
    }
    //endregion


    /**
     * UC 6.2
     */
    public void nominateTeamOwner() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new team owner");
        String username = input.nextLine();

        if (owner.nominateTeamOwner(t, season, username))
            System.out.println("New team owner was nominated!");
        else System.out.println("Something went wrong... Try again");
    }

    /**
     * UC 6.3
     */
    public void discardNominationForTeamOwner() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new team owner");
        String username = input.nextLine();

        owner.discardNominationForTeamOwner(t, season, username);
        System.out.println("New team owner was nominated!");
    }

    /**
     * UC 6.4
     */
    public void nominateTeamManager() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the new team manager");
        String username = input.nextLine();

        if (owner.nominateTeamManager(t, season, username))
            System.out.println("New team manager was nominated!");
        else System.out.println("Something went wrong... Try again");
    }


    /**
     * UC 6.5
     */
    public void removeTeamManager() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        System.out.println("Insert username for the coach");
        String username = input.nextLine();

        owner.removeTeamManager(t, season, username);
        System.out.println("Coach was removed successfully");
    }

    /**
     * UC 6.6
     */
    public void closeTeam(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert Team name");
        Team t = searcher.getTeamByTeamName(input.nextLine());
        System.out.println("Insert Season's year");
        Season season = searcher.getSeasonByYear(input.nextLine());
        owner.closeTeam(t,season);
        System.out.println("Team was closed.");
    }

    /**
     * UC 6.7
     */
    // Not done yet


    }

