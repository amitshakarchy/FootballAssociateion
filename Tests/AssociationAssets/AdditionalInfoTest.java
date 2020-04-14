//package AssociationAssets;
//
//import Users.Coach;
//import Users.Player;
//import Users.TeamManager;
//import Users.TeamOwner;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.util.HashMap;
//
//import static org.junit.Assert.*;
//
//public class AdditionalInfoTest {
//
//    Season season;
//    Team team;
//    Player player;
//    Coach coach;
//    TeamManager manager;
//    TeamOwner owner;
//
//    @Before
//    public void set_Up() {
//        season= new Season("season");
//        team = new Team(1, new Field("name", "city", 300)
//                , null, season, new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
//        player=new Player("player","a","a",null,null);
//        coach= new Coach("ss","ss","ss", null, null);
//        manager = new TeamManager("33","33","33");
//        owner= new TeamOwner("44","44","44");
//    }
//
//
//    @Test
//    public void getSeason() {
//        assertEquals(team.getSeason().year, season.year);
//    }
//
//    @Test
//    public void setSeason() {
//        team.setSeason(season);
//        assertEquals(team.getSeason().year, season.year);
//    }
//
//    @Test
//    public void getOwners() {
//        assertNotNull(team.getOwners());
//    }
//
//    @Test
//    public void setOwners() {
//        team.setOwners(new HashMap<>());
//        assertNotNull(team.getOwners());
//    }
//
//    @Test
//    public void getCoaches() {
//        assertNotNull(team.getCoaches());
//    }
//
//    @Test
//    public void setCoaches() {
//        team.setCoaches(new HashMap<>());
//        assertNotNull(team.getCoaches());
//    }
//
//    @Test
//    public void getManagers() {
//        assertNotNull(team.getManagers());
//    }
//
//    @Test
//    public void setManagers() {
//        team.setManagers(new HashMap<>());
//        assertNotNull(team.getManagers());
//    }
//
//    @Test
//    public void getPlayers() {
//        assertNotNull(team.getPlayers());
//    }
//
//    @Test
//    public void setPlayers() {
//        team.setPlayers(new HashMap<>());
//        assertNotNull(team.getPlayers());
//    }
//
//    @Test
//    public void addPlayer() {
//        team.addPlayer(player);
//        assertEquals(team.getPlayers().size(),1);
//    }
//
//    @Test
//    public void addCoach() {
//        team.addCoach(coach);
//        assertEquals(team.getCoaches().size(),1);
//    }
//
//    @Test
//    public void addManager() {
//        team.addManager(manager);
//        assertEquals(team.getManagers().size(),1);
//    }
//
//    @Test
//    public void addTeamOwner() {
//        team.addTeamOwner(owner);
//        assertEquals(team.getOwners().size(),1);
//    }
//
//    @Test
//    public void removePlayer() {
//        team.addPlayer(player);
//        team.removePlayer(player);
//        assertEquals(team.getPlayers().size(),0);
//
//        team.removePlayer(player);
//        assertEquals(team.getPlayers().size(),0);
//    }
//
//    @Test
//    public void removeCoach() {
//        team.addCoach(coach);
//        team.removeCoach(coach);
//        assertEquals(team.getCoaches().size(),0);
//    }
//
//    @Test
//    public void removeManager() {
//        team.addManager(manager);
//        team.removeManager(manager);
//        assertEquals(team.getManagers().size(),0);
//
//    }
//
//    @Test
//    public void removeTeamOwner() {
//        team.addTeamOwner(owner);
//        team.removeTeamOwner(owner);
//        assertEquals(team.getOwners().size(),0);
//    }
//
//    @Test
//    public void findPlayer() {
//        assertNull(team.findPlayer(player.getUserName()));
//        team.addPlayer(player);
//        assertEquals(team.findPlayer(player.getUserName()),player);
//    }
//
//    @Test
//    public void findCoach() {
//        assertNull(team.findCoach(coach.getUserName()));
//        team.addCoach(coach);
//        assertEquals(team.findCoach(coach.getUserName()),coach);
//    }
//
//    @Test
//    public void findManager() {
//        assertNull(team.findManager(manager.getUserName()));
//        team.addManager(manager);
//        assertEquals(team.findManager(manager.getUserName()),manager);
//    }
//
//    @Test
//    public void findTeamOwner() {
//        assertNull(team.findTeamOwner(owner.getUserName()));
//        team.addTeamOwner(owner);
//        assertEquals(team.findTeamOwner(owner.getUserName()),owner);
//    }
//}