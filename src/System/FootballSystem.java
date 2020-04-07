package System;
import Security.SecuritySystem;
import Users.AUser;
import Users.Fan;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * this class responsible for the football system.
 */
public class FootballSystem {

    SecuritySystem securitySystem = new SecuritySystem();
    List<Guest> guestList = new LinkedList<>();
    Map<String,AUser> userHashMap = new HashMap<>();


    public void signIn(String id, String userName, String password,
                       String firstName,String lastName){
        if(id.length() != 9) {

        }
        else if(userName == null || userName.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty() ){

        }
        securitySystem.addNewUser(id,password);
    } // useCase 2.2

    public void login(String userName, String password){
        // TODO: 4/5/2020 give the authorization of user and not of guest
        if(securitySystem.checkPasswordForLogIn(userName,password)){
            // the guest can log in into the system
        }
        else{
            System.out.println("user name or password incorrect");
        }
    } // useCase 2.3

    public void removeUser(String userName){
        this.userHashMap.remove(userName);
    }
}
