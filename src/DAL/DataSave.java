package DAL;

import AssociationAssets.*;
import Security.SecuritySystem;
import Users.*;
import System.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DataSave {

    private HashMap<Integer, Game> allGames;
    DatabaseManager databaseManager;

    FootballSystem system;
    HashMap<String, Season> allSeasons;
    HashMap<String, League> allLeagues;
    HashMap<String, Team> allTeams;
    HashMap<String, Field> allFields;
    Map<String, Coach> allCoaches;

    // Users:
    Map<String, Fan> allFans;
    Map<String, TeamOwner> allTeamOwners;
    Map<String, Player> allPlayers;
    private Map<String, TeamManager> allTeamManagers;
    private Map<String, SystemManager> allSystemManagers;
    private Map<String, RepresentativeFootballAssociation> allRFAs;
    private Map<String, Referee> allReferees;


    public DataSave(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        system = FootballSystem.getInstance();
        allLeagues = system.getLeagueDB().getAllLeagues();
        allSeasons = system.getSeasonDB().getAllSeasons();
        allTeams = system.getTeamDB().getAllTeams();
        allFields = system.getFieldDB().getAllFields();
        allFans = system.getFansHashMap();
        allTeamOwners = system.getTeamOwnerMap();
        allPlayers = system.getPlayerMap();
        allCoaches = system.getCoachMap();
        allTeamManagers = system.getTeamManagerMap();
        allSystemManagers = system.getSystemManagerMap();
        allRFAs = system.getRepresentativeFootballAssociationMap();
        allReferees = system.getRefereeMap();
        allGames = system.getGameDB().getAllGames();
    }

    public void saveAllData() {
        saveUsers();
        saveFields();
        saveLeagues();
        saveSeasons();
//        saveTeams();
//        saveGames();
//        saveTeamsGames();
//        saveAdditionalInfo();
//        saveSeasonLeagueBinders();
    }




    private void saveUsers() {
        savePlayers();
        saveCoaches();
        saveTeamManagers();
        saveTeamOwners();
        saveSystemManagers();
        saveRFAs();
        saveReferees();
        saveFans();
        savePasswordsUsers();
    }

/*    private void saveTeams() {
        int id=1;
        for (Team team : allTeams.values()) {
            int active=0;
            String leaguename=team.get
            if(team.getIsActive()==true) active=1;
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteams(name, Fields_Name_Main, teamStatus, Seasons_has_Leagues_Seasons_Year, Seasons_has_Leagues_Leagues_Name)\n" +
                    "VALUES\n" +
                    "    ("+ team.getName()+","+team.getMainField().getName()+","+active+","+team.getCurrentSeason().getYear()+","+ ";");
        }
    }*/

    private void saveSeasons() {
        int id=1;
        for (Season season : allSeasons.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tseasons(ID, Year)\n" +
                    "VALUES\n" +
                    "    ("+ id++ +"," + season.getYear()+ ";");
        }
    }

    private void saveLeagues() {
        for (League league : allLeagues.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tleagues(Name)\n" +
                    "VALUES\n" +
                    "    ("+ league.getLeagueName()+ ";");
        }
    }

    private void saveFields() {
        int id = 1;
        for (Field field : allFields.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tfields(ID, Name,City, Capacity)\n" +
                    "VALUES\n" +
                    "    (" + id++ + "," + field.getName() + "," + field.getCity() + "," + field.getCapacity() + ";");
        }
    }


    private void savePasswordsUsers() {
        SecuritySystem sec = FootballSystem.getInstance().getSecuritySystem();
        for (Map.Entry<String, String> entry : sec.getUsersHashMap("iseFab5").entrySet()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tuserpasswords(Username,Password_user)\n" +
                    "VALUES\n" +
                    "    (" + entry.getKey() + "," + entry.getValue() + ";");
        }
    }

    private void saveFans() {
        for (Fan fan : allFans.values()) {
            int offlineStatus = 0;
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tfans(Username,FirstName, LastName, AccountStatus)\n" +
                    "VALUES\n" +
                    "    (" + fan.getUserName() + "," + fan.getfName() + "," + fan.getlName() + "," + offlineStatus + ";");
        }
    }

    private void saveReferees() {
        for (Referee referee : allReferees.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\treferee(Username,Training)\n" +
                    "VALUES\n" +
                    "    (" + referee.getUserName() + "," + referee.getTraining() + ";");
        }
    }

    private void saveRFAs() {
        for (RepresentativeFootballAssociation rfa : allRFAs.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\trfa(Username)\n" +
                    "VALUES\n" +
                    "    (" + rfa.getUserName() + ");");
        }
    }

    private void saveSystemManagers() {
        for (SystemManager systemManager : allSystemManagers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tsystemmanager(Username)\n" +
                    "VALUES\n" +
                    "    (" + systemManager.getUserName() + ");");
        }
    }


    private void saveTeamOwners() {
        for (TeamOwner owner : allTeamOwners.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteamowner(Username)\n" +
                    "VALUES\n" +
                    "    (" + owner.getUserName() + ");");
        }
    }

    private void saveTeamManagers() {
        for (TeamManager manager : allTeamManagers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteammanager(Username)\n" +
                    "VALUES\n" +
                    "    (" + manager.getUserName() + ");");
        }
    }

    private void saveCoaches() {
        for (Coach c : allCoaches.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tcoach(Username,Training,CoachRole)\n" +
                    "VALUES\n" +
                    "    (" + c.getUserName() + "," + c.getTraining() + "," + c.getRole() + ");");
        }
    }

    private void savePlayers() {
        for (Player p : allPlayers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tplayer(Username,BirthDate,PlayerRole)\n" +
                    "VALUES\n" +
                    "    (" + p.getUserName() + "," + p.getbDate() + "," + p.getRole() + ");");
        }
    }

}



