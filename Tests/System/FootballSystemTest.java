package System;

import AssociationAssets.*;
import Budget.TeamBudget;
import Users.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FootballSystemTest {

    Field field;
    Team team1, team2;
    Season season;
    League league;
    Game game;
    TeamOwner teamOwner1,teamOwner2;
    TeamBudget teamBudget;
    Player player;
    Coach coach;
    TeamManager teamManager;
    Fan fan;

    @BeforeEach
    void setUp() {
        season = new Season("2020");
        FootballSystem.getInstance().addSeasonToDB(season);
        //field = new Field("blomfield","Tel Aviv",100);
        FootballSystem.getInstance().addFieldToDB(field);
        teamBudget = new TeamBudget(null,null,5);
        teamOwner1 = (TeamOwner)FootballSystem.getInstance().creatingTeamOwner("Tair233","Tair","Cohen");
        teamOwner2 = (TeamOwner)FootballSystem.getInstance().creatingTeamOwner("Tal12","Tal","Cohen");
        team1 = new Team(1,"Macabi-Tel-aviv",season,field,teamBudget, teamOwner1);
        team2 = new Team(2,"Beitar",season,field,teamBudget,teamOwner2);
        team1.addSeasonToTeam(season);
        team2.addSeasonToTeam(season);
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
        assertTrue(null != FootballSystem.getInstance().signIn("Tair","12","tair","cohen"));
        assertEquals(1,FootballSystem.getInstance().fansHashMap.size());
        FootballSystem.getInstance().removeUser("Tair");
        FootballSystem.getInstance().removeUser("lalala");
        assertEquals(0,FootballSystem.getInstance().fansHashMap.size());
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void getFanByUserName() {
        Fan fan = FootballSystem.getInstance().signIn("Tair","12","tair",
                "cohen");
        assertTrue(fan.equals(FootballSystem.getInstance().getFanByUserName("Tair")));
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void existFanByUserName() {
        Fan fan = FootballSystem.getInstance().signIn("Tair","12","tair","cohen");
        assertTrue(FootballSystem.getInstance().existFanByUserName("Tair"));
        assertTrue(!FootballSystem.getInstance().existFanByUserName("lala"));
        FootballSystem.getInstance().getFansHashMap().clear();
    }

    @Test
    void addTeamToDB() {
        assertEquals(0,FootballSystem.getInstance().getTeamDB().getAllTeams().size());
        FootballSystem.getInstance().addTeamToDB(team1);
        assertEquals(1,FootballSystem.getInstance().getTeamDB().getAllTeams().size());
        FootballSystem.getInstance().addTeamToDB(team2);
        assertEquals(2,FootballSystem.getInstance().getTeamDB().getAllTeams().size());
        FootballSystem.getInstance().addTeamToDB(team2);
        assertEquals(2,FootballSystem.getInstance().getTeamDB().getAllTeams().size());
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