package Users;
import java.util.ArrayList;
import java.util.List;

/**
 * A personal page is a page with official content about the player / coach.
 * The player / coach may upload current content to his or her personal page.
 */
public abstract class APageEditor implements IPageEditor {

    private String myFirstName;
    private String myLastName;
    private Enum role;
    private List<String> myFeed;

    /**
     * A personal page is a page with official content about the player / coach.
     * @param myFirstName - his first name.
     * @param myLastName - his last name.
     * @param role - his role in the team.
     */
    public APageEditor(String myFirstName, String myLastName, Enum role) {
        this.myFirstName = myFirstName;
        this.myLastName = myLastName;
        this.role = role;
        this.myFeed = new ArrayList<>();
    }

    public Enum getRole() {
        return role;
    }

    /**
     * A player / coach can replace their role.
     * @param role - his role in the team.
     */
    public void setRole(Enum role) {
        this.role = role;
    }

    /**
     *A player / coach can upload content to their personal page.
     * @param feed - The content the player/coach want to upload
     */
    public void addFeedToMyPage(String feed){
        this.myFeed.add(feed);
    }

    /**
     * A player / coach can delete content that
     * they upload to their personal page
     * @param feed - The content the player/coach want to delete
     */
    public void removeFeedFromMyPage(String feed) {
        try{
            this.myFeed.remove(feed);

        }
        catch (Exception e){
            System.out.println("There is no content like this in your feed");
        }
    }

    public String getMyFisrtName() {
        return myFirstName;
    }


    public void setMyFisrtName(String myFirstName) {
        this.myFirstName = myFirstName;
    }

    public String getMyLastName() {
        return myLastName;
    }


    public void setMyLastName(String myLastName) {
        this.myLastName = myLastName;
    }


    public List<String> getMyFeed() {
        return myFeed;
    }

    public void setMyFeed(List<String> myFeed) {
        this.myFeed = myFeed;
    }

    /**
     *This feature return the content about the coach/player that is on his personal
     * page and the content that the coach/player uploads to his site.
     * @return String with the content
     */
    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! "+
                "My Name is: '" + myFirstName + '\'' +
                " " + myLastName + '\'' +
                ". My role is " + role +
                ", My Feed : " + myFeed ;

    }

}
