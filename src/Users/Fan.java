package Users;
import java.util.ArrayList;
import java.util.List;
import System.*;

public class Fan extends Guest implements IFan {

    private String userName;
    private String fName;
    private String lName;
    private EStatus status;
    private List<String> searchHistory;

    public Fan(String UID, String fName, String lName) {
        this.userName = UID;
        this.fName = fName;
        this.lName = lName;
        this.searchHistory = new ArrayList<>();
        this.status = EStatus.ONLINE;
    }

    public void logout() { //useCase 3.1
        this.status = EStatus.OFFLINE;
    }

    public String viewProfile() { //useCase 3.6
        return toString();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public void setStatus(EStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  "My name is " + fName +
                " " + lName ;
    }

    public void subscribePersonalPage(APageEditor pageEditor){//useCase 3.2
        pageEditor.register(this);
    }

    public void removeRegisterFromPersonalPage(APageEditor pageEditor){//useCase 3.2
        pageEditor.delete(this);
    }

    // TODO: 11/04/2020 Ask Alon&Amit about team page
    public void subscribeTeamPersonalPage() {//useCase 3.2


    }

    public void update(APageEditor pageEditor,String feed){}


    public void subscribeGamesNotifications() {//useCase 3.3
        // TODO: 11/04/2020 ask yarin
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