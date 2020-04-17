package System;

import AssociationAssets.*;
import DB.*;
import PoliciesAndAlgorithms.GamesAssigningPolicy;
import Security.SecuritySystem;
import Users.*;

import java.util.*;

/**
 * this class is Singleton for the Football System.
 */
public class FootballSystem {

    SecuritySystem securitySystem = new SecuritySystem();
    List<Guest> guestList = new LinkedList<>();
    Map<String, Fan> fansHashMap = new HashMap<>();
    TeamDB teamDB = new TeamDB();
    GameDB gameDB = new GameDB();
    FieldDB fieldDB = new FieldDB();
    SeasonDB seasonDB = new SeasonDB();
    LeagueDB leagueDB = new LeagueDB();
    Map<String,Referee> refereeMap = new HashMap<>();
    Map<String,Player> playerMap  = new HashMap<>();
    Map<String,Coach> coachMap = new HashMap<>();


    /**
     * Create an instance of the class at the time of class loading
     */
    private static final FootballSystem instance = new FootballSystem();

    /**
     * private constructor to prevent others from instantiating this class
     */
    private FootballSystem() {

    }

    public SecuritySystem getSecuritySystem() {
        return securitySystem;
    }

    public List<Guest> getGuestList() {
        return guestList;
    }

    public Map<String, Fan> getFansHashMap() {
        return fansHashMap;
    }

    public TeamDB getTeamDB() {
        return teamDB;
    }

    public GameDB getGameDB() {
        return gameDB;
    }

    public FieldDB getFieldDB() {
        return fieldDB;
    }

    public SeasonDB getSeasonDB() {
        return seasonDB;
    }

    public LeagueDB getLeagueDB() {
        return leagueDB;
    }

    public Map<String, Referee> getRefereeMap() {
        return refereeMap;
    }

    public Map<String, Player> getPlayerMap() {
        return playerMap;
    }

    public Map<String, Coach> getCoachMap() {
        return coachMap;
    }

    /**
     * Provide a global point of access to the instance
     */
    public static FootballSystem getInstance() {
        return instance;
    }

    public Fan signIn(String userName, String password, String firstName,
                      String lastName) {
        // check if this user name already exits
        if (fansHashMap.containsKey(userName)) {
            System.out.println("This user name is already exist.");
            return null;
        }
        // input check
        else if (userName == null || userName.isEmpty() ||
                password == null || password.isEmpty() ||
                firstName == null || firstName.isEmpty() ||
                lastName == null || lastName.isEmpty()) {
            System.out.println("please insert valid inputs");
            return null;
        }
        securitySystem.addNewUser(userName, password);
        Fan fan = new Fan(userName, firstName, lastName);
        this.fansHashMap.put(userName, fan);
        return fan;
    } // useCase 2.2


