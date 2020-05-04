package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.UserDetails;

public class RealSystemOperations implements ISystemOperationsBridge{


    @Override
    public UserDetails getNewRegisteredUserForTest() {
        return null;
    }

    @Override
    public boolean login(String username, String pw) {
        return false;
    }

    @Override
    public boolean register(String userName, String password, String firstName, String lastName) {
        return false;
    }
}
