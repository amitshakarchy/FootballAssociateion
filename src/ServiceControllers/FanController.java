package ServiceControllers;

import Users.*;
import System.*;

import java.util.List;
import java.util.Scanner;

public class FanController {

Fan fan;

    public FanController(Fan fan) {
        this.fan = fan;
    }

    /**
     * UC 3.1
     *
     */
    public void logOut(){ Scanner input = new Scanner(System.in);
    if(fan.getStatus()==EStatus.OFFLINE)
        System.out.println("Goodbye!");
    else System.out.println("Ho no! Something bad is going on in here");
}

    /**
     * UC 3.2
     */
    public void subscribePersonalPage(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert a player's username");

      //TODO 15/04/2020 I need some searching methods. I NEED them so much!
        String username = input.nextLine();
        FootballSystem s= FootballSystem.getInstance();
        s.findPlayerAtTeamByUserName(username);

        // subscribe...
    }

    /**
     * UC 3.3
     */
    public void subscribeGames(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert a date");
        //TODO 15/04/2020 I need some searching methods: search game by date/league/season

    }

    /**
     * UC 3.4
     */
    public void submitComplain(){
        Scanner input = new Scanner(System.in);
        System.out.println("Insert copmlain here");
        String complain= input.nextLine();
        while(complain.equals("")){
            System.out.println("Cannot send an empty message. Insert ypu complain here");
            complain= input.nextLine();
        }
        fan.submitComplain(complain);
        System.out.println("Complain was submitted successfully");
    }

    /**
     * UC 3.5
     */
    public void viewSearchHistory(){
        List<String> history=fan.getSearchHistory();
        for(int i=0;i<history.size();i++){
            System.out.println(history.get(i));
        }
    }

    /**
     * UC 3.6
     */
    public void viewDetails(){

        System.out.println(fan.toString());

    }

    /**
     * UC 3.6
     */
    public void editDetails(){
        Scanner input = new Scanner(System.in);
        System.out.println("insert first name");
        String firstName= input.nextLine();
        System.out.println("insert last name");
        String lastName= input.nextLine();

        fan.setfName(firstName);
        fan.setlName(lastName);

        if(fan.getfName().equals(firstName)&& fan.getlName().equals(lastName))
            System.out.println("Name was edited");
        else System.out.println("Oops! try again");
    }
}
