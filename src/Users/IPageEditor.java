package Users;

import java.util.Date;

public interface IPageEditor {

     void addFeedToMyPage(String feed);
     void removeFeedFromMyPage(Date publishDate, String feed);
}
