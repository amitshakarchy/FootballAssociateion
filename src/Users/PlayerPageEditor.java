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

    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! "+
                "My Name is: '" + super.getMyFisrtName() + '\'' +
                " " + super.getMyLastName() + '\'' +
                "I was born on " + this.getbDay()+
                ". My role is " + super.getRole() +
                ", My Feed : " + super.getMyFeed() ;
    }
}
