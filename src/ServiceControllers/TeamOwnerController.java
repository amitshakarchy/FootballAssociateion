package ServiceControllers;

import AssociationAssets.Season;
import AssociationAssets.Team;
import Users.*;

import java.util.Date;
import java.util.Scanner;

public class TeamOwnerController {

    TeamOwner owner;

    public TeamOwnerController(String username, String firstName, String lastName) {
        this.owner = new TeamOwner(username,firstName,lastName);
    }


/**
 * UC 6.1
 */
public void addPlayer(Team team, Season season, String userName, String password, String firstName, String lastName, Date birthday, EPlayerRole ePlayerRole){
    Scanner input = new Scanner(System.in);
    System.out.println("Insert ");
}

}
