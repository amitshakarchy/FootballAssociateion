package Users;
import AssociationAssets.AdditionalInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *A coach is a person who guides an athlete or group of athletes,
 * advises them and manages their actions as they prepare for a sporting
 * competition, and while they are actually participating.
 * The coach is responsible for transferring training before the games.
 */
public class Coach extends AUser {

    ETraining training;
    ECoachRole role;
    CoachPageEditor myPage;
    List<AdditionalInfo> myAdditionalInfo;

    /**
     * @param userName - Unique user name
     * @param fName - First name of the coach
     * @param lName - Last name of the coach
     * @param training - Coach type of training. It could be:
     *                 CDiploma, UEFAA, UEFAB, UEFAPro
     * @param role - The role of the coach. It could be:
     *             GoalkeeperCoach, HeadCoach, AssistantCoach, YouthCoach
     * When a coach is created, a personal page is created for him.
     */
    public Coach(String userName, String fName, String lName, ETraining training, ECoachRole role) {
        super(userName, fName, lName);
        this.training = training;
        this.role = role;
        this.myPage = new CoachPageEditor(fName,lName,role,training);
        this.myAdditionalInfo = new ArrayList<>();
    }

    public List<AdditionalInfo> getMyAdditionalInfo() {
        return myAdditionalInfo;
    }

    /**
     * The position of the coaches varies according to the team and the season
     * @param additionalInfo - This section contains details about the team manager,
     *                      team, team owner, team players, team coach and season.
     */
    public void addAdditionalInfo(AdditionalInfo additionalInfo){
        if(additionalInfo != null){
            this.myAdditionalInfo.add(additionalInfo);
        }

    }


    /**
     * Coaches can upload content to their personal page.
     * The coach uploads verbal content and that content goes up
     * to his personal page along with the current date.
     *
     * # use case 5.2
     *
     * @param feed verbal content
     */
    public void addFeedToMyPage(String feed){
        if(feed != null) {
            this.myPage.addFeedToMyPage(feed);
        }
    }

    /**
     * Coaches can remove content from their personal page.
     * # use case 5.2
     * @param feed Verbal content
     */
    public void removeFeedFromMyPage(String feed){
        this.myPage.removeFeedFromMyPage(feed);
    }

    public APageEditor getMyPage() {
        return myPage;
    }

    public ETraining getTraining() {
        return training;
    }

    /**
     * Coach can change his type of training.
     * The training also update in his personal page.
     * @param training - Coach type of training. It could be:
     *                  CDiploma, UEFAA, UEFAB, UEFAPro
     */
    public void setTraining(ETraining training) {
        if( training != null){
                this.training = training;
                this.myPage.setTraining(training);
        }
    }

    public ECoachRole getRole() {
        return role;
    }

    /**
     * Update the coach's role.
     * The coach's role is also updated on his personal page.
     * use case #5.1
     * @param role The role of the coach. It could be:
     *             GoalkeeperCoach, HeadCoach, AssistantCoach, YouthCoach
     */
    public void setRole(ECoachRole role) {
        if(role != null) {
            this.role = role;
            this.myPage.setRole(role);
        }
    }

    /**
     * Coach can change his personal page.
     * # use case 5.1
     * @param myPage - Personal page.
     */
    public void setMyPage(CoachPageEditor myPage) {
        if(myPage != null){
            this.myPage = myPage;
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

    /**
     * With this function you can view coach information.
     * Provides a brief description of the actor,
     * his full name, his training and his role.
     * # use case 2.4 - You can view details about the coaches through
     * this function as well as through the personal pages of the coaches.
     */
    @Override
    public String viewProfile() {
        return super.viewProfile() + ", I am a football coach, " +
                ", My training is " + this.training +
                ", My role is " + this.role;
    }

}
