package Users;
import AssociationAssets.AdditionalInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * A footballer or a football player is an athlete who plays football, as an amateur or as a professional.
 * This system is about professional football players.
 */
public class Player extends CanBeOwner {
    private Date bDate;
    private EPlayerRole role;
    private PlayerPageEditor myPage;
    private List<AdditionalInfo> myAdditionalInfo;



    /**
     * @param userName - Unique user name
     * @param fName - First name of the player
     * @param lName -Last name of the player
     * @param bDate - Date of birth of the player
     * @param role - The role of the player. It could be:
     *             GoalKeeper/Defender/Forward/Midfielder.
     *
     * When a player is created, a personal page is created for him.
     * Each player has one personal page.
     */
    public Player(String userName, String fName, String lName, Date bDate, EPlayerRole role) {
        super(userName, fName, lName);
        this.bDate = bDate;
        this.role = role;
        this.myPage = new PlayerPageEditor(fName,lName,role,bDate);
        this.myAdditionalInfo = new ArrayList<>();
    }

    /**
     * Players can upload content to their personal page.
     * The player uploads verbal content and that content goes up
     * to his personal page.
     *
     * # use case 4.2
     *
     * @param feed verbal content
     */
    public void addFeedToMyPage(String feed){
        if(feed != null){
            this.myPage.addFeedToMyPage(feed);
        }
    }

    /**
     * Players can remove content from their personal page.
     * # use case 4.2
     * @param feed Verbal content
     */
    public void removeFeedFromMyPage(String feed){
        if(feed != null) {
            this.myPage.removeFeedFromMyPage(feed);
        }
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
     * # use case 4.1
     * @param role The role of the player. It could be:
     *            GoalKeeper/Defender/Forward/Midfielder.
     */
    public void setRole(EPlayerRole role) {
        if(role != null){
            this.role = role;
            this.myPage.setRole(role);
        }
    }

    public APageEditor getMyPage() {
        return myPage;
    }

    /**
     * # use case 4.1
     * @param myPage - Personal page.
     */
    public void setMyPage(PlayerPageEditor myPage) {
        if(myPage != null){
            this.myPage = myPage;
        }
    }

    public List<AdditionalInfo> getMyAdditionalInfo() {
        return myAdditionalInfo;
    }

    /**
     The team the player plays in,the team composition the player plays in,
     and their managers may change according to the season.
     * @param additionalInfo - This section contains details about the team manager,
     *                      team, team owner, team players, team coach and season.
     */
    public void addAdditionalInfo(AdditionalInfo additionalInfo){
        if(additionalInfo != null){
            this.myAdditionalInfo.add(additionalInfo);
        }

    }


    @Override
    public void setfName(String fName) {
        if(fName != null) {
            super.setfName(fName);
            this.myPage.setMyFisrtName(fName);
        }
    }

    @Override
    public void setlName(String lName) {
        if(lName!= null) {
            super.setlName(lName);
            this.myPage.setMyLastName(lName);
        }
    }

    public void setbDate(Date bDate) {
        this.bDate = bDate;
    }

    /**
     * With this function you can view player information.
     * Provides a brief description of the actor,
     * his full name, date of birth and his role.
     * # use case 2.4 - You can view details about the players through
     * this function as well as through the personal pages of the players.
     */
    @Override
    public String viewProfile() {
        return super.viewProfile()+ " , I am a football player, " +
                " I was born on" + bDate +
                ", I play as a " + role ;
    }

}
