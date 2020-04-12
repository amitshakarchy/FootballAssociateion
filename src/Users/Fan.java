package Users;
import java.util.ArrayList;
import java.util.List;
import System.*;

/**
 * A fan of a sports club, national team, team or athlete,
 * is a fan of that club, group or athlete.
 */
public class Fan extends Guest implements IFan {

    private String userName;
    private String fName;
    private String lName;
    private EStatus status;
    private List<String> searchHistory;

    /**
     * @param userName - Unique user name
     * @param fName - First name of the fan
     * @param lName - Last name of the fan
     * When a fan is created his status is ONLINE
     */
    public Fan(String userName, String fName, String lName) {
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.searchHistory = new ArrayList<>();
        this.status = EStatus.ONLINE;
    }

    /**
     * When a fan leaves the system, a fan's status changes to offline
     *
     * # use case 3.1
     */
    public void logout() {
        this.status = EStatus.OFFLINE;
    }

    /**
     *  With this function you can view fan information.
     * @return - His full name, status and search history.
     */
    public String viewProfile() { //useCase 3.6
        return toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName!=null) {
            this.userName = userName;
        }
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        if(fName != null){
            this.fName = fName;
        }
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        if(lName != null){
            this.lName = lName;
        }
    }

    public EStatus getStatus() {
        return status;
    }

    /**
     * This function changes the player's status in the system
     * @param status - It could be:
     *      *             ONLINE , OFFLINE
     */
    public void setStatus(EStatus status) {
        if(status != null) {
            this.status = status;
        }
    }

    /**
     * With this function you can view fan information.
     * Provides a brief description of the player,
     * His full name, status and search history.
     *
     * # use case 3.6
     */
    @Override
    public String toString() {
        return "My name is" +
                ": " + fName + '\'' +
                " " + lName + '\'' +
                ", my status is" + status +
                ", my search History is " + searchHistory ;
    }

    public void subscribePersonalPage(APageEditor pageEditor){//useCase 3.2
        pageEditor.register(this);
    }

    public void removeRegisterFromPersonalPage(APageEditor pageEditor){//useCase 3.2
        pageEditor.delete(this);
    }

    public void subscribeTeamPersonalPage() {//useCase 3.2
        // TODO: 12/04/2020 Ask Alon&Amit about team page
    }

    public void update(APageEditor pageEditor,String feed){}


    public void subscribeGamesNotifications() {//useCase 3.3
        // TODO: 12/04/2020 yarin
    }

    /**
     * This method should receive a complain text (from the service layer)
     * and send it as an email to the system managers .
     * @param complain
     */
    public void submitComplain(String complain) {//useCase 3.4
        Complains.getInstance().addComplain(complain,this);
    }

    public void getResponseForComplain(SystemManager systemManager,String Complain,String response){}

    /**
     * A fan can view his search history
     *
     * # use case 3.5
     * @return search history.
     */
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
        if(searchHistory!= null) {
            this.searchHistory = searchHistory;
        }
    }

    /**
     * A fan can perform system searches
     * A fan search history is saved to the system
     * @param search - The content for which the fan wants information
     */
    @Override
    public void search(String search) {
        if (search != null) {
            super.search(search);
            this.searchHistory.add(search);
        }
    }

}