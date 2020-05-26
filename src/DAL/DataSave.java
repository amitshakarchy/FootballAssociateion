package DAL;

import AssociationAssets.*;
import PoliciesAndAlgorithms.RegularScorePolicy;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Security.SecuritySystem;
import Users.*;
import System.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public void saveAdditionalInfo() {
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


    public void saveGames() {
        for (Game game : allGames.values()) {

            // change date formatting
            java.util.Date dt = game.getDate();
            java.text.SimpleDateFormat sdf =
                    new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(dt);

            // save game:
            PreparedStatement ps = null;
            String query = "REPLACE INTO games(idGames, DateTime, GoalHost, GoalGuest, Fields_Name," +
                    "Teams_Host_Name, Teams_Seasons_Year_Host," +
                    "    Teams_Guest_Name, Teams_Seasons_Year_Guest," +
                    "    Seasons_has_Leagues_Seasons_Year, Seasons_has_Leagues_Leagues_Name)"+
                    " VALUES (?,?,?,?,?,?,?,?,?,?,?);"; //query
            try{
                ps = databaseManager.conn.prepareStatement(query); //compiling query in the DB
                ps.setInt(1, game.getGID());
                ps.setString(2, dateStr);
                ps.setInt(3, game.getScore().getGoalsHost());
                ps.setInt(4, game.getScore().getGoalsGuest());
                ps.setString(5, game.getField().getName());
                ps.setString(6, game.getHost().getName());
                ps.setInt(7, Integer.parseInt(game.getSeason().getYear()));
                ps.setString(8, game.getGuest().getName());
                ps.setInt(9, Integer.parseInt(game.getSeason().getYear()));
                ps.setInt(10, Integer.parseInt(game.getSeason().getYear()));
                ps.setString(11, game.getLeague().getLeagueName());
                //System.out.println(ps.toString());
                ps.executeUpdate();
                databaseManager.conn.commit();
                ps=null;


                // games_has_referee table:

                // save main referee
                query =  "Replace  INTO \n" +
                        "\tgames_has_referee(Games_idGames, Referee_Username, Game_Role)\n" +
                        "\tVALUES(?,?,?);";
                ps = databaseManager.conn.prepareStatement(query); //compiling query in the DB
                ps.setInt(1, game.getGID());
                ps.setString(2, game.getMain().getUserName());
                ps.setString(3,"main");
               // System.out.println(ps.toString());
                ps.executeUpdate();
                databaseManager.conn.commit();

                // save side1 referee
                query =  "Replace  INTO \n" +
                        "\tgames_has_referee(Games_idGames, Referee_Username, Game_Role)\n" +
                        "\tVALUES(?,?,?);";
                ps = databaseManager.conn.prepareStatement(query); //compiling query in the DB
                ps.setInt(1, game.getGID());
                ps.setString(2, game.getSide1().getUserName());
                ps.setString(3,"side1");
                //System.out.println(ps.toString());
                ps.executeUpdate();
                databaseManager.conn.commit();

                // save side2 referee
                query =  "Replace  INTO \n" +
                        "\tgames_has_referee(Games_idGames, Referee_Username, Game_Role)\n" +
                        "\tVALUES(?,?,?);";
                ps = databaseManager.conn.prepareStatement(query); //compiling query in the DB
                ps.setInt(1, game.getGID());
                ps.setString(2, game.getSide2().getUserName());
                ps.setString(3,"side2");
                //System.out.println(ps.toString());
                ps.executeUpdate();
                databaseManager.conn.commit();

            }catch (SQLException e) {
                try{
                    databaseManager.conn.rollback();
                }catch (SQLException e2) {
                    e2.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }


    public void saveTeams() {
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
                    "    (" + team.getName() + "," + team.getMainField().getName() + "," + active + "," + team.getCurrentSeason().getYear() +","+ ");");

            for (Field f:team.getFields().values()) {
                databaseManager.executeQuerySelect("" +
                        "Replace  INTO \n" +
                        "\tteams_has_fields(Team_Name, Fields_Name)\n" +
                        "VALUES\n" +
                        "    (" + team.getName() + "," +f.getName()+");");
            }
    }
    }

    public void saveSeasons() {
        int id = 1;
        for (Season season : allSeasons.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tseasons(ID, Year)\n" +
                    "VALUES\n" +
                    "    (" + id++ + "," + season.getYear() + ");");
        }
    }

    public void saveLeagues() {
        for (League league : allLeagues.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tleagues(Name)\n" +
                    "VALUES\n" +
                    "    (" + league.getLeagueName() + ");");
        }
    }

    public void saveFields() {
        int id = 1;
        for (Field field : allFields.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tfields(ID, Name,City, Capacity)\n" +
                    "VALUES\n" +
                    "    (" + id++ + "," + field.getName() + "," + field.getCity() + "," + field.getCapacity() + ");");
        }
    }


    public void savePasswordsUsers() {
        SecuritySystem sec = FootballSystem.getInstance().getSecuritySystem();
        for (Map.Entry<String, String> entry : sec.getUsersHashMap("iseFab5").entrySet()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tuserpasswords(Username,Password_user)\n" +
                    "VALUES\n" +
                    "    (" + entry.getKey() + "," + entry.getValue() + ";");
        }
    }

    public void saveFans() {
        for (Fan fan : allFans.values()) {
            int offlineStatus = 0;
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tfans(Username,FirstName, LastName, AccountStatus)\n" +
                    "VALUES\n" +
                    "    (" + fan.getUserName() + "," + fan.getfName() + "," + fan.getlName() + "," + offlineStatus + ";");
        }
    }

    public void saveReferees() {
        for (Referee referee : allReferees.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\treferee(Username,Training)\n" +
                    "VALUES\n" +
                    "    (" + referee.getUserName() + "," + referee.getTraining() + ";");
        }
    }

    public void saveRFAs() {
        for (RepresentativeFootballAssociation rfa : allRFAs.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\trfa(Username)\n" +
                    "VALUES\n" +
                    "    (" + rfa.getUserName() + ");");
        }
    }

    public void saveSystemManagers() {
        for (SystemManager systemManager : allSystemManagers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tsystemmanager(Username)\n" +
                    "VALUES\n" +
                    "    (" + systemManager.getUserName() + ");");
        }
    }


    public void saveTeamOwners() {
        for (TeamOwner owner : allTeamOwners.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteamowner(Username)\n" +
                    "VALUES\n" +
                    "    (" + owner.getUserName() + ");");
        }
    }

    public void saveTeamManagers() {
        for (TeamManager manager : allTeamManagers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tteammanager(Username)\n" +
                    "VALUES\n" +
                    "    (" + manager.getUserName() + ");");
        }
    }

    public void saveCoaches() {
        for (Coach c : allCoaches.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tcoach(Username,Training,CoachRole)\n" +
                    "VALUES\n" +
                    "    (" + c.getUserName() + "," + c.getTraining() + "," + c.getRole() + ");");
        }
    }

    public void savePlayers() {
        for (Player p : allPlayers.values()) {
            ResultSet resultSet = databaseManager.executeQuerySelect("" +
                    "Replace  INTO \n" +
                    "\tplayer(Username,BirthDate,PlayerRole)\n" +
                    "VALUES\n" +
                    "    (" + p.getUserName() + "," + p.getbDate() + "," + p.getRole() + ");");
        }
    }

}



