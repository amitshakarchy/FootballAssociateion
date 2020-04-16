package System;

import AssociationAssets.*;
import DB.*;
import Users.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static org.junit.Assert.*;

public class SearchTest {
    FootballSystem footballSystem;
    Search search;
    Coach coach;
    Team team;
    League league;
    League league2;
    Season season;
    Game game;
    Referee ref1,ref2,ref3;
    Field field;
    Player player;
    @Before
    public void setUp(){
        footballSystem= FootballSystem.getInstance();
        search = new Search();
        coach = new Coach("coachTest","coachFname", "coachLname", ETraining.CDiploma, ECoachRole.AssistantCoach);
        team = new Team(123,"teamTest",null, null,null, null);
        league = new League("leagueTest");
        league2 = new League("leagueTest2");
        season = new Season("2020");
        field = new Field("fieldTest","Beer-Sheva",1000);
        Team hostTeam = new Team(456,"hostTest",season,field,null,null);
        ref1 = new Referee("ref1","refTest1","refTest1", EReferee.MAIN);
        ref2=new Referee("ref2","refTest2","refTest2",EReferee.ASSISTANT);
        ref3 =new Referee("ref3","refTest3","refTest3",EReferee.ASSISTANT);
        try { game =new Game(new Date(),null,field,hostTeam,team,ref1,ref2,ref3,season,league);
        } catch (Exception e) { e.printStackTrace(); }
        player = new Player("playerTest","playerFname", "playerLname",null,EPlayerRole.GoalKeeper);
        //footballSystem.addFanHashMap(coach); //TODO: Tair: add addFan(Fan) function to FootballSystem
        footballSystem.addTeamToDB(team);
        search.getTeamDB().addTeam(team,team.getName());
        search.getLeagueDB().addLeague(league,league.getLeagueName());
        search.getSeasonDB().addSeason(season,"2020");
        search.getGameDB().addGame(game,game.getGID());
        search.getFieldDB().addField(field,field.getName());
    }

    //TODO: Tair: add addFan(Fan) function to FootballSystem
    @Test
    public void getUserByUserName() {
         assertEquals(search.getUserByUserName("coachTest"), coach);
    }

    //Todo: Tair: add team to DB by team name instead of team id
    @Test
    public void getTeamByTeamName() {
        assertSame(search.getTeamByTeamName("teamTest"),team);
    }

    @Test
    public void getLeagueByLeagueName() {
        assertSame(search.getLeagueByLeagueName(league.getLeagueName()),league);
    }

    @Test
    public void getSeasonByYear() {
        assertSame(search.getSeasonByYear("2020"),season);
    }

    @Test
    public void getGameByGameID() {
       assertSame(search.getGameByGameID(1),game);
    }

    @Test
    public void getFieldByFieldName() {
        assertSame(search.getFieldByFieldName("fieldTest"),field);
    }

    //TODO: Amit&Alon: Add viewProfile() in League
    @Test
    public void getAllLeaguesProfile() {
        HashMap<String,String>testHashMap = new HashMap<>();
     //   testHashMap.put(league.getLeagueName(),league.viewProfile());
        Iterator it = testHashMap.entrySet().iterator();
        Map.Entry entryTest = (Map.Entry) it.next();
        while (it.hasNext()) {
            for (Map.Entry<String, String> entry : search.getAllLeaguesProfile().entrySet()) {
                assertSame(entryTest, entry);
                entryTest = (Map.Entry) it.next();
            }
        }
    }

    //TODO: Amit&Alon: Add viewProfile() in Team
    @Test
    public void getAllTeamsProfile() {
        HashMap<String,String> testHashMap = new HashMap<>();
     //   testHashMap.put(team.getName(),team.viewProfile());
        Iterator it = testHashMap.entrySet().iterator();
        Map.Entry entryTest = (Map.Entry) it.next();
        while (it.hasNext()) {
            for (Map.Entry<String, String> entry : search.getAllTeamsProfile().entrySet()) {
                assertSame(entryTest, entry);
                entryTest = (Map.Entry) it.next();
            }
        }
    }

    // Todo: Tair: add getAllReferees() function to System that return HashMap<String, Referee>
    @Test
    public void getAllRefereesProfile() {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put(ref1.getUserName(),ref1.viewProfile());
        Iterator it = testHashMap.entrySet().iterator();
        Map.Entry entryTest = (Map.Entry) it.next();
        while (it.hasNext()) {
            for (Map.Entry<String, String> entry : search.getAllRefereesProfile().entrySet()) {
                assertSame(entryTest, entry);
                entryTest = (Map.Entry) it.next();
            }
        }
    }

    // Todo: Tair: add getAllCoaches() function to System that return HashMap<String, Coach>
    @Test
    public void getAllCoachesProfile() {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put(coach.getUserName(),coach.viewProfile());
        Iterator it = testHashMap.entrySet().iterator();
        Map.Entry entryTest = (Map.Entry) it.next();
        while (it.hasNext()) {
            for (Map.Entry<String, String> entry : search.getAllCoachesProfile().entrySet()) {
                assertSame(entryTest, entry);
                entryTest = (Map.Entry) it.next();
            }
        }
    }

    // Todo: Tair: add getAllPlayers() function to System that return HashMap<String, player>
    @Test
    public void getAllPlayersProfile() {
        HashMap<String,String> testHashMap = new HashMap<>();
        testHashMap.put(player.getUserName(),player.viewProfile());
        Iterator it = testHashMap.entrySet().iterator();
        Map.Entry entryTest = (Map.Entry) it.next();
        while (it.hasNext()) {
            for (Map.Entry<String, String> entry : search.getAllPlayersProfile().entrySet()) {
                assertSame(entryTest, entry);
                entryTest = (Map.Entry) it.next();
            }
        }
    }

    @Test
    public void setLeagueDB() {
        LeagueDB leagueDBTest = new LeagueDB();
        search.setLeagueDB(leagueDBTest);
        assertSame(search.getLeagueDB(), leagueDBTest);
    }

    @Test
    public void getTeamDB() {
        assertSame(search.getTeamDB().getAllTeams().get(team.getTID()), team);
    }

    @Test
    public void getLeagueDB() {
        assertSame(search.getLeagueDB().getAllLeagues().get(league.getLeagueName()), league);
    }

    @Test
    public void getSeasonDB() {
        assertSame(search.getSeasonDB().getAllSeasons().get(season.getYear()), season);
    }

    @Test
    public void getGameDB() {
        assertSame(search.getGameDB().getAllGames().get(game.getGID()), game);
    }

    @Test
    public void getFieldDB() {
        assertSame(search.getFieldDB().getAllFields().get(field.getName()), field);
    }
}