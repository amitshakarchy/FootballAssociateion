package System;

import AssociationAssets.*;
import DB.*;
import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Users.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class SearchTest {
    //<editor-fold desc="Fields">
    FootballSystem footballSystem;
    GamesAssigningPolicy gamePolicy;
    Search search;
    Coach coach;
    Team team;
    Team hostTeam;
    League league;
    League league2;
    Season season;
    Game game;
    Game gameTest;
    Referee ref1,ref2,ref3;
    Field field;
    Player player;
    Fan fan;
    TeamOwner teamOwner;
    TeamManager teamManager;
    SystemManager systemManager;
    RepresentativeFootballAssociation representative;
    //</editor-fold>
    @Before
    public void setUp() throws Exception {
        footballSystem= FootballSystem.getInstance();
        gamePolicy = new SimpleGamesAssigningPolicy();
        search = new Search();
        field = new Field("fieldTest","Beer-Sheva",1000);
        team = new Team(123,"teamTest",new Season("2019"), field,null, null);
        league = new League("leagueTest");
        league2 = new League("leagueTest2");
        season = new Season("2020");
        hostTeam = new Team(456,"hostTest",season,field,null,null);
        footballSystem.signIn("ref1","672", "refTest1","crefTest1");
        ref1=(Referee)footballSystem.creatingReferee("ref1","refTest1","refTest1", EReferee.MAIN);
        ref2=new Referee("ref2","refTest2","refTest2",EReferee.ASSISTANT);
        ref3 =new Referee("ref3","refTest3","refTest3",EReferee.ASSISTANT);
        game = new Game(new Date(), null, field, hostTeam, team, ref1, ref2, ref3, season, league);
        gameTest = new Game(null, null, new Field("field", "city1", 30), team, hostTeam, ref1, ref2, ref3, season, league);
        //teamOwner
        footballSystem.signIn("teamOwner", "390124","tOwnerFname","tOnerLname");
        teamOwner = (TeamOwner)footballSystem.creatingTeamOwner("teamOwner","tOwnerFname","tOnerLname");
        //teamManager
        footballSystem.signIn("TMUname","67103","TMFname","TMLname");
        teamManager=(TeamManager)footballSystem.creatingTeamManager("TMUname","TMFname","TMLname");
        //systemManager
        footballSystem.signIn("SMenagerUname","678912","SMenagerFname","SMenagerLname");
       // systemManager= (SystemManager)footballSystem.creatingSystemManager("SMenagerUname","SMenagerFname","SMenagerLname");
       //representative
      footballSystem.signIn("repreUname","5689","repreFname","repreLname");
      representative=(RepresentativeFootballAssociation)footballSystem.creatingRepresentativeFootballAssociation("repreUname","repreFname","repreLname",gamePolicy);
       //player
        footballSystem.signIn("playerUName","67267", "playerFname","playerLname");
        player = (Player)footballSystem.creatingPlayer("playerUName","playerFname","playerLname",null,EPlayerRole.GoalKeeper);
        //coach
        footballSystem.signIn("coachTest","94672", "coachFname","coachLname");
        coach=(Coach) footballSystem.creatingCoach("coachName","coachFname","coachLname",ETraining.CDiploma,ECoachRole.AssistantCoach);
        //fan
        fan= footballSystem.signIn("testFan","123456","FanFname","FanLname");
        footballSystem.addTeamToDB(team);
        search.getTeamDB().addTeam(team,team.getName());
        search.getLeagueDB().addLeague(league,league.getLeagueName());
        search.getSeasonDB().addSeason(season,"2020");
        search.getGameDB().addGame(game,game.getGID());
        search.getFieldDB().addField(field,field.getName());
    }

    @Test
    public void getUserByUserName() {
        try {
           setUp();
           assertSame(search.getUserByUserName(ref1.getUserName()), ref1);
           assertSame((Coach) search.getUserByUserName(coach.getUserName()),coach);
           assertSame(search.getUserByUserName(player.getUserName()),player);
           assertSame(search.getUserByUserName(fan.getUserName()),fan);
//           assertSame(search.getUserByUserName(teamOwner.getUserName()),teamOwner);
//           assertSame(search.getUserByUserName(teamManager.getUserName()),teamManager);
//           assertSame(search.getUserByUserName(representative.getUserName()),representative);
//           assertSame(search.getUserByUserName(systemManager.getUserName()),systemManager);
       }catch (Exception e){
        }
    }

    @Test
    public void getTeamByTeamName() {
            assertSame(search.getTeamByTeamName("teamTest"),team);
    }

    @Test
    public void getLeagueByLeagueName() {
        try {
            setUp();
            assertSame(search.getLeagueByLeagueName(league.getLeagueName()), league);
        }catch(Exception e){
        }
    }

    @Test
    public void getSeasonByYear() {
        try {
            setUp();
            assertSame(search.getSeasonByYear("2020"), season);
        }catch (Exception e){

        }
    }

    @Test
    public void getGameByGameID() {
        try {
            setUp();
            search.getGameDB().addGame(gameTest, gameTest.getGID());
            assertSame(search.getGameByGameID(gameTest.getGID()), gameTest);
        }catch (Exception e){

        }
    }

    @Test
    public void getFieldByFieldName() {
        try {
            setUp();
            assertSame(search.getFieldByFieldName("fieldTest"), field);
        }catch (Exception e){
        }
    }

    @Test
    public void getAllLeaguesProfile() {
        try {
            setUp();
            HashMap<String, String> testHashMap = new HashMap<>();
            testHashMap.put(league.getLeagueName(), league.viewProfile());
            Iterator it = testHashMap.entrySet().iterator();
            Map.Entry entryTest = (Map.Entry) it.next();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : search.getAllLeaguesProfile().entrySet()) {
                    assertSame(entryTest, entry);
                    entryTest = (Map.Entry) it.next();
                }
            }
        }catch (Exception e){
        }
    }

    @Test
    public void getAllTeamsProfile() {
        try {
            setUp();
            HashMap<String, String> testHashMap = new HashMap<>();
            testHashMap.put(team.getName(), team.viewProfile());
            Iterator it = testHashMap.entrySet().iterator();
            Map.Entry entryTest = (Map.Entry) it.next();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : search.getAllTeamsProfile().entrySet()) {
                    assertSame(entryTest, entry);
                    entryTest = (Map.Entry) it.next();
                }
            }
        }catch (Exception e){
        }
    }

    @Test
    public void getAllRefereesProfile() {
        try {
            setUp();
            HashMap<String, String> testHashMap = new HashMap<>();
            testHashMap.put(ref1.getUserName(), ref1.viewProfile());
            Iterator it = testHashMap.entrySet().iterator();
            Map.Entry entryTest = (Map.Entry) it.next();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : search.getAllRefereesProfile().entrySet()) {
                    assertSame(entryTest, entry);
                    entryTest = (Map.Entry) it.next();
                }
            }
        }catch (Exception e){
        }
    }

    @Test
    public void getAllCoachesProfile() {
        try {
            setUp();
            HashMap<String, String> testHashMap = new HashMap<>();
            testHashMap.put(coach.getUserName(), coach.viewProfile());
            Iterator it = testHashMap.entrySet().iterator();
            Map.Entry entryTest = (Map.Entry) it.next();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : search.getAllCoachesProfile().entrySet()) {
                    assertSame(entryTest, entry);
                    entryTest = (Map.Entry) it.next();
                }
            }
        }catch (Exception e){
        }
    }

    @Test
    public void getAllPlayersProfile() {
        try {
            setUp();
            HashMap<String, String> testHashMap = new HashMap<>();
            testHashMap.put(player.getUserName(), player.viewProfile());
            Iterator it = testHashMap.entrySet().iterator();
            Map.Entry entryTest = (Map.Entry) it.next();
            while (it.hasNext()) {
                for (Map.Entry<String, String> entry : search.getAllPlayersProfile().entrySet()) {
                    assertSame(entryTest, entry);
                    entryTest = (Map.Entry) it.next();
                }
            }
        }catch (Exception e){
        }
    }

    @Test
    public void setLeagueDB() {
        try {
            setUp();
            LeagueDB leagueDBTest = new LeagueDB();
            search.setLeagueDB(leagueDBTest);
            assertSame(search.getLeagueDB(), leagueDBTest);
        }catch (Exception e){
        }
    }

    @Test
    public void setTeamDB() {
        try {
            setUp();

            TeamDB teamDBTest = new TeamDB();
            search.setTeamDB(teamDBTest);
            assertSame(search.getTeamDB(), teamDBTest);
        }catch (Exception e){
        }
    }

    @Test
    public void setSeasonDB() {
        try {
            setUp();

            SeasonDB seasonDBTest = new SeasonDB();
            search.setSeasonDB(seasonDBTest);
            assertSame(search.getSeasonDB(), seasonDBTest);
        }catch (Exception e) {
        }
    }

    @Test
    public void getTeamDB() {
        try {
            setUp();
            assertSame(search.getTeamDB().getAllTeams().get(team.getName()), team);
        }catch (Exception e){
        }
    }

    @Test
    public void getLeagueDB() {
        try {
            setUp();
            assertSame(search.getLeagueDB().getAllLeagues().get(league.getLeagueName()), league);
        }catch (Exception e){
        }
    }

    @Test
    public void getSeasonDB() {
        try {
            setUp();
            assertSame(search.getSeasonDB().getAllSeasons().get(season.getYear()), season);
        }catch (Exception e){
        }
    }

    @Test
    public void getGameDB() {
        try {
            setUp();
            assertSame(search.getGameDB().getAllGames().get(game.getGID()), game);
        }catch (Exception e){
        }
    }

    @Test
    public void getFieldDB() {
        try {
            setUp();
            assertSame(search.getFieldDB().getAllFields().get(field.getName()), field);
        }catch (Exception e){
        }
    }
}