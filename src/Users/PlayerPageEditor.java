package Users;

import java.util.Date;

/**
 *Each player has a personal page that they can upload and edit.
 *
 * @ Written by Yuval Ben Eliezer
 */
public class PlayerPageEditor extends APageEditor {
    Date bDay;

    /**
     *This personal page contains official content about the player.
     *To this page the player uploads updates as he chooses.
     * @param myFirstName - first name.
     * @param myLastName - last name.
     * @param role - The role of the player. It could be:
     *      *             GoalKeeper/Defender/Forward/Midfielder.
     * @param bDate -Date of birth of the player
     */
    public PlayerPageEditor(String myFirstName, String myLastName, Enum role, Date bDate) {
        super(myFirstName, myLastName, role);
        this.bDay = bDate;
    }

    public Date getBDay() {
        return bDay;
    }

    @Override
    public void addToMyPage(String feed) {

    }

    @Override
    public String getMyFisrtName() {
        return null;
    }

    /**
     *This feature return the content about the player that is on his personal
     * page and the content that the player uploads to his site.
     * @return String with the content
     */
    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! "+
                "My Name is: '" + getMyFisrtName() + '\'' +
                " " + super.getMyLastName() + '\'' +
                "I was born on " + this.getBDay()+
                ". My role is " + super.getRole() +
                ", My Feed : " + super.getMyFeed() ;
    }
}