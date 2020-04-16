package Users;

import AssociationAssets.Field;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import DB.LeagueDB;
import DB.TeamDB;
import org.junit.Before;
import org.junit.Test;
import System.Search;
import System.*;


import static org.junit.Assert.*;

public class GuestTest {
    Guest guest;
    Coach coach;
    Player player;
    Team team;
    League league;
    Season season;
   LeagueDB leagueDB;
   Search search;
   Field field;
   FootballSystem footballSystem = FootballSystem.getInstance();
    @Before
    public void setUp(){
        guest = new Guest();
        leagueDB = new LeagueDB();
        search= guest.getSearch();
        coach = new Coach("coachTest", "Dan", "Levi", ETraining.UEFAA,ECoachRole.AssistantCoach);
        player = new Player("playerTest","Eli","Dan",null,EPlayerRole.GoalKeeper);
        league = new League("champions");
        League league1 = new League("leagueTest");
        season = new Season("2020");
        field = new Field("fieldTest","city",10);
        team= new Team(1,"Barcelona" ,season, field, null, null);
        leagueDB.addLeague(league,"champions");
        leagueDB.addLeague(league1,"leagueTest");
        search.setLeagueDB(leagueDB);
       // footballSystem.addFan(coach); //Todo: Tair : add addFan(Fan) function to FootballSystem
    }

    @Test
    public void signInGuest() {
        Fan newUser= guest.signInGuest("GuestTest","1234567","Dani","Dan");
        assertNotNull(newUser);
        Fan userIsAlreadyExist= guest.signInGuest("GuestTest","1234567","Dani","Dan");
        assertNull(userIsAlreadyExist);
    }

    @Test
    public void logInGuest() {
        guest.signInGuest("GuestTest","1234567","Dani","Dan");
       Fan loginUser= guest.logInGuest("GuestTest","1234567");
       assertNotNull(loginUser);
       Fan loginUser1 =  guest.logInGuest("GuestTest","123456");
       assertNull(loginUser1);
    }

    //Todo: Tair : add addFan(Fan) function to FootballSystem
    //Todo:yarin: remove from comment getAllCoachesProfile in Search
    @Test
    public void searchByName() {
       assertEquals(guest.searchByName("coachTest"),search.getAllCoachesProfile().get("coachTest"));

    }

    @Test
    public void searchByCategory() {
        guest.searchByCategory("League");
    }

    @Test
    public void searchByKeyWord() {
    }
}