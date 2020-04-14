package Users;

import AssociationAssets.*;
import Budget.AssociationBudget;
import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy1;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Budget.TeamBudget;
import System.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class RepresentativeFootballAssociationTest {
    League league;
    Season season;
    RepresentativeFootballAssociation representative;
    SimpleGamesAssigningPolicy gamePolicy;
    ScoreTablePolicy1 scorePolicy;
    AssociationBudget associationBudget;
    Game game1;
    Game game2;
    Team team1, team2;
    Field field;
    Referee r1,r2,r3;
    HashMap <String,Game> games;
    HashMap <String,Team> teams;
    TeamBudget teamBudget;
    HashMap<String,TeamOwner> owners;
    HashMap<String,Coach> coaches;
    HashMap<String,TeamManager> managers;
    HashMap<String, Player>players;
    @Before
    public void set_up() throws Exception {
        league = new League("champions");
        season = new Season("2020");
        owners= new HashMap<>();
        coaches =  new HashMap<>();
        players = new HashMap<>();
        gamePolicy =new SimpleGamesAssigningPolicy() ;
        scorePolicy =  new ScoreTablePolicy1();
        associationBudget = new AssociationBudget();
        field= new Field("Teddi", "Beer Sheva", 800);
        r1= new Referee("1","a","a",EReferee.MAIN);
        r2= new Referee("2","a","a",EReferee.ASSISTANT);
        r3= new Referee("3","a","a",EReferee.ASSISTANT);
        // TODO: 4/14/2020 fix test
       // team1= new Team(1,"Barcelona" ,field, null, season, owners, coaches,managers,players);
       // team2= new Team(2,"Real Madrid" ,field, null, season, owners, coaches,managers,players);
        game1= new Game(new Date(10,10,2020),
                new Time(19,30,0),
                field,team1,team2,r1,r2,r3,season,
                league);
        game2= new Game(new Date(5,4,2020),
                new Time(20,00,0),
                field,team2,team1,r1,r3,r2,season,
                league);
        games = new HashMap<>();
        games.put("123",game1);
        games.put("113",game2);
        teams = new HashMap<>();
        teams.put(team1.getName(),team1);
        teams.put(team2.getName(),team2);
        teamBudget =  new TeamBudget(team1,season,10.5);
        representative = new RepresentativeFootballAssociation("representative","Dan","Levi",gamePolicy);
    }

    @Test
    public void addNewLeague() {
        representative.addNewLeague("leagueTest",games,teams,scorePolicy,gamePolicy,"2020");
        assertTrue(FootballSystem.getInstance().existLeagueByName("leagueTest"));
    }

    @Test
    public void setSeasonToLeague() {
        representative.setSeasonToLeague(league,"2019",games,teams);
        assertTrue(league.getSeasonBinders().containsKey("2019"));
    }

    @Test
    public void nominateReferee() {
        representative.nominateReferee("Dani","Mizrahi",EReferee.MAIN);
        assertTrue(FootballSystem.getInstance().existFanByUserName("DaniMizrahi1"));
    }

    @Test
    public void signInReferee() {
        String userName = representative.signInReferee("Alon","Mizrahi","1234567");
        assertEquals(userName,"AlonMizrahi1");
    }

    @Test
    public void removeReferee() {
        Referee referee = (Referee) FootballSystem.getInstance().creatingReferee("refTest","Dna","Levi",EReferee.ASSISTANT);
        representative.removeReferee(referee);
        assertFalse(FootballSystem.getInstance().existFanByUserName("refTest"));
    }

    @Test
    public void assignReferees() throws Exception {
        Referee ref1= new Referee("ref1","ref1","a",EReferee.MAIN);
        Referee  ref2= new Referee("ref2","ref2","a",EReferee.ASSISTANT);
        Referee ref3= new Referee("ref3","ref3","a",EReferee.ASSISTANT);
        representative.assignReferees(ref1,ref2,ref3,game1);
        assertEquals(game1.getMain().getfName(),"ref1");
        assertEquals(game1.getSide1().getfName(),"ref2");
        assertEquals(game1.getSide2().getfName(),"ref3");
    }
    /////////////////TODO
    @Test
    public void setGamesAssigningPolicy() {
        representative.SetGamesAssigningPolicy(gamePolicy);

    }
    /////////////////TODO
    @Test
    public void activateGamesAssigning() {
    }

    /////////////////TODO
    @Test
    public void setTeamBudgetControlRules() {
        representative.setTeamBudgetControlRules(team1,season,20.2);
        ///////??????????????????
    }

    ////////////////TODO!!
    @Test
    public void alertTeamExceedBudget() {
    }


    @Test
    public void setAssociationBudgetTutuIntakes() {
        representative.setAssociationBudgetTutuIntakes(10);
        assertEquals(associationBudget.getTutuIntakes(),10,0);
    }

    @Test
    public void setAssociationBudgetRegistrationFee() {
        representative.setAssociationBudgetRegistrationFee(10);
        assertEquals(associationBudget.getRegistrationFee(),10,0);

    }

    @Test
    public void setAssociationBudgetSalaries() {
        Fan user = new Fan("testUser","bla","bla");
        representative.setAssociationBudgetSalaries(user,1000.0);
        boolean bool = associationBudget.userSalaryIsExist(user);
        assertTrue(associationBudget.userSalaryIsExist(user));
    }

    /////////////////TODO
    @Test
    public void getTeamsExceedBudget(){

    }
}