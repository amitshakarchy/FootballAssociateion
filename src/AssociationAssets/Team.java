package AssociationAssets;

import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.HashMap;

/**
 * Class Team represents a team. It holds a team's home and away games, it's players and
 * all other team assets.
 */
public class Team {

    //region Fields
    int TID;
    String name;
    Field homeField;
    AdditionalInfo info;
    HashMap<Integer, Game> homeGames;
    HashMap<Integer, Game> awayGames;
    TeamBudget budget;
    //endregion

    /**
     * The constructor receives all needed information in order to generate an AdditionalInfo
     * object, and binds it with the team.
     * @param TID
     * @param homeField
     * @param budget
     * @param season
     * @param owners
     * @param coaches
     * @param managers
     * @param players
     */
    public Team(int TID,String name, Field homeField, TeamBudget budget, Season season, HashMap<String,TeamOwner>  owners, HashMap<String, Coach> coaches, HashMap<String, TeamManager> managers, HashMap<String, Player> players) {

        this.TID = TID;
        this.name = name;
        this.homeField = homeField;
        this.budget = budget;
        this.homeGames = new HashMap<>();
        this.awayGames = new HashMap<>();
        this.info = new AdditionalInfo( season, owners, coaches, managers, players);
        info.setTeam(this);
    }

    //region Getters
    public int getTID() {
        return TID;
    }
    public String getName() {
        return name;
    }
    public Field getHomeField() {
        return homeField;
    }

    public HashMap<Integer, Game> getHomeGames() {
        return homeGames;
    }

    public HashMap<Integer, Game> getAwayGames() {
        return awayGames;
    }

    public TeamBudget getBudget() {
        return budget;
    }

    public Season getSeason() {
        return info.getSeason();
    }

    public HashMap<String,TeamOwner>  getOwners() {

        return info.getOwners();
    }

    public HashMap<String, Coach> getCoaches() {

        return info.getCoaches();
    }

    public HashMap<String, TeamManager> getManagers() {

        return info.getManagers();
    }

    public HashMap<String, Player> getPlayers() {

        return info.getPlayers();
    }
    //endregion


    //region Setters
    public void setHomeField(Field homeField) {
        this.homeField = homeField;
    }

    public void setBudget(TeamBudget budget) {
        this.budget = budget;
    }

    public void setTeam(Team team) {

        info.setTeam(team);
    }

    public void setSeason(Season season) {

        info.setSeason(season);
    }

    public void setOwners(HashMap<String,TeamOwner>  owners) {

        info.setOwners(owners);
    }

    public void setCoaches(HashMap<String, Coach> coaches) {

        info.setCoaches(coaches);
    }

    public void setManagers(HashMap<String, TeamManager> managers) {

        info.setManagers(managers);
    }

    public void setPlayers(HashMap<String, Player> players) {

        info.setPlayers(players);
    }

    public void setInfo(AdditionalInfo info) {
        this.info = info;
    }

    public void setHomeGames(HashMap<Integer, Game> homeGames) {
        this.homeGames = homeGames;
    }

    public void setAwayGames(HashMap<Integer, Game> awayGames) {
        this.awayGames = awayGames;
    }


    //endregion

    //region Add/Remove from AdditionalInfo

    /**
     * Add a player to the players list
     * @param player
     */
    public void addPlayer(Player player) {
        info.addPlayer(player);
    }

    /**
     * Add a coach to the coachs list
     * @param coach
     */
    public void addCoach(Coach coach) {
        info.addCoach(coach);
    }

    /**
     * Add a manager to the managers list
     * @param manager
     */
    public void addManager(TeamManager manager) {
        info.addManager(manager);
    }

    /**
     * Add an owner to the owners list
     * @param owner
     */
    public void addTeamOwner(TeamOwner owner) {
        info.addTeamOwner(owner);
    }
    /**
     * Remove a player from the players list
     * @param player
     */
    public void removePlayer(Player player) {
        info.removePlayer(player);
    }
    /**
     * Remove a coach from the coaches list
     * @param coach
     */
    public void removeCoach(Coach coach) {
        info.removeCoach(coach);

    }

    /**
     * Remove a manager from the managers list
     * @param manager
     */
    public void removeManager(TeamManager manager) {
        info.removeManager(manager);
    }
    /**
     * Remove an owner from the owners list
     * @param owner
     */
    public void removeTeamOwner(TeamOwner owner) {
        info.removeTeamOwner(owner);
    }

    //endregion

    //region Add/Remove games

    /**
     *
     * Add a home game to the home games list
     * @param homeGame
     */
    public void addHomeGame(Game homeGame) {
        if (!homeGames.containsKey(homeGame.getGID())) {
            homeGames.put(homeGame.getGID(), homeGame);
        }
    }
    /**
     *
     * Add an away game to the away games list
     * @param awayGame
     */
    public void addAwayGame(Game awayGame) {
        if (!awayGames.containsKey(awayGame.getGID())) {
            awayGames.put(awayGame.getGID(), awayGame);
        }
    }

    /**
     *
     * Remove a home game from the home games list
     * @param homeGame
     */
    public void removeHomeGame(Game homeGame) {
        if (homeGames.containsKey(homeGame.getGID())) {
            homeGames.remove(homeGame.getGID());
        }
    }
    /**
     *
     * Remove an away game from the away games list
     * @param awayGame
     */
    public void removeAwayGame(Game awayGame) {
        if (awayGames.containsKey(awayGame.getGID())) {
            awayGames.remove(awayGame.getGID());
        }
    }
    //endregion

    //region Find methods
    /**
     * Given a user ID, finds the player in the team.
     * if the player is not in the team, returns NULL
     * @param PID
     * @return
     */
    public Player findPlayer(String PID){
        Player p=info.findPlayer(PID);
        return p;
    }

    /**
     * Given a user ID, finds the coach in the team.
     * if the coach is not in the team, returns NULL
     * @param CID
     * @return
     */
    public Coach findCoach(String CID){
        Coach c=info.findCoach(CID);
        return c;
    }
    /**
     * Given a user ID, finds the manager in the team.
     * if the manager is not in the team, returns NULL
     * @param MID
     * @return
     */
    public TeamManager findManager(String MID){
        TeamManager m=info.findManager(MID);
        return m;
    }
    /**
     * Given a user ID, finds the owner in the team.
     * if the owner is not in the team, returns NULL
     * @param OID
     * @return
     */
    public TeamOwner findTeamOwner(String OID){
        TeamOwner o=info.findTeamOwner(OID);
        return o;
    }
    /**
     * Given a game ID, finds the relevant home-game
     * if the game is not in the team, returns NULL
     * @param gid
     * @return
     */
    public Game findHomeGame(int gid){
        return homeGames.get(gid);
    }

    /**
     * Given a game ID, finds the relevant away-game
     * if the game is not in the team, returns NULL
     * @param gid
     * @return
     */
    public Game findAwayGame(int gid){
        return awayGames.get(gid);
    }
    //endregion
}
