package Users;

import java.util.Date;

public class Coach extends AUser {

    ETraining training;
    ECoachRole role;
    APageEditor myPage;

    /**
     *
     * @param UID - Unique user ID
     * @param fName - First name of the coach
     * @param lName - Last name of the coach
     * @param training - Coach type of training. It could be:
     *                 CDiploma, UEFAA, UEFAB, UEFAPro
     * @param role - The role of the coach. It could be:
     *             GoalkeeperCoach, HeadCoach, AssistantCoach, YouthCoach
     * When a coach is created, a personal page is created for him.
     */
    public Coach(String UID, String fName, String lName, ETraining training, ECoachRole role) {
        super(UID, fName, lName);
        this.training = training;
        this.role = role;
        this.myPage = new CoachPageEditor(fName,lName,role,training);
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
        this.myPage.addFeedToMyPage(feed);
    }

    /**
     * Coaches can remove content from their personal page.
     * # use case 5.2
     * @param publishDate Content publication date
     * @param feed Verbal content
     */
    public void addFeedToMyPage(Date publishDate, String feed){
        this.myPage.removeFeedFromMyPage(publishDate,feed);
    }

    public APageEditor getMyPage() {
        return myPage;
    }

    public ETraining getTraining() {
        return training;
    }

    public void setTraining(ETraining training) {
        this.training = training;
    }

    public ECoachRole getRole() {
        return role;
    }

    /**
     * Update the coach's role.
     * The coach's role is also updated on his personal page.
     * @param role The role of the coach. It could be:
     *             GoalkeeperCoach, HeadCoach, AssistantCoach, YouthCoach
     */
    public void setRole(ECoachRole role) {
        this.role = role;
        this.myPage.setRole(role);
    }

}
