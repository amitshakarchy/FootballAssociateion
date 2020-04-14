package Users;

import AssociationAssets.*;
import System.FootballSystem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Budget.TeamBudget;

import static org.junit.jupiter.api.Assertions.*;

class TeamOwnerTest {

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
    public void setUp(){
        season = new Season("2020");
        FootballSystem.getInstance().addSeasonToDB(season);
        field = new Field("blomfield","Tel Aviv",100);
        FootballSystem.getInstance().addFieldToDB(field);
        teamBudget = new TeamBudget(null,null,5);
        teamOwner1 = (TeamOwner)FootballSystem.getInstance().creatingTeamOwner("Tair233","Tair","Cohen");
        teamOwner2 = (TeamOwner)FootballSystem.getInstance().creatingTeamOwner("Tal12","Tal","Cohen");
        team1 = new Team(1,"Macabi-Tel-aviv",season,field,teamBudget, teamOwner1);
        team2 = new Team(2,"Beitar",season,field,teamBudget,teamOwner2);
        team1.addSeasonToTeam(season);
        team2.addSeasonToTeam(season);
        FootballSystem.getInstance().addTeamToDB(team1);
        FootballSystem.getInstance().addTeamToDB(team1);
        FootballSystem.getInstance().signIn("yuval","12","yuval","Ben Eliezer");
        FootballSystem.getInstance().signIn("Gili","1","gili","la");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addPlayer() {
        setUp();
        // adding exits user
        assertEquals(team1.getAdditionalInfoWithSeasons().get(season.getYear()).getPlayers().size(),0);
        this.teamOwner1.addPlayer(team1,season,"yuval","123","yuval",
                "lala",null,EPlayerRole.Defender);
        AdditionalInfo additionalInfo = team1.getAdditionalInfoWithSeasons().get(season.getYear());
        assertEquals(team1.getAdditionalInfoWithSeasons().get(season.getYear()).getPlayers().size(),1);

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findPlayer("yuval")!= null);

        // trying to add a player that playing in another team
        assertEquals(this.teamOwner2.addPlayer(team2,season,"yuval","111","yuval","lalala",null,
                EPlayerRole.Defender),false);

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findPlayer("yuval")!= null);

        // team is null
        assertEquals(this.teamOwner2.addPlayer(null,season,"tairos","12","tair",
                "cohen",null,EPlayerRole.Defender),false);

        // season is null
        assertEquals(this.teamOwner2.addPlayer(team1,null,"tairos","12","tair",
                "cohen",null,EPlayerRole.Defender),false);

        //creating new user with sign in and creation
        assertEquals(this.teamOwner2.addPlayer(team2,season,"tairos","12","tair",
                "cohen",null,EPlayerRole.Defender),true);
        assertEquals(team2.getAdditionalInfoWithSeasons().get(season.getYear()).getPlayers().size(),1);

