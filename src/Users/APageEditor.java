package Users;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class APageEditor implements IPageEditor {

    private String myFirstName;
    private String myLastName;
    private Enum role;
    private List<Pair<Date,String>> myFeed;

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
        Date today = new Date();
        Pair<Date,String> newFeed = new Pair<>(today,feed);
        this.myFeed.add(newFeed);
    }

    public void removeFeedFromMyPage(Date publishDate, String feed) {
        Pair<Date,String> newFeed = new Pair<>(publishDate,feed);
        try{
            this.myFeed.remove(newFeed);

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


    public List<Pair<Date, String>> getMyFeed() {
        return myFeed;
    }

    public void setMyFeed(List<Pair<Date, String>> myFeed) {
        this.myFeed = myFeed;
    }


}
