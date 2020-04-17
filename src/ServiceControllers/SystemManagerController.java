package ServiceControllers;

import AssociationAssets.*;
import RecommendationSystem.ComputaionalModel;
import Users.*;
import System.*;
import javafx.util.Pair;

import java.text.DateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

public class SystemManagerController {


    SystemManager systemManager;
    Search searcher;

    public SystemManagerController(String username, String firstName, String lastName) {
        this.systemManager = new SystemManager(username, firstName, lastName);
        searcher = new Search();
    }


    /**
     * UC 8.1
     */
    // Not done yet

    /**
     * UC 8.2
     */
    //Not done yet

    /**
     * UC 8.2
     */
    public void viewComplainBox() {
        System.out.println(systemManager.getComplains());
    }

    /**
     * UC 8.3
     */
    public void responseOnComplain() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose a complaint to response to by its number");
        int i = 0;
        for (Pair<String, Fan> complaint : systemManager.getComplains()) {
            System.out.println(i + ". " + complaint.getKey());
            i++;
        }
        int index = input.nextInt();
        System.out.println("Type your response here.");
        String response = input.nextLine();

        systemManager.responseOnComplain(response, systemManager.getComplains().get(index));
        System.out.println("Response was submitted.");
    }

    /**
     * UC 8.4
     */
    public void viewSystemLog() {
        for (String log : systemManager.getLogInformation()) {
            System.out.println(log);
        }
    }

    /**
     * UC 8.5
     */
    public void activateRecommendationSystemModel() {
        Scanner input = new Scanner(System.in);
        System.out.println("Choose a model to use by its number:\n" +
                "1. Model 1\n" +
                "2. Model 2");
        switch (input.nextInt()) {
            case 1:
                systemManager.activateRecommendationSystemModel(new ComputaionalModel());
                break;
            case 2:
                systemManager.activateRecommendationSystemModel(new ComputaionalModel());
                break;
        }
        System.out.println("Recommendation system was activated.");
    }


}
