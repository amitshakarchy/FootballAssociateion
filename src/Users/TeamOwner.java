package Users;

public class TeamOwner extends AUser {


    public TeamOwner(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    @Override
    public String viewProfile() {
        return null;
    }

    @Override
    public EStatus getStatus() {
        return null;
    }

    @Override
    public void setStatus(EStatus status) {

    }
}
