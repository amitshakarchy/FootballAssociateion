package Users;

public abstract class AUser implements IUser {


    //region Fields
    int UID;
    String fName;
    String lName;

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

    }

    @Override
    public void viewProfile() { //useCase 3.6

    }

    //endregion


}
