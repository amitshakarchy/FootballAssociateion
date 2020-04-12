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
    Map<String,Fan> fansHashMap = new HashMap<>();

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

    public Fan signIn(String userName, String password, String firstName,
                       String lastName) {
        // check if this user name already exits
        if (fansHashMap.containsKey(userName)) {
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
        Fan fan = new Fan(userName,firstName,lastName);
        this.fansHashMap.put(userName, fan);
        return fan;
    } // useCase 2.2


    public Fan creatingRepresentativeFootballAssociation(String userName, String firstName, String lastName) {
        Fan fan = new representativeFootballAssociation(userName, firstName, lastName);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan creatingReferee(String userName, String firstName, String lastName, EReferee training) {
        Fan fan = new Referee(userName, firstName, lastName, training);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan creatingCoach(String userName, String firstName, String lastName, ETraining training,
                              ECoachRole eCoachRole) {
        Fan fan = new Coach(userName, firstName, lastName, training, eCoachRole);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan creatingTeamOwner(String userName, String firstName, String lastName) {
        Fan fan = new TeamOwner(userName, firstName, lastName);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan creatingTeamManager(String userName, String firstName, String lastName) {
        Fan fan = new TeamManager(userName, firstName, lastName);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan creatingPlayer(String userName, String firstName, String lastName
            , Date bDate, EPlayerRole playerRole) {
        Fan fan = new Player(userName, firstName, lastName, bDate, playerRole);
        this.fansHashMap.put(userName,fan);
        return fan;
    }

    public Fan login(String userName, String password) {
        if (securitySystem.checkPasswordForLogIn(userName, password)) {
            if (this.fansHashMap.containsKey(userName)) {
                return fansHashMap.get(userName);
            }
        }
        System.out.println("user name or password incorrect");
        return null;
    }  // useCase 2.3

    public void removeUser(String userName) {
        this.fansHashMap.remove(userName);
        this.securitySystem.removeUser(userName);
    }

    public Fan getFanByUserName(String userName) {
        if (this.fansHashMap.containsKey(userName)) {
            return this.fansHashMap.get(userName);
        }
        return null;
    }

    public boolean existFanByUserName(String userName){
        return this.fansHashMap.containsKey(userName);
    }

}
