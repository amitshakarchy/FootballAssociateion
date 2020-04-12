package Controllers;
import AssociationAssets.Field;
import System.FootballSystem;
import Users.*;
import java.util.Date;
import java.util.Random;

public class TeamOwnerController {


    // TODO: 4/11/2020 connect all data to additional info
    private static int userNameCounter = 0;

    public Role addPlayer(String userID, String firstName, String lastName,
                          Date birthday, EPlayerRole playerRole) {
        Role userRole = null;
        // if this user id exist in the system
        if (FootballSystem.getInstance().existUserByID(userID)) {
            User user = FootballSystem.getInstance().getUserByUserID(userID);
            // if this role exist in the user roles
            if (user.existRole(ERoleType.Player)) {
                userRole = user.getRoleByERoleType(ERoleType.Player);
                if (userRole != null) {
                    // update details
                    userRole.setfName(firstName);
                    userRole.setlName(lastName);
                    ((Player) userRole).setbDate(birthday);
                    ((Player) userRole).setRole(playerRole);
                }
            } else {
                // this user doesnt have player role, and we create new Player at the
                // football system
                FootballSystem.getInstance().creatingPlayer(userID, firstName, lastName, birthday, playerRole);
            }
        } else {
            // this user id doesnt exist in the system - we need to create new User
            User user = signInNewUser(userID, firstName, lastName);
            FootballSystem.getInstance().creatingPlayer(userID, firstName, lastName, birthday, playerRole);
            // TODO: 4/11/2020 add to additional info the player
            userRole = user.getRoleByERoleType(ERoleType.Player);
        }
        return userRole;
    }

    public Role addTeamManager(String userID, String firstName, String lastName) {
        Role userRole = null;
        // if this user id exist in the system
        if (FootballSystem.getInstance().existUserByID(userID)) {
            User user = FootballSystem.getInstance().getUserByUserID(userID);
            // if this role exist in the user roles
            if (user.existRole(ERoleType.TeamManager)) {
                // TODO: 4/12/2020 check that this user id is not a team manger / team
                // TODO: 4/12/2020 owner already!
                // TODO: 4/12/2020 add permissions to manger
                userRole = user.getRoleByERoleType(ERoleType.TeamManager);
                if (userRole != null) {
                    // update details
                    userRole.setfName(firstName);
                    userRole.setlName(lastName);
                }
            } else {
                // this user doesnt have player role, and we create new Player at the
                // football system
                FootballSystem.getInstance().creatingTeamManager(userID, firstName, lastName);
            }
        } else {
            // this user id doesnt exist in the system - we need to create new User
            User user = signInNewUser(userID, firstName, lastName);
            FootballSystem.getInstance().creatingTeamManager(userID, firstName, lastName);
            // TODO: 4/11/2020 add to additional info the player
            userRole = user.getRoleByERoleType(ERoleType.TeamManager);
        }
        return userRole;
    }

    public Role addCoach(String userID, String firstName, String lastName, ETraining training,
                         ECoachRole coachRole) {
        Role userRole = null;
        // if this user id exist in the system
        if (FootballSystem.getInstance().existUserByID(userID)) {
            User user = FootballSystem.getInstance().getUserByUserID(userID);
            // if this role exist in the user roles
            if (user.existRole(ERoleType.Coach)) {
                userRole = user.getRoleByERoleType(ERoleType.Coach);
                if (userRole != null) {
                    // update details
                    userRole.setfName(firstName);
                    userRole.setlName(lastName);
                    ((Coach) userRole).setTraining(training);
                    ((Coach) userRole).setRole(coachRole);
                }
            } else {
                // this user doesnt have player role, and we create new Player at the
                // football system
                FootballSystem.getInstance().creatingCoach(userID, firstName, lastName, training, coachRole);
            }
        } else {
            // this user id doesnt exist in the system - we need to create new User
            User user = signInNewUser(userID, firstName, lastName);
            FootballSystem.getInstance().creatingCoach(userID, firstName, lastName, training, coachRole);
            // TODO: 4/11/2020 add to additional info the player
            userRole = user.getRoleByERoleType(ERoleType.Coach);
        }
        return userRole;
    }

    public Field addField(String name, String city, int capacity) {
        Field field = new Field(name, city, capacity);
        // TODO: 4/11/2020 wait for alon to add to team fields!!
        //additionalInfo.getTeam().
        return field;
    }// use case 6.1

    public void removeTeamManagerByUserID(String userID) {
        // TODO: 4/12/2020 check if this team manger was the one that nominate this user id

    }

    public void removeCoachByUserID(String userID) {

    }

    public void removeFieldrByUserID(String userID) {

    }

    public void removePlayerByUserID(String userID) {

    }

    private User signInNewUser(String userID, String firstName, String lastName) {
        String password = String.valueOf(new Random().nextInt(100000000));
        User user = null;
        String uName;
        while (user == null) {
            uName = createNewUserName(firstName, lastName);
            // creating new user!!
            user = FootballSystem.getInstance().signIn(uName, userID, password,
                    firstName, lastName);
            // adding new role of Player to this user
        }
        return user;
    }

    private String createNewUserName(String firstName, String lastName) {
        userNameCounter++;
        return firstName + lastName + userNameCounter;
    }

    public void nominateTeamOwner(String userID, String firstName, String lastName) {

    }

    public void removeTeamOwner(String userID) {
        // TODO: 4/12/2020 check if this team manger was the one that nominate this user id
    }

    public void closeTeam(){

    }

    // TODO: 4/12/2020 budget of the team



}
