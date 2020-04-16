package Users;
import java.util.ArrayList;
import java.util.List;
import System.Logger;

/**
 * A personal page is a page with official content about the player / coach.
 * The player / coach may upload current content to his or her personal page.
 *
 * @ Written by Yuval Ben Eliezer
 */
public abstract class APageEditor implements IPageEditor {

    private String myFirstName;
    private String myLastName;
    private Enum role;
    private List<String> myFeed;
    private List<Fan> observers;

    /**
     * A personal page is a page with official content about the player / coach.
     *
     * @param myFirstName - his first name.
     * @param myLastName  - his last name.
     * @param role        - his role in the team.
     */
    public APageEditor(String myFirstName, String myLastName, Enum role) {
        this.myFirstName = myFirstName;
        this.myLastName = myLastName;
        this.role = role;
        this.myFeed = new ArrayList<>();
        this.observers = new ArrayList<>();
        Logger.getInstance().addActionToLogger("A new personal page created, user: "+ myFirstName +" "+myLastName);

    }

    public Enum getRole() {
        return role;
    }

    /**
     * A player / coach can replace their role.
     *
     * @param role - his role in the team.
     */
    public void setRole(Enum role) {
        if(role != null) {
            this.role = role;
        }
    }

    /**
     * A player / coach can upload content to their personal page.
     *
     * @param feed - The content the player/coach want to upload
     */
    public void addFeedToMyPage(String feed) {
        if(feed != null) {
            this.myFeed.add(feed);
            notifyObserver(feed);
        }
    }

    /**
     * A player / coach can delete content that
     * they upload to their personal page
     *
     * @param feed - The content the player/coach want to delete
     */
    public void removeFeedFromMyPage(String feed) {
        try {
            if(feed != null) {
                this.myFeed.remove(feed);
            }

        } catch (Exception e) {
            System.out.println("There is no content like this in your feed");
        }
    }

    public String getMyFisrtName() {
        return myFirstName;
    }


    public void setMyFisrtName(String myFirstName) {
        if(myFirstName != null) {
            this.myFirstName = myFirstName;
        }
    }

    public String getMyLastName() {
        return myLastName;
    }


    public void setMyLastName(String myLastName) {
        if(myLastName != null) {
            this.myLastName = myLastName;
        }
    }


    public List<String> getMyFeed() {
        return myFeed;
    }

    public void setMyFeed(List<String> myFeed) {
        if(myFeed!= null) {
            this.myFeed = myFeed;
        }
    }

    /**
     * This feature return the content about the coach/player that is on his personal
     * page and the content that the coach/player uploads to his site.
     *
     * @return String with the content
     */
    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! " +
                "My Name is: '" + myFirstName + '\'' +
                " " + myLastName + '\'' +
                ". My role is " + role +
                ", My Feed : " + myFeed;

    }


    /**
     * This function allows you to register for updates to the player's/coach's personal page
     * @param observer - The fan who wants to sign up for updates
     */
    public void register(Fan observer){
        if(observer != null) {
            this.observers.add(observer);
        }
    }

    /**
     * With this function you can stop receiving updates from the player's/coach's personal page
     * @param observer- The fan who wants to stop receiving updates
     */
    public void delete(Fan observer){
        if(observer != null) {
            this.observers.remove(observer);
        }
    }

    public List<Fan> getObservers() {
        return observers;
    }

    /**
     * With this function, we send updates for anyone who wants to receive
     *  updates from the personal page
     * @param feed - New feed
     */
    public void notifyObserver(String feed){
        if(feed != null) {
            if (this.observers.size() > 0) {
                for (Fan fan :
                        this.observers) {
                    fan.update(this, feed);
                }
            }
        }
    }
}