    public Fan creatingRepresentativeFootballAssociation(String userName, String firstName, String lastName, GamesAssigningPolicy gamesAssigningPolicy) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof RepresentativeFootballAssociation){
                System.out.println("The user name: "+userName+" is already a RepresentativeFootballAssociation");
                return null;
            }
        }
        Fan fan = new RepresentativeFootballAssociation(userName, firstName, lastName, gamesAssigningPolicy);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Fan creatingReferee(String userName, String firstName, String lastName, EReferee training) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof Referee){
                System.out.println("The user name: "+userName+" is already a Referee");
                return null;
            }
        }
        Fan fan = new Referee(userName, firstName, lastName, training);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Fan creatingCoach(String userName, String firstName, String lastName, ETraining training,
                             ECoachRole eCoachRole) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof Coach){
                System.out.println("The user name: "+userName+" is already a Coach");
                return null;
            }
        }
        Fan fan = new Coach(userName, firstName, lastName, training, eCoachRole);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Fan creatingTeamOwner(String userName, String firstName, String lastName) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof TeamOwner){
                System.out.println("The user name: "+userName+" is already a Team Owner");
                return null;
            }
        }
        Fan fan = new TeamOwner(userName, firstName, lastName);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Fan creatingTeamManager(String userName, String firstName, String lastName) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof TeamManager){
                System.out.println("The user name: "+userName+" is already a Team Manager");
                return null;
            }
        }
        Fan fan = new TeamManager(userName, firstName, lastName);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Fan creatingPlayer(String userName, String firstName, String lastName
            , Date bDate, EPlayerRole playerRole) {
        if(this.getFansHashMap().containsKey(userName)){
            if(this.getFansHashMap().get(userName) instanceof Player){
                System.out.println("The user name: "+userName+" is already a Player");
                return null;
            }
        }
        Fan fan = new Player(userName, firstName, lastName, bDate, playerRole);
        this.fansHashMap.put(userName, fan);
        return fan;
    }

    public Field createField(String name, String city, int capacity){
        if(fieldDB.getAllFields().containsKey(name)){
            return fieldDB.getAllFields().get(name);
        }
        Field field = new Field(name,city,capacity);
        this.addFieldToDB(field);
        return field;
    }
    public Fan login(String userName, String password) {
        if (securitySystem.checkPasswordForLogIn(userName, password)) {
            if (this.fansHashMap.containsKey(userName)) {
                return fansHashMap.get(userName);
            }
        }
        System.out.println("user name or password incorrect");
        return null;
    }  // useCase 2.3

    public void removeUser(String userName) {
        if(this.fansHashMap.containsKey(userName)){
            this.fansHashMap.remove(userName);
            System.out.println("The user name: "+userName+" was removed successfully");
        }
        this.securitySystem.removeUser(userName);
    }

    public Fan getFanByUserName(String userName) {
        if (this.fansHashMap.containsKey(userName)) {
            return this.fansHashMap.get(userName);
        }
        return null;
    }

    public boolean existFanByUserName(String userName) {
        return this.fansHashMap.containsKey(userName);
    }
    public void addTeamToDB(Team team) {
        if(team != null) {
            if(this.teamDB.getAllTeams().containsKey(team.getName())){
                System.out.println("The team: "+team.getName()+" is already exit in the DB");
                return;
            }
            this.teamDB.addTeam(team, team.getName());
        }
    }
    public void removeTeamFromDB(int tid){
        this.teamDB.removeTeam(tid);
    }
    public void addLeagueToDB(League league) {
        if(league != null) {
            this.leagueDB.addLeague(league, league.getLeagueName());
        }
    }
    public void removeLeagueFromDB(String league){
        this.leagueDB.removeLeague(league);
    }
    public void addFieldToDB(Field field) {
        if(field != null) {
            this.fieldDB.addField(field, field.getName());
        }
    }
    public void removeFieldFromDB(String fieldName){
        this.fieldDB.removeField(fieldName);
    }
    public void addSeasonToDB(Season season) {
        if(season != null) {
            this.seasonDB.addSeason(season, season.getYear());
        }
    }
    public void removeSeasonFromDB(String year){
        this.seasonDB.removeSeason(year);
    }
    public void addGameToDB(Game game) {
        if(game != null) {
            this.gameDB.addGame(game, game.getGID());
        }
    }
    public void removeGameFromDB(int gid){
        this.gameDB.removeGame(gid);
    }
    public boolean findPlayerAtTeamByUserName(String userName){
        for (Team team: this.teamDB.getAllTeams().values()) {
            if(team.findPlayer(userName) != null){
                return true;
            }
        }
        return false;
    }
    public boolean findCoachAtTeamByUserName(String userName){
        for (Team team: this.teamDB.getAllTeams().values()) {
            if(team.findCoach(userName) != null){
                return true;
            }
        }
        return false;
    }
    public boolean findTeamOwnerAtTeamByUserName(String userName){
        for (Team team: this.teamDB.getAllTeams().values()) {
            if(team.findTeamOwner(userName) != null){
                return true;
            }
        }
        return false;
    }
    public boolean findTeamManagerAtTeamByUserName(String userName){
        for (Team team: this.teamDB.getAllTeams().values()) {
            if(team.findManager(userName) != null){
                return true;
            }
        }
        return false;
    }
    public boolean existLeagueByName(String leagueName){
        return this.leagueDB.getAllLeagues().containsKey(leagueName);
    }

    public boolean existFieldByName(String fieldName){
        return this.fieldDB.getAllFields().containsKey(fieldName);
    }
}
