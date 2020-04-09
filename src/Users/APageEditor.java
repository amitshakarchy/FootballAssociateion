package Users;
import java.util.ArrayList;
import java.util.List;

public abstract class APageEditor implements IPageEditor {

    private String myFirstName;
    private String myLastName;
    private Enum role;
    private List<String> myFeed;

    public APageEditor(String myFirstName, String myLastName, Enum role) {
        this.myFirstName = myFirstName;
        this.myLastName = myLastName;
        this.role = role;
        this.myFeed = new ArrayList<>();
    }

    public Enum getRole() {
        return role;
    }

    public void setRole(Enum role) {
        this.role = role;
    }

    public void addFeedToMyPage(String feed){ //useCase 4.2
        this.myFeed.add(feed);
    }

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

    @Override
    public String viewMyPersonalPage() {
        return "Welcome to my page! "+
                "My Name is: '" + myFirstName + '\'' +
                " " + myLastName + '\'' +
                ". My role is " + role +
                ", My Feed : " + myFeed ;

    }

}
