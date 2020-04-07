package Users;

public class Coach  extends AUser {

    ETraining training;
    ECoachRole role;
    PageEditor myPage;

    public Coach(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }
}
