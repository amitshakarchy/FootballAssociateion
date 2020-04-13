package Users;
import java.util.List;
/**
 * This class is an interface with the APageEditor object
 * @ Written by Yuval Ben Eliezer
 */
public interface IPageEditor {
     void addFeedToMyPage(String feed);
     void removeFeedFromMyPage(String feed);
     Enum getRole();
     void setRole(Enum role);
     String getMyFisrtName();
     String getMyLastName();
     List<String> getMyFeed();
     String viewMyPersonalPage();


}
