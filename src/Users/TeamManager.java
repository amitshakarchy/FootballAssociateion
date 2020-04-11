package Users;

public class TeamManager extends AUser {

    public TeamManager(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    public void manageTeam(){//useCase 7.1

        // Improvise :)
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
