package DAL;

import AssociationAssets.*;
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
//        saveFields();
//        saveLeagues();
//        saveSeasons();
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
//        saveTeamOwners();
//        saveSystemManagers();
//        saveRFAs();
//        saveReferees();
//        saveFans();
//        savePasswordsUsers();
    }

    private void saveTeamManagers() {
        for (TeamManager manager : allTeamManagers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteammanager(Username,Training,CoachRole)\n" +
                    "VALUES\n" +
                    "    (" + manager.getUserName()+");");
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



