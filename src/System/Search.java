package System;

import AssociationAssets.*;
import DB.*;
import Users.Coach;
import Users.Fan;
import Users.Player;
import Users.Referee;

import java.util.HashMap;
import java.util.Map;

public class Search {
    private FootballSystem footballSystem = FootballSystem.getInstance();
    private TeamDB teamDB;
    private LeagueDB leagueDB;
    private SeasonDB seasonDB;
    private GameDB gameDB;
    private FieldDB fieldDB;

    /**
     * constructor
     */
    public Search() {
        teamDB = new TeamDB();
        leagueDB = new LeagueDB();
        seasonDB =new SeasonDB();
        gameDB = new GameDB();
        fieldDB = new FieldDB();
    }

    public Fan getUserByUserName(String uName){
        if(uName != null) {
            Fan user = footballSystem.getFanByUserName(uName);
            return user;
        }
        return null;
    }


    /**
     * @param teamName
     * @return Team by team name
     */
    public Team getTeamByTeamName(String teamName){
        if(teamName!=null) {
            if (this.teamDB.getAllTeams().containsKey(teamName)) {
                return this.teamDB.getAllTeams().get(teamName);
            }
        }
        return null;
    }

    /**
     * @param leagueName
     * @return League by league name
     */
    public League getLeagueByLeagueName(String leagueName){
        if(leagueName!=null){
            if (this.leagueDB.getAllLeagues().containsKey(leagueName)) {
                return this.leagueDB.getAllLeagues().get(leagueName);
            }
        }
        return null;
    }

    /**
     * @param year
     * @return Season by year
     */
    public Season getSeasonByYear(String year){
        if(year!= null) {
            if(this.seasonDB.getAllSeasons().containsKey(year)){
                return this.seasonDB.getAllSeasons().get(year);
            }
        }
        return null;
    }

    /**
     * @param gameID
     * @return Game by game id
     */
    public Game getGameByGameID(Integer gameID){
        if(gameID!= null) {
            if(this.gameDB.getAllGames().containsKey(gameID)){
                return this.gameDB.getAllGames().get(gameID);
            }
        }
        return null;
    }

    /**
     * @param fieldName
     * @return Field by field name
     */
    public Field getFieldByFieldName(String fieldName){
        if(fieldName!= null) {
            if(this.fieldDB.getAllFields().containsKey(fieldName)){
                return this.fieldDB.getAllFields().get(fieldName);
            }
        }
        return null;
    }

    /**
     * @return Hash map that holds for each league the league's name and its profile details
     */
    public HashMap<String, String> getAllLeaguesProfile(){
        HashMap<String, String>leaguesProfile = new HashMap<>();
        for (Map.Entry<String,League> entry : leagueDB.getAllLeagues().entrySet()) {
            String profileDetails=  entry.getValue().viewProfile();
            leaguesProfile.put(entry.getKey(), profileDetails);
        }
        return leaguesProfile;
    }

    /**
     * @return Hash map that holds for each team the team's name and its profile details
     */
    public HashMap<String, String> getAllTeamsProfile(){
        HashMap<String, String> teamsProfile = new HashMap<>();
        for (Map.Entry<String,Team> entry : teamDB.getAllTeams().entrySet()) {
            String profileDetails=  entry.getValue(). viewProfile();
            teamsProfile.put(entry.getValue().getName(),profileDetails);
        }
        return teamsProfile;
    }


    /**
     * @return Hash map that holds, for each referee, the referee's user name and his profile details
     */
    public HashMap<String, String> getAllRefereesProfile(){
        HashMap<String, String> refereesProfile = new HashMap<>();
        for (Map.Entry<String, Referee> entry : footballSystem.getRefereeMap().entrySet()) {
            String profileDetails= entry.getValue().viewProfile();
            refereesProfile.put(entry.getValue().getUserName(),profileDetails);
        }
        return refereesProfile;
    }

    /**
     * @return Hash map that holds, for each coach, the coach's user name and his profile details
     */
    public HashMap<String, String> getAllCoachesProfile(){
        HashMap<String, String> coachesProfile = new HashMap<>();
        for (Map.Entry<String, Coach> entry : footballSystem.getCoachMap().entrySet()) {
            String profileDetails= entry.getValue().viewProfile();
            coachesProfile.put(entry.getValue().getUserName(),profileDetails);
        }
        return coachesProfile;
    }

    /**
     * @return Hash map that holds, for each player, the player's user name and his profile details
     */
    public HashMap<String, String> getAllPlayersProfile(){
        HashMap<String, String> playersProfile = new HashMap<>();
        for (Map.Entry<String, Player> entry : footballSystem.getPlayerMap().entrySet()) {
            String profileDetails=  entry.getValue().viewProfile();
            playersProfile.put(entry.getValue().getUserName(),profileDetails);
        }
        return playersProfile;
    }


    //<editor-fold desc="Setter: setLeagueDB">
    public void setLeagueDB(LeagueDB leagueDB) {
        this.leagueDB = leagueDB;
    }
    //</editor-fold>

    //<editor-fold desc="Getters:getTeamDB,getLeagueDB,getSeasonDB,getGameDB,getFieldDB ">
    public TeamDB getTeamDB() {
        return teamDB;
    }
    public LeagueDB getLeagueDB() {
        return leagueDB;
    }

    public SeasonDB getSeasonDB() {
        return seasonDB;
    }

    public GameDB getGameDB() {
        return gameDB;
    }

    public FieldDB getFieldDB() {
        return fieldDB;
    }
    //</editor-fold>
}
