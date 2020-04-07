package Users;

import com.sun.org.apache.xerces.internal.util.Status;
import jdk.net.SocketFlow;

public abstract class AUser implements IUser {


    //region Fields
    String UID;
    String fName;
    String lName;
    EStatus status;

    public AUser(String UID, String fName, String lName) {
        this.UID = UID;
        this.fName = fName;
        this.lName = lName;
        this.status = EStatus.ONLINE;
    }
//TODO: decide how to represent and use the fields :
    // String username
    // String password
    // In order to do so, contact ___ who is on charge on authentication and security.
    //endregion

    //region Methods

    @Override
    public void setUserSettings() {

    }

    @Override
    public void logout() { //useCase 3.1
        this.status = EStatus.OFFLINE;
    }

    @Override
    public void viewProfile() { //useCase 3.6

    }

    //endregion


    public String getUID() {
        return UID;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }
}
