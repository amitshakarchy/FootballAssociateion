package Users;

public abstract class AUser implements IUser {


    //region Fields
    String userName;
    String fName;
    String lName;
    //EStatus status;

    public AUser(String userName, String fName, String lName) {
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
      //  this.status = EStatus.ONLINE;
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
    //    this.status = EStatus.OFFLINE;
    }
//
//    @Override
//    public String viewProfile() { //useCase 3.6
//        return toString();
//    }

    //endregion


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        if(fName != null){
            this.fName = fName;
        }
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        if(lName != null){
            this.lName = lName;
        }
    }

//    public EStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(EStatus status) {
//        this.status = status;
//    }

    @Override
    public String toString() {
        return  "My name is " + fName +
                " " + lName ;
    }
}