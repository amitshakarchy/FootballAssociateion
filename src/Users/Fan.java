package Users;
import java.util.ArrayList;
import java.util.List;

public class Fan extends AUser {

    private List<String> searchHistory;

    public Fan(String UID, String fName, String lName) {
        super(UID, fName, lName);
        searchHistory = new ArrayList<>();
    }

    public void subscribePlayerPersonalPage(APageEditor pageEditor) {//useCase 3.2

    }

    public void subscribeTeamPersonalPage() {//useCase 3.2


    }
    public void subscribeCoachPersonalPage() {//useCase 3.2


    }
    public void subscribeGamesNotifications() {//useCase 3.3


    }

    public void submitComplain(String complain) {//useCase 3.4

        // the way I see it, this method should receive a complain text (from the service layer)
        // and send it as an email to a system manage (you can get it's email from the FootballSystem itself).

    }

    public List<String> getSearchHistory() { //useCase 3.5
        return this.searchHistory;
    }

    /**
     * A fan can view his search history
     *
     * # use case 3.5
     * @return search history in order to display it on the screen.
     */
    public String viewSearchHistory(){ //useCase 3.5
        StringBuilder res = new StringBuilder();
        for (String aSearchHistory : this.searchHistory) {
            res.append(aSearchHistory);
        }
        return res.toString();
    }

    public void setSearchHistory(List<String> searchHistory) {
        this.searchHistory = searchHistory;
    }
}
