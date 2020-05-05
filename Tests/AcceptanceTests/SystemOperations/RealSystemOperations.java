package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.UserDetails;
import Model.*;

public class RealSystemOperations implements ISystemOperationsBridge{
    private final Model model;
    private static int randomNumberForUser = 0;
    private static int getRandomNumberForTeam = 0;

    public RealSystemOperations() {
        this.model = new Model();
    }

    @Override
    public UserDetails getNewRegisteredUserForTest() {
        UserDetails newRandomUser = new UserDetails("UserName"+randomNumberForUser+"","UserPassword"+randomNumberForUser,"UserFirstName"+randomNumberForUser,"UserLastName"+randomNumberForUser);
        randomNumberForUser++;
        model.signIn(newRandomUser.userName,newRandomUser.password,newRandomUser.firstName,newRandomUser.LastName);
        return newRandomUser;
    }

    @Override
    public boolean login(String username, String pw) {
        return model.login(username,pw);
    }

    @Override
    public boolean register(String userName, String password, String firstName, String lastName) {
        return model.signIn(userName,password,firstName,lastName);
    }


}
