package DAL;

import AssociationAssets.*;
import DB.*;
import PoliciesAndAlgorithms.*;
import System.*;
import Users.*;

import java.sql.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class JDBCConnector {

    FootballSystem system;
    DatabaseManager databaseManager;
    HashMap<String, Season> allSeasons;
    HashMap<String, League> allLeagues;
    HashMap<String,Team> allTeams;
    HashMap<String, Field> allFields;
    Map<String, Fan> allFans;
    Map<String, TeamOwner> allTeamOwners;
    public void connectDB() {

        databaseManager = new databaseManagerServerMySQL();
        databaseManager.startConnection();

        system = FootballSystem.getInstance();
        allLeagues= system.getLeagueDB().getAllLeagues();
        allSeasons=system.getSeasonDB().getAllSeasons();
        allTeams= system.getTeamDB().getAllTeams();
        allFields= system.getFieldDB().getAllFields();
        allFans= system.getFansHashMap();
        allTeamOwners= system.getTeamOwnerMap();

        uploadFans();

        uploadFields();
        uploadLeagues();
        uploadSeasons();
        uploadTeams();
        uploadSeasonLeagueBinders();

        databaseManager.closeConnection();
    }

    private void uploadFans() {

    }

    private void uploadTeams() {
        ResultSet resultSet = databaseManager.executeQuerySelect("Select * From teams");
        try {
            while (resultSet.next()) {

                String name= resultSet.getString("name");
                String fieldName= resultSet.getString("Fields_Name_Main");
                String status= resultSet.getString("teamStatus");
                String seasonYear= resultSet.getString("Seasons_has_Leagues_Seasons_Year");

                ResultSet teamOwnerRes = databaseManager.executeQuerySelect("" +
                                "SELECT * FROM additionalinfo_has_teamowner "+
                                "WHERE AdditionalInfo_Teams_name = \""+name+"\" " +
                                "AND AdditionalInfo_Seasons_Year=\""+seasonYear+"\";");
                TeamOwner owner=null;
                if(teamOwnerRes.next()){
                    owner= allTeamOwners.get(teamOwnerRes.getString("TeamOwner_Username"));
                }

                Field field= allFields.get(fieldName);
                if(status.equals("0")) status= "INACTIVE";
                else status= "ACTIVE";
                Season season= allSeasons.get(seasonYear);

                Team team= new Team(1,name,season,field,null,owner);
                team.setIsActive(ETeamStatus.valueOf(status));

                //TODO: additional info
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    private void uploadSeasonLeagueBinders() {
        Set<SeasonLeagueBinder> binders= new HashSet<>();
        ResultSet resultSet = databaseManager.executeQuerySelect("Select * From seasons_has_leagues");
        try {
            while (resultSet.next()) {

                String seasonYear= resultSet.getString("Seasons_Year");
                String leagueName= resultSet.getString("Leagues_Name");
                String scoreTablePolicy= resultSet.getString("ScorePolicy");
                String gameSchedulePolicy= resultSet.getString("SchedulePolicy");

                ScoreTablePolicy tablePolicy= new RegularScorePolicy();
                if(scoreTablePolicy.equals("2")) tablePolicy= new ScoreTablePolicy2();

                GamesAssigningPolicy gamesAssigningPolicy = new HeuristicGamesAssigningPolicy();
                if(gameSchedulePolicy.equals("1")) gamesAssigningPolicy = new SimpleGamesAssigningPolicy();

                Season season= allSeasons.get(seasonYear);
                League league= allLeagues.get(leagueName);

                // upload new binder
                SeasonLeagueBinder binder= new SeasonLeagueBinder(season,league);

                // attach teams
                ResultSet teamsSet = databaseManager.executeQuerySelect(
                                "SELECT * from teams " +
                                "WHERE Seasons_has_Leagues_Leagues_Name = \""+leagueName+"\" " +
                                "AND Seasons_has_Leagues_Seasons_Year = "+seasonYear );

                HashMap<String, Team> teams= new HashMap<>();
                while(teamsSet.next()){
                    String teamName= teamsSet.getString("name");
                    Team team= allTeams.get(teamName);
                    teams.put(teamName, team);
                }
                binder.addTeamsToLeague(teams);

                //  TODO: attach games

                // attach policies
                binder.setScoreTablePolicy(tablePolicy);
                binder.setAssigningPolicy(gamesAssigningPolicy);

                // TODO: attach to league & Season


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void uploadSeasons( ) {
        ResultSet resultSet = databaseManager.executeQuerySelect("Select * From seasons");
        try {
            while (resultSet.next()) {
                Season season = new Season(resultSet.getString("Year"));
                allSeasons.put(season.getYear(), season);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void uploadLeagues() {

        ResultSet resultSet = databaseManager.executeQuerySelect("Select * From leagues");
        try {
            while (resultSet.next()) {
                // Load the league
                League league = new League(resultSet.getString("Name"));
                allLeagues.put(league.getLeagueName(), league);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void uploadFields( ) {

        ResultSet resultSet = databaseManager.executeQuerySelect("Select * From fields");
        try {
            while (resultSet.next()) {
                Field field = new Field(resultSet.getString("Name"),
                        resultSet.getString("City"),
                        resultSet.getInt("Capacity"));
                allFields.put(field.getName(), field);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
