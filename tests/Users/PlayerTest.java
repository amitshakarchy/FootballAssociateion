//package Users;
//import org.junit.jupiter.api.Test;
//import java.util.Date;
//import static org.junit.jupiter.api.Assertions.*;
//class PlayerTest {
//
//
//    @Test
//    void addFeedToMyPage() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        String feed = "this is my first post";
//        player.addFeedToMyPage(feed);
//        assertTrue(player.getMyPage().getMyFeed().size() == 1 && player.getMyPage().getMyFeed().get(0).equals(feed));
//        player.addFeedToMyPage(null);
//        assertTrue(player.getMyPage().getMyFeed().size() == 1 && player.getMyPage().getMyFeed().get(0).equals(feed));
//
//    }
//
//    @Test
//    void removeFeedFromMyPage() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        String feed = "this is my first post";
//        player.addFeedToMyPage(feed);
//        player.getMyPage().removeFeedFromMyPage(feed);
//        assertEquals(0, player.getMyPage().getMyFeed().size());
//        player.getMyPage().removeFeedFromMyPage(null);
//        assertEquals(0, player.getMyPage().getMyFeed().size());
//
//    }
//
//    @Test
//    void setRole() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        assertEquals(player.getRole(), EPlayerRole.GoalKeeper);
//        player.setRole(EPlayerRole.Defender);
//        assertEquals(player.getRole(), EPlayerRole.Defender);
//        player.setRole(null);
//        assertEquals(player.getRole(), EPlayerRole.Defender);
//
//    }
//
//    @Test
//    void setMyPage() {
//        Date bDay = new Date();
//        Player player = new Player("newPlayer", "yossi","cohen", bDay,EPlayerRole.GoalKeeper);
//        String feed = "this is my first post";
//        player.addFeedToMyPage(feed);
//        PlayerPageEditor newPage = new PlayerPageEditor("yossi","cohen",EPlayerRole.GoalKeeper,bDay);
//        player.setMyPage(newPage);
//        assertEquals(0, player.getMyPage().getMyFeed().size());
//        player.setMyPage(null);
//        assertEquals(0, player.getMyPage().getMyFeed().size());
//
//    }
//
//    @Test
//    void logout() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        assertEquals(player.status, EStatus.ONLINE);
//        player.logout();
//        assertEquals(player.status, EStatus.OFFLINE);
//    }
//
//
//    @Test
//    void setfName() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        assertEquals(player.fName,"yossi");
//        player.setfName("yosi");
//        assertEquals(player.fName, "yosi");
//        player.setfName(null);
//        assertEquals(player.fName, "yosi");
//
//    }
//
//    @Test
//    void setlName() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        assertEquals(player.lName,"cohen");
//        player.setlName("levi");
//        assertEquals(player.lName, "levi");
//        player.setlName(null);
//        assertEquals(player.lName, "levi");
//
//    }
//
//    @Test
//    void setStatus() {
//        Player player = new Player("newPlayer", "yossi","cohen", new Date(),EPlayerRole.GoalKeeper);
//        assertEquals(player.status, EStatus.ONLINE);
//        player.setStatus(EStatus.OFFLINE);
//        assertEquals(player.status, EStatus.OFFLINE);
//    }
//}