        //creating new player with a team not that im owning
        assertEquals(this.teamOwner2.addPlayer(team1,season,"tairos","12","tair",
                "cohen",null,EPlayerRole.Defender),false);
    }

    @Test
    void addCoach() {
        setUp();
        // adding exits user
        assertEquals(team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size(),0);
        this.teamOwner1.addCoach(team1,season,"yuval","123","yuval",
                "lala",ETraining.CDiploma,ECoachRole.AssistantCoach);
        AdditionalInfo additionalInfo = team1.getAdditionalInfoWithSeasons().get(season.getYear());
        assertEquals(team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size(),1);

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findCoach("yuval")!= null);

        // trying to add a Coach that training another team
        assertEquals(this.teamOwner2.addCoach(team2,season,"yuval","111"
                ,"yuval","lalala",ETraining.CDiploma,ECoachRole.AssistantCoach),false);

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findCoach("yuval")!= null);

        // team is null
        assertEquals(this.teamOwner2.addCoach(null,season,"tairos","12","tair",
                "cohen",ETraining.CDiploma,ECoachRole.AssistantCoach),false);

        // season is null
        assertEquals(this.teamOwner2.addCoach(team1,null,"tairos","12","tair",
                "cohen",ETraining.CDiploma,ECoachRole.AssistantCoach),false);

        //creating new user with sign in and creation
        assertEquals(this.teamOwner2.addCoach(team2,season,"tairos","12","tair",
                "cohen",ETraining.CDiploma,ECoachRole.AssistantCoach),true);

        //creating new Coach with a team not that im owning
        assertEquals(this.teamOwner2.addCoach(team1,season,"tairos","12","tair",
                "cohen",ETraining.CDiploma,ECoachRole.AssistantCoach),false);

        assertEquals(team2.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size(),1);
    }

    @Test
    void addTeamManager() {
        setUp();
        // adding exits user
        assertEquals(true,this.teamOwner1.addTeamManager(team1,season,"yuval","123","yuval",
                "lala"));
        AdditionalInfo additionalInfo = team1.getAdditionalInfoWithSeasons().get(season.getYear());
        assertEquals(1,team1.getAdditionalInfoWithSeasons()
                .get(season.getYear()).getManagers().get(team1.getTeamOwner().getUserName()).size());
        assertEquals(true,teamOwner1.addTeamManager(team1,season,"Gili","12","gili","cohen"));

        assertEquals(2,team1.getAdditionalInfoWithSeasons()
                .get(season.getYear()).getManagers().get(team1.getTeamOwner().getUserName()).size());       // checking if the username exist an the additional info
        assertTrue(additionalInfo.findManager("yuval")!= null);

        // trying to add a Coach that training another team
        assertEquals(false,this.teamOwner2.addTeamManager(team2,season,"yuval","111"
                ,"yuval","lalala"));

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findManager("yuval")!= null);

        // team is null
        assertEquals(false,this.teamOwner2.addTeamManager(null,season,"tairos","12","tair",
                "cohen"));

        // season is null
        assertEquals(false,this.teamOwner2.addTeamManager(team1,null,"tairos","12","tair",
                "cohen"));

        //creating new user with sign in and creation
        assertEquals(true,this.teamOwner2.addTeamManager(team2,season,"tairos","12","tair",
                "cohen"));

        //creating new Coach with a team not that im owning
        assertEquals(false,this.teamOwner2.addTeamManager(team1,season,"tairos","12","tair",
                "cohen"));
        assertEquals(1,team2.getAdditionalInfoWithSeasons().get(season.getYear()).getManagers().size());
    }

    @Test
    void addField() {
        setUp();
        // insert new field
        assertEquals(true,teamOwner1.addField(team1,season,"Blom","Tel-Aviv",13000));
        // team owner trying to add field not to his team
        assertEquals(false,teamOwner2.addField(team1,season,"Blom","Tel-Aviv",13000));
        // trying to add null team
        assertEquals(false,teamOwner1.addField(null,season,"Blom","Tel-Aviv",13000));
        //trying to add null season
        assertEquals(false,teamOwner1.addField(team1,null,"Blom","Tel-Aviv",13000));
        // trying to add field that exits in a team
        assertEquals(false,teamOwner1.addField(team1,season,"Blom","Tel-Aviv",13000));
        // trying to add field that exits in another team
        assertEquals(true,teamOwner2.addField(team2,season,"Blom","Tel-Aviv",13000));

    }

    @Test
    void removePlayer() {
        addPlayer();
        assertEquals(1,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getPlayers().size());
        teamOwner1.removePlayer(team1,season,"yuval");
        assertEquals(0,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getPlayers().size());
    }

    @Test
    void removeCoach() {
        addCoach();
        assertEquals(1,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size());
        teamOwner1.removeCoach(team1,season,"yuval");
        assertEquals(0,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size());
    }

    @Test
    void removeTeamManager() {
        addTeamManager();
        assertEquals(1,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size());
        teamOwner1.removeTeamManager(team1,season,"yuval");
        assertEquals(0,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getCoaches().size());

    }

    @Test
    void removeField() {
    }

    @Test
    void nominateTeamOwner() {
        setUp();
        // adding exits user
        assertEquals(0,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getOwners().size());
        this.teamOwner1.nominateTeamOwner(team1,season,"yuval");
        AdditionalInfo additionalInfo = team1.getAdditionalInfoWithSeasons().get(season.getYear());
        assertEquals(1,team1.getAdditionalInfoWithSeasons().get(season.getYear()).getOwners().size());

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findTeamOwner("yuval")!= null);

        // trying to add a team owner that owing another team
        assertEquals(false,this.teamOwner2.nominateTeamOwner(team2,season,"yuval"));

        // checking if the username exist an the additional info
        assertTrue(additionalInfo.findTeamOwner("yuval")!= null);

        // team is null
        assertEquals(false,this.teamOwner2.nominateTeamOwner(null,season,"tairos"));

        // season is null
        assertEquals(false,this.teamOwner2.nominateTeamOwner(team1,null,"tairos"));

        //trying to nominate team owner that is not exits in the system
        assertEquals(false,this.teamOwner2.nominateTeamOwner(team2,season,"tairos"));

        //trying to nominate team owner that exits in the system
        assertEquals(true,this.teamOwner2.nominateTeamOwner(team2,season,"Gili"));

        //creating new team owner with a team not that im owning
        assertEquals(false,this.teamOwner2.nominateTeamOwner(team1,season,"tairos"));
        assertEquals(1,team2.getAdditionalInfoWithSeasons().get(season.getYear()).getOwners().size());

    }

    @Test
    void discardNominationForTeamOwner() {
    }

    @Test
    void nominateTeamManager() {
    }

    @Test
    void closeTeam() {
    }

    @Test
    void finnacelReport() {
    }
}