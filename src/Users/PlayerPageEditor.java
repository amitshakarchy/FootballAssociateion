package Users;

import java.util.Date;

public class PlayerPageEditor extends APageEditor {
    Date bDay;

    public PlayerPageEditor(String myFirstName, String myLastName, Enum role, Date bDate) {
        super(myFirstName, myLastName, role);
        this.bDay = bDate;
    }

    public Date getbDay() {
        return bDay;
    }
}
