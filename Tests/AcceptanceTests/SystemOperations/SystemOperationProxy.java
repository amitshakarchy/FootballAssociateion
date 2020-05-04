package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.UserDetails;

public class SystemOperationProxy implements  ISystemOperationsBridge{
    ISystemOperationsBridge real;

    public SystemOperationProxy( ) {
        this.real = null;
    }

    @Override
    public UserDetails getNewRegisteredUserForTest() {
        if (real != null){
            return real.getNewRegisteredUserForTest();
        }
        return null;
    }

    @Override
    public boolean login(String username, String pw) {
        if (real != null){
            return real.login(username, pw) ;
        }
        return false;
    }

    @Override
    public boolean register(String userName, String password, String firstName, String lastName) {
        if (real != null){
            return real.register(userName, password,firstName,lastName) ;
        }
        return true;
    }


}
