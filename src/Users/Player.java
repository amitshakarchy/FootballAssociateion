package Users;
import AssociationAssets.AdditionalInfo;

import java.util.Date;

public class Player extends AUser {
    private Date bDate;
    private EPlayerRole role;
    private PlayerPageEditor myPage;
    private AdditionalInfo myAdditionalInfo;

    /**
     * @param UID - Unique user ID
     * @param fName - First name of the player
     * @param lName -Last name of the player
     * @param bDate - Date of birth of the player
     * @param role - The role of the player. It could be:
     *             GoalKeeper/Defender/Forward/Midfielder.
     *
     * When a player is created, a personal page is created for him.
     */
    public Player(String UID, String fName, String lName, Date bDate, EPlayerRole role) {
        super(UID, fName, lName);
        this.bDate = bDate;
        this.role = role;
        this.myPage = new PlayerPageEditor(fName,lName,role,bDate);
        this.myAdditionalInfo = null;
    }


    /**
     * @param UID - Unique user ID
     * @param fName - First name of the player
     * @param lName -Last name of the player
     * @param bDate - Date of birth of the player
     * @param role - The role of the player. It could be:
     *             GoalKeeper/Defender/Forward/Midfielder.
     * @param myAdditionalInfo - Additional Info about player like team , manager..
     *
     * When a player is created, a personal page is created for him.
     */
    public Player(AdditionalInfo myAdditionalInfo, String UID, String fName, String lName, Date bDate, EPlayerRole role) {
        super(UID, fName, lName);
        this.bDate = bDate;
        this.role = role;
        this.myPage = new PlayerPageEditor(fName,lName,role,bDate);
        this.myAdditionalInfo = null;
        this.myAdditionalInfo = myAdditionalInfo;
    }


    /**
     * Players can upload content to their personal page.
     * The player uploads verbal content and that content goes up
     * to his personal page along with the current date.
     *
     * # use case 4.2
     *
     * @param feed verbal content
     */
    public void addFeedToMyPage(String feed){
        this.myPage.addFeedToMyPage(feed);
    }

    /**
     * Players can remove content from their personal page.
     * # use case 4.2
     * @param publishDate Content publication date
     * @param feed Verbal content
     */
    public void addFeedToMyPage(Date publishDate, String feed){
        this.myPage.removeFeedFromMyPage(publishDate,feed);
    }

    public Date getbDate() {
        return bDate;
    }

    public EPlayerRole getRole() {
        return role;
    }

    /**
     * Update the player's role.
     * The player's role is also updated on his personal page.
     * @param role The role of the player. It could be:
     *            GoalKeeper/Defender/Forward/Midfielder.
     */
    public void setRole(EPlayerRole role) {
        this.role = role;
        this.myPage.setRole(role);
    }

    public APageEditor getMyPage() {
        return myPage;
    }

    public AdditionalInfo getMyAdditionalInfo() {
        return myAdditionalInfo;
    }

    public void setMyAdditionalInfo(AdditionalInfo myAdditionalInfo) {
        this.myAdditionalInfo = myAdditionalInfo;
    }
}
