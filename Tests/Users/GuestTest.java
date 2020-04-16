package Users;

import AssociationAssets.Field;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import DB.LeagueDB;
import DB.SeasonDB;
import DB.TeamDB;
import org.junit.Before;
import org.junit.Test;
import System.Search;
import System.*;


import java.awt.geom.Area;

import static org.junit.Assert.*;

public class GuestTest {
    Guest guest;
    Coach coach;
    Player player;
    Team team;
    League league;
    Season season;
   LeagueDB leagueDB;
   TeamDB teamDB;
   SeasonDB seasonDB;
   Search search;
   Field field;
   Referee referee;
   FootballSystem footballSystem = FootballSystem.getInstance();
    @Before
    public void setUp(){
        guest = new Guest();
        leagueDB = new LeagueDB();
        teamDB = new TeamDB();
        seasonDB = new SeasonDB();
        search= guest.getSearch();
        coach = new Coach("coachTest", "Dan", "Levi", ETraining.UEFAA,ECoachRole.AssistantCoach);
        player = new Player("playerTest","Eli","Dan",null,EPlayerRole.GoalKeeper);
        league = new League("champions");
        League league1 = new League("leagueTest");
        referee = new Referee("refereeTest","refereeFname","refereeLname",EReferee.ASSISTANT);
        season = new Season("2020");
        field = new Field("fieldTest","city",10);
        team= new Team(1,"Barcelona" ,season, field, null, null);
        leagueDB.addLeague(league,league.getLeagueName());
        leagueDB.addLeague(league1,league1.getLeagueName());
        teamDB.addTeam(team,team.getName());
        seasonDB.addSeason(season,season.getYear());
        search.setLeagueDB(leagueDB);
        search.setTeamDB(teamDB);
        search.setSeasonDB(seasonDB);
        footballSystem.signIn(coach.getUserName(),"34567",coach.getfName(),coach.getlName());
        footballSystem.addCoach(coach); //Todo: Tair : add addCoach(coach) function to FootballSystem
        footballSystem.addReferee(referee); //Todo: Tair : add addReferee(referee) function to FootballSystem
        footballSystem.addPlayer(player); //Todo: Tair : add addPlayer(player) function to FootballSystem
    }

    @Test
    public void signInGuest() {
         guest.signInGuest("GuestTest","1234567","Dani","Dan");
        assertTrue( footballSystem.getFansHashMap().containsKey("GuestTest"));
    }

    @Test
    public void logInGuest() {
        guest.signInGuest("GuestTest","1234567","Dani","Dan");
       Fan loginUser= guest.logInGuest("GuestTest","1234567");
       assertNotNull(loginUser);
       Fan loginUser1 =  guest.logInGuest("GuestTest","123456");
       assertNull(loginUser1);
    }

    @Test
    public void viewInformationCoach(){
       assertEquals(guest.viewInformationCoach(coach.getUserName()),coach.viewProfile());
    }

    @Test
    public void viewInformationPlayer(){
        assertEquals(guest.viewInformationPlayer(player.getUserName()),player.viewProfile());

    }

    @Test
    public void viewInformationTeam(){
        assertEquals(guest.viewInformationTeam(team.getName()),team.viewProfile());
    }

    @Test
    public void viewInformationLeague(){
        assertEquals(guest.viewInformationLeague(league.getLeagueName()),league.viewProfile());

    }
    @Test
    public void viewInformationSeason(){
        assertEquals(guest.viewInformationSeason(season.getYear()),season.viewProfile());
    }

    //Todo: Tair : add addCoach(Coach) / addPlayer(Player) / addReferee(Referee) functions in FootballSystem
    @Test
    public void searchByName() {
        //searchInUsers
        assertEquals(guest.searchByName("coachTest"),search.getAllCoachesProfile().get("coachTest"));
        //searchInLeagues
        assertEquals(guest.searchByName(league.getLeagueName()),league.viewProfile());
        //searchInTeams
        assertNull(guest.searchByName(team.getName()),null);
    }

    @Test
    public void searchByCategory() {
        //case1:
        String equalLeague = league.getLeagueName()+" "+"League"+":"+"\n"+league.viewProfile();
       assertEquals(guest.searchByCategory("League"), equalLeague);

       //case2:
//       String equalTeam = team.getName()+":"+"\n"+ team.viewProfile();
//       assertEquals(guest.searchByCategory("Team"),equalTeam);
       assertNull((guest.searchByCategory("Team")),null);

        //TODO: Tair: add addReferee(referee) in footballSystem
        //case 3:
//        String equalReferee = referee.getUserName()+":"+"\n"+ referee.viewProfile();
//        assertEquals(guest.searchByCategory("Referee"),equalReferee);
         assertNull((guest.searchByCategory("Referee")),null);

        //TODO: Tair: add addPlayer(player) in footballSystem
        //case4:
//        String equalPlayer = player.getUserName()+":"+"\n"+ player.viewProfile();
//        assertEquals(guest.searchByCategory("Player"),equalPlayer);
          assertNull((guest.searchByCategory("Player")),null);

          //case5:
//        String equalCoach = coach.getUserName()+":"+"\n"+ coach.viewProfile();
//        assertEquals(guest.searchByCategory("Coach"),equalCoach);
        assertNull((guest.searchByCategory("Coach")),null);
    }

    @Test
    public void searchByKeyWord() {
        //case1:
        //assertEquals(guest.searchByName(league.getLeagueName()),league.viewProfile());

        //case2:
        assertNull(guest.searchByName("notExist"),null);
        String equalLeague = league.getLeagueName()+" "+"League"+":"+"\n"+league.viewProfile();
        assertEquals(guest.searchByCategory("League"), equalLeague);

    }
}