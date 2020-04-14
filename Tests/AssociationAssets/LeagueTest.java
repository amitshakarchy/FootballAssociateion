//package AssociationAssets;
//
//import PoliciesAndAlgorithms.GamesAssigningPolicy;
//import PoliciesAndAlgorithms.HeuristicGamesAssigningPolicy;
//import PoliciesAndAlgorithms.ScoreTablePolicy1;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
//public class LeagueTest {
//
//    League league;
//    Season season;
//
//    @Before
//    public void set_up(){
//
//        league = new League("champions");
//        season = new Season("2020");
//    }
//
//
//
//    @Test
//    public void getAssigningPolicy() {
//        league.addSeasonToLeague(season);
//        assertNull(league.getAssigningPolicy(season.year));
//    }
//
//    @Test
//    public void setAssigningPolicy() {
//        league.addSeasonToLeague(season);
//        league.setAssigningPolicy(season.year, new HeuristicGamesAssigningPolicy());
//        assertNotNull(league.getAssigningPolicy(season.year));
//    }
//
//    @Test
//    public void setScoreTablePolicy() {
//        league.addSeasonToLeague(season);
//        league.setScoreTablePolicy("2020", new ScoreTablePolicy1());
//        assertNotNull(league.getScoreTablePolicy(season.year));
//    }
//
//    @Test
//    public void getScoreTablePolicy() {
//        league.addSeasonToLeague(season);
//        league.setScoreTablePolicy("2020", new ScoreTablePolicy1());
//        assertNotNull(league.getScoreTablePolicy(season.year));
//    }
//
//    @Test
//    public void getTeams() {
//        league.addSeasonToLeague(season);
//        assertNotNull(league.getTeams("2020"));
//    }
//
//    @Test
//    public void getGames() {
//        league.addSeasonToLeague(season);
//        assertNotNull(league.getGames("2020"));
//    }
//
//    @Test
//    public void getLeagueName() {
//        assertEquals(league.getLeagueName(),"champions");
//    }
//
//    @Test
//    public void getSeasonBinders() {
//        assertNotNull(league.getSeasonBinders());
//
//    }
//
//    @Test
//    public void addSeasonToLeague() {
//        league.addSeasonToLeague(season);
//        assertEquals(league.getSeasonBinders().get(season.year).season.year,"2020");
//    }
//
//    @Test
//    public void addTeamsToLeague() {
//        league.addSeasonToLeague(season);
//        league.addTeamsToLeague("2020", new HashMap<>());
//        assertNotNull(league.getTeams("2020"));
//    }
//
//    @Test
//    public void addGamesToLeague() {
//        league.addSeasonToLeague(season);
//        league.addGamesToLeague("2020", new HashMap<>());
//        assertNotNull(league.getGames("2020"));
//    }
//}