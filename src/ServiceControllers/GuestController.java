package ServiceControllers;

import Users.*;
import System.*;
import java.util.Scanner;
import AssociationAssets.*;

public class GuestController {

    Guest guest;
    Search searcher;
    GuestController(){
        searcher= new Search();
        guest= new Guest();
    }

    /**
     * UC 2.2
     */
    public void signIn() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert username");
        String username = input.nextLine();
        System.out.println("Insert password");
        String password = input.nextLine();
        System.out.println("Insert first Name");
        String firstName = input.nextLine();
        System.out.println("Insert last Name");
        String lastName = input.nextLine();

        Fan fan = guest.signInGuest(username, password, firstName, lastName);
        if (fan != null)
            System.out.println("Sign in completed successfully");
        else System.out.println("Wrong details.");
    }

    /**
     * UC 2.3
     */
    public Fan login() {
        Scanner input = new Scanner(System.in);
        System.out.println("Insert username");
        String username = input.nextLine();
        System.out.println("Insert password");
        String password = input.nextLine();
        Fan fan = guest.logInGuest(username, password);
        if (fan != null) {
            System.out.println("login completed successfully");
        } else System.out.println("Wrong details.");
        return fan;
    }



    /**
     * UC 2.4
     */
    public void viewInformation() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose Category (by its number):\n" +
                "1. Teams\n" +
                "2. Players\n" +
                "3. Coaches\n" +
                "4. Leagues\n" +
                "5. Seasons\n");
        int category=Integer.parseInt(input.nextLine());
        System.out.println("type here what you are looking for");
        String toSearch= input.nextLine();
        switch (category){

            case 1:
                System.out.println(searcher.getTeamByTeamName(toSearch).viewProfile());
                break;
            case 2:
                System.out.println(((Player)(searcher.getUserByUserName(toSearch))).viewProfile());
                break;
            case 3:
                System.out.println(((Coach)(searcher.getUserByUserName(toSearch))).viewProfile());
                break;
            case 4:
                System.out.println(searcher.getLeagueByLeagueName(toSearch).viewProfile());
                break;
            case 5:
                System.out.println(searcher.getSeasonByYear(toSearch).viewProfile());
                break;
        }

    }

    /**
     * UC 2.5
     */
    public void search(){
        Scanner input = new Scanner(System.in);
        System.out.println("what would you like to search for?\n" +
                "1. search by category\n" +
                "2. search by name\n" +
                "3. search By Key Word\n" );

        int category= Integer.parseInt(input.nextLine());
        System.out.println("type here what you are looking for");
        String toSearch= input.nextLine();
        switch (category) {
            case 1:
                System.out.println(guest.searchByCategory(toSearch));
                break;
            case 2:
                System.out.println(guest.searchByName(toSearch));
                break;
            case 3:
                System.out.println(guest.searchByKeyWord(toSearch));
                break;
        }

    }


}
