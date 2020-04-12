package Users;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FanTest {

    @Test
    void logout() {
        Fan fan = new Fan("1","1","1");
        assertEquals(fan.getStatus(),EStatus.ONLINE);
        fan.logout();
        assertEquals(fan.getStatus(),EStatus.OFFLINE);
    }

    @Test
    void setUserName() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        assertEquals(fan.getUserName(),"newFan");
        fan.setUserName("yosi");
        assertEquals(fan.getUserName(), "yosi");
        fan.setUserName(null);
        assertEquals(fan.getUserName(), "yosi");
    }

    @Test
    void setfName() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        assertEquals(fan.getfName(),"yossi");
        fan.setfName("yosi");
        assertEquals(fan.getfName(), "yosi");
        fan.setfName(null);
        assertEquals(fan.getfName(), "yosi");
    }

    @Test
    void setlName() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        assertEquals(fan.getlName(),"cohen");
        fan.setlName("yosi");
        assertEquals(fan.getlName(), "yosi");
        fan.setlName(null);
        assertEquals(fan.getlName(), "yosi");
    }

    @Test
    void setStatus() {
        Fan fan = new Fan("1","1","1");
        assertEquals(fan.getStatus(),EStatus.ONLINE);
        fan.setStatus(EStatus.OFFLINE);
        assertEquals(fan.getStatus(),EStatus.OFFLINE);
    }






    @Test
    void subscribePersonalPage() {
    }

    @Test
    void removeRegisterFromPersonalPage() {
    }

    @Test
    void subscribeTeamPersonalPage() {
    }

    @Test
    void update() {
    }

    @Test
    void subscribeGamesNotifications() {
    }

    @Test
    void submitComplain() {
    }

    @Test
    void getResponseForComplain() {
    }




    @Test
    void getSearchHistory() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        fan.search("newSearch");
        assertEquals(fan.getSearchHistory().size(),1 );
        assertEquals(fan.getSearchHistory().get(0),"newSearch" );

    }


    @Test
    void setSearchHistory() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        fan.search("newSearch");
        assertEquals(fan.getSearchHistory().size(),1 );
        assertEquals(fan.getSearchHistory().get(0),"newSearch" );
        fan.setSearchHistory(null);
        assertEquals(fan.getSearchHistory().size(),1 );
        assertEquals(fan.getSearchHistory().get(0),"newSearch" );
    }

    @Test
    void search() {
        Fan fan = new Fan("newFan", "yossi","cohen");
        fan.search("newSearch");
        assertEquals(fan.getSearchHistory().size(),1 );
        assertEquals(fan.getSearchHistory().get(0),"newSearch" );
        fan.search(null);
        assertEquals(fan.getSearchHistory().size(),1 );
        assertEquals(fan.getSearchHistory().get(0),"newSearch" );
    }
}