package DAL;

import AssociationAssets.*;
import PoliciesAndAlgorithms.RegularScorePolicy;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Security.SecuritySystem;
import Users.*;
import System.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataSave {

    //region Fields
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
    //endregion


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
        saveTeams();
        saveGames();
        saveAdditionalInfo();
        saveSeasonLeagueBinders();
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
    private void saveSeasonLeagueBinders() {
        for (Season s:allSeasons.values()) {
            for (SeasonLeagueBinder binder:s.getLeagueBinders().values()) {
                int scorePolicy = (binder.getScoreTablePolicy() instanceof RegularScorePolicy) ? 1 :2;
                int gamePolicy = (binder.getAssigningPolicy() instanceof SimpleGamesAssigningPolicy) ? 1 :2;
                databaseManager.executeQuerySelect("" +
                        "Replace  INTO \n" +
                        "\tseasons_has_leagues(Seasons_Year, Leagues_Name, ScorePolicy, SchedulePolicy)\n" +
                        "VALUES\n" +
                        "    (" + binder.getSeason().getYear() + "," + binder.getLeague().getLeagueName() +","+
                        scorePolicy+","+gamePolicy+");");
            }

        }
    }



    private void saveAdditionalInfo() {

        for (Season season : allSeasons.values()) {
            for (AdditionalInfo additionalInfo : season.getTeamAdditionalInfo().values()) {
                // additionalinfo table
                databaseManager.executeQuerySelect("" +
                        "Replace  INTO \n" +
                        "\tadditionalinfo(Teams_name,Seasons_Year)\n" +
                        "VALUES\n" +
                        "    (" + additionalInfo.getTeam().getName() + "," + additionalInfo.getSeason().getYear() + ");");

                // additionalinfo_has_teamowner table
                Iterator iter = additionalInfo.getOwners().values().iterator();
                while (iter.hasNext()) {
                    String owner = (String) iter.next();
                    databaseManager.executeQuerySelect("" +
                            "Replace  INTO \n" +
                            "\tadditionalinfo_has_teamowner(AdditionalInfo_Teams_name,AdditionalInfo_Seasons_Year,TeamOwner_Username)\n" +
                            "VALUES\n" +
                            "    (" + additionalInfo.getTeam().getName() + "," + additionalInfo.getSeason().getYear() + "," + owner + ");");
                }


                // coach_has_additionalinfo table
                for (String coach : additionalInfo.getCoaches()) {
                    databaseManager.executeQuerySelect("" +
                            "Replace  INTO \n" +
                            "\tcoach_has_additionalinfo(Coach_Username,AdditionalInfo_Teams_name,AdditionalInfo_Seasons_Year)\n" +
                            "VALUES\n" +
                            "    (" + coach + "," + additionalInfo.getTeam().getName() + "," + additionalInfo.getSeason().getYear() + ");");
                }

                // teammanager_has_additionalinfo table
                iter = additionalInfo.getManagers().values().iterator();
                while (iter.hasNext()) {
                    String manager = (String) iter.next();
                    databaseManager.executeQuerySelect("" +
                            "Replace  INTO \n" +
                            "\tteammanager_has_additionalinfo(TeamManager_Username,AdditionalInfo_Teams_name,AdditionalInfo_Seasons_Year)\n" +
                            "VALUES\n" +
                            "    (" + manager + "," + additionalInfo.getTeam().getName() + "," + additionalInfo.getSeason().getYear() + ");");
                }

                // player_has_additionalinfo table
                for (String player : additionalInfo.getPlayers()) {
                    databaseManager.executeQuerySelect("" +
                            "Replace  INTO \n" +
                            "\tplayer_has_additionalinfo(Player_Username,AdditionalInfo_Teams_name,AdditionalInfo_Seasons_Year)\n" +
                            "VALUES\n" +
                            "    (" + player + "," + additionalInfo.getTeam().getName() + "," + additionalInfo.getSeason().getYear() + ");");
                }
            }
        }
    }


    private void saveGames() {
        for (Game game : allGames.values()) {

            // games table
            databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tgames(name, Fields_Name_Main, teamStatus, Seasons_has_Leagues_Seasons_Year, Seasons_has_Leagues_Leagues_Name)\n" +
                    "VALUES\n" +
                    "    (" + game.getGID() + "," + game.getDate() + "," + game.getScore().getGoalsHost() + ","
                    + game.getScore().getGoalsGuest() + "," + game.getField().getName() + "," + game.getHost().getName() + "," +
                    game.getSeason().getYear() + "," + game.getGuest().getName() + "," + game.getSeason().getYear() + ","
                    + game.getLeague() + ");");

            // games_has_referee table:
            databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tgames_has_referee(Games_idGames, Referee_Username, Game_Role)\n" +
                    "VALUES\n" +
                    "("+game.getGID()+","+ game.getSide1().getUserName()+",side1),"+
                            "("+game.getGID()+","+ game.getSide2().getUserName()+",side2),"+
                    "    (" +game.getGID()+","+ game.getMain().getUserName()+",main);");
        }

    }


    private void saveTeams() {
        //int id=1;
        for (Team team : allTeams.values()) {
            int active = 0;
            // TODO set team's league
            //String leaguename=team.get
            if (team.getIsActive() == ETeamStatus.ACTIVE) active = 1;
            databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteams(name, Fields_Name_Main, teamStatus, Seasons_has_Leagues_Seasons_Year, Seasons_has_Leagues_Leagues_Name)\n" +
                    "VALUES\n" +
                    "    (" + team.getName() + "," + team.getMainField().getName() + "," + active + "," + team.getCurrentSeason().getYear() + ");");

            for (Field f:team.getFields().values()) {
                databaseManager.executeQuerySelect("" +
                        "Replace  INTO \n" +
                        "\tteams_has_fields(Team_Name, Fields_Name)\n" +
                        "VALUES\n" +
                        "    (" + team.getName() + "," +f.getName()+");");
            }
    }
    }

    private void saveSeasons() {
        int id = 1;
        for (Season season : allSeasons.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tseasons(ID, Year)\n" +
                    "VALUES\n" +
                    "    (" + id++ + "," + season.getYear() + ");");
        }
    }

    private void saveLeagues() {
        for (League league : allLeagues.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tleagues(Name)\n" +
                    "VALUES\n" +
                    "    (" + league.getLeagueName() + ");");
        }
    }

    private void saveFields() {
        int id = 1;
        for (Field field : allFields.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tfields(ID, Name,City, Capacity)\n" +
                    "VALUES\n" +
                    "    (" + id++ + "," + field.getName() + "," + field.getCity() + "," + field.getCapacity() + ");");
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



