package Users;

import java.util.Date;

public class Player extends AUser {
    Date bDate;
    EPlayerRole role;
    PageEditor myPage;

    public Player(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }
}
