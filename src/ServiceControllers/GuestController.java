package ServiceControllers;

import Users.*;

import java.util.Scanner;

public class GuestController {

    Guest guest;

    GuestController(){
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

       //TODO 15.04.2020 - Talk with Yarin. She has to fix those methods.
    }

    /**
     * UC 2.5
     */
    public void search(){
        Scanner input = new Scanner(System.in);
        System.out.println("type here what you are looking for");
        String toSearch= input.nextLine();
        guest.search(toSearch);

        //TODO 15.04.2020 - Talk with Yarin. She has to fix those methods.
    }


}
