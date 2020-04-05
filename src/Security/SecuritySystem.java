package Security;

import java.util.HashMap;
import java.util.Map;

/**
 * this class responsible for the security of the users.
 * the passwords encrypted and saved for each user.
 */
public class SecuritySystem {
    Map<String,String> usersHashMap = new HashMap<>();
    AESEncryption AES = new AESEncryption();
    final String secretKey = "ssshhhhhhhhhhh!!!!";


    /**
     * This function adding new user to the system.
     * the password has been saved with "0" at the start and the end for
     * encryption issues.
     * @param userName
     * @param password
     */
    public void addNewUser(String userName,String password){
        if(password == null){

        }
        if(usersHashMap.containsKey(userName)){
            System.out.println("This user id is already exist");
        }
        else{
            password = AES.encrypt(password,secretKey);
            usersHashMap.put(userName,password);
        }
    }

    /**
     * this function updates a new password for exiting user
     * the password has been saved with "0" at the start and the end for
     * encryption issues.
     * @param userName
     * @param password
     */
    public void updatePassword(String userName,String password){
        if(!usersHashMap.containsKey(userName)){
            System.out.println("This user id doesnt exits in the system");
        }
        else{
            password = AES.encrypt(password,secretKey);
            usersHashMap.put(userName,password);
        }
    }

    public boolean checkPasswordForLogIn(String userName,String password){
        if(!userName.contains(userName)){
            System.out.println("the user name is NOT EXISTS");
            return false;
        }
        if(AES.decrypt(usersHashMap.get(userName),secretKey).equals(password)) {
            // the user can log in
            return true;
        }
        //else..
        System.out.println("the password is wrong");
        return false;
    }



}
