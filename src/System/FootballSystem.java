package System;

import Security.SecuritySystem;
import Users.*;

import java.util.*;

/**
 * this class is Singleton for the Football System.
 */
public class FootballSystem {

    SecuritySystem securitySystem = new SecuritySystem();
    List<Guest> guestList = new LinkedList<>();
    Map<String, User> usersHashMap = new HashMap<>();
    Map<String,String> usersIDHashMap = new HashMap<>();

    /**
     * Create an instance of the class at the time of class loading
     */
    private static final FootballSystem instance = new FootballSystem();

    /**
     * private constructor to prevent others from instantiating this class
     */
    private FootballSystem() {

    }

    /**
     * Provide a global point of access to the instance
     */
    public static FootballSystem getInstance() {
        return instance;
    }

    public User signIn(String userName, String userID, String password, String firstName,
                       String lastName) {
        // check if this user name already exits
        if (usersHashMap.containsKey(userName)) {
            System.out.println("This user name is already exist.");
            return null;
        }
        // input check
        else if (userName == null || userName.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            System.out.println("please insert valid inputs");
            return null;
        }
        securitySystem.addNewUser(userName, password);
        User user = new User(userName,userID);
        this.usersHashMap.put(userName, user);
        this.usersIDHashMap.put(userID,userName);
        user.addRole(ERoleType.Fan, new Fan(userName, firstName, lastName));
        return user;
    } // useCase 2.2


    public Role creatingRepresentativeFootballAssociation(String userName, String firstName, String lastName) {
        Role role = new representativeFootballAssociation(userName, firstName, lastName);
        usersHashMap.get(userName).addRole(ERoleType.representativeFootballAssociation,
                role);
        return role;
    }

    public Role creatingReferee(String userName, String firstName, String lastName, EReferee training) {
        Role role = new Referee(userName, firstName, lastName, training);
        usersHashMap.get(userName).addRole(ERoleType.Referee, role);
        return role;
    }

    public Role creatingCoach(String userName, String firstName, String lastName, ETraining training,
                              ECoachRole eCoachRole) {
        Role role = new Coach(userName, firstName, lastName, training, eCoachRole);
        usersHashMap.get(userName).addRole(ERoleType.Coach, role);
        return role;
    }

    public Role creatingTeamOwner(String userName, String firstName, String lastName) {
        Role role = new TeamOwner(userName, firstName, lastName);
        usersHashMap.get(userName).addRole(ERoleType.TeamOwner, role);
        return role;
    }

    public Role creatingTeamManager(String userName, String firstName, String lastName) {
        Role role = new TeamManager(userName, firstName, lastName);
        usersHashMap.get(userName).addRole(ERoleType.TeamManager, role);
        return role;
    }

    public Role creatingPlayer(String userName, String firstName, String lastName
            , Date bDate, EPlayerRole playerRole) {
        Role role = new Player(userName, firstName, lastName, bDate, playerRole);
        usersHashMap.get(userName).addRole(ERoleType.Player, role);
        return role;
    }

    /**
     * this function login a user to the system.
     * the function checks if the password and the user name are correct using the security system.
     *
     * @param userName
     * @param password
     * @return User with the user name entered, or null if the user doesnt exist.
     */
    public User login(String userName, String password) {
        if (securitySystem.checkPasswordForLogIn(userName, password)) {
            if (usersHashMap.containsKey(userName)) {
                return usersHashMap.get(userName);
            }
        }
        System.out.println("user name or password incorrect");
        return null;
    }  // useCase 2.3

    public void removeUser(String userName) {
        this.usersIDHashMap.remove(this.usersHashMap.get(userName).getUserID());
        this.usersHashMap.remove(userName);
        this.securitySystem.removeUser(userName);
    }

    public User getUserByUserName(String userName) {
        if (this.usersHashMap.containsKey(userName)) {
            return this.usersHashMap.get(userName);
        }
        return null;
    }

    public boolean existUserByID(String id){
        return usersIDHashMap.containsKey(id);
    }

    public User getUserByUserID(String id){
        return this.usersHashMap.get(this.usersIDHashMap.get(id));
    }
}
