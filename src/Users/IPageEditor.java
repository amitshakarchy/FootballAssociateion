package Users;
import java.util.List;

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
