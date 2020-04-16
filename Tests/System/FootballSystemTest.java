package System;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FootballSystemTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getInstance() {
    }

    @Test
    void signIn() {
        assertEquals(null,FootballSystem.getInstance().signIn(null,"12","tair","cohen"));
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertEquals(null,FootballSystem.getInstance().signIn("tair",null,"tair","cohen"));
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertEquals(null,FootballSystem.getInstance().signIn("tair","12",null,"cohen"));
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertEquals(null,FootballSystem.getInstance().signIn("tair","12","tair",null));
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().signIn("tair12","12","tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        assertEquals(null,FootballSystem.getInstance().signIn("tair12","12","tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void creatingRepresentativeFootballAssociation() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingRepresentativeFootballAssociation("Tair",
                "tair","cohen",null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());

        assertTrue(null == FootballSystem.getInstance().creatingRepresentativeFootballAssociation("Tair",
                "tair","cohen",null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void creatingReferee() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingReferee("Tair",
                "tair","cohen",null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());

        assertTrue(null == FootballSystem.getInstance().creatingReferee("Tair",
                "tair","cohen",null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void creatingCoach() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingCoach("Tair",
                "tair","cohen",null,null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null == FootballSystem.getInstance().creatingCoach("Tair",
                "tair","cohen",null,null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }


    @Test
    void creatingTeamOwner() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingTeamOwner("Tair",
                "tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null == FootballSystem.getInstance().creatingTeamOwner("Tair",
                "tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void creatingTeamManager() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingTeamManager("Tair",
                "tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null == FootballSystem.getInstance().creatingTeamManager("Tair",
                "tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void creatingPlayer() {
        assertEquals(0,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null != FootballSystem.getInstance().creatingPlayer("Tair",
                "tair","cohen",null,null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        assertTrue(null == FootballSystem.getInstance().creatingPlayer("Tair",
                "tair","cohen",null,null));
        assertEquals(1,FootballSystem.getInstance().getFansHashMap().size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void login() {
        assertTrue(null != FootballSystem.getInstance().signIn("Tair","12","tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().fansHashMap.size());
        assertTrue(null != FootballSystem.getInstance().login("Tair","12"));
        assertTrue(null == FootballSystem.getInstance().login("Tair","11"));
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void removeUser() {

    }

    @Test
    void getFanByUserName() {
    }

    @Test
    void existFanByUserName() {
    }

    @Test
    void addTeamToDB() {
    }

    @Test
    void removeTeamFromDB() {
    }

    @Test
    void addLeagueToDB() {
    }

    @Test
    void removeLeagueFromDB() {
    }

    @Test
    void addFieldToDB() {
    }

    @Test
    void removeFieldFromDB() {
    }

    @Test
    void addSeasonToDB() {
    }

    @Test
    void removeSeasonFromDB() {
    }

    @Test
    void addGameToDB() {
    }

    @Test
    void removeGameFromDB() {
    }

    @Test
    void findPlayerAtTeamByUserName() {
    }

    @Test
    void findCoachAtTeamByUserName() {
    }
}