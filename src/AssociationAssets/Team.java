package AssociationAssets;

import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.HashMap;

/**
 * Class Team represents a team. It holds a team's home and away games, it's players and
 * all other team assets.
 * Aouthors: Amit Shakarchy, Alon Gutman
 */
public class Team {

    //region Fields
    int TID;
    Field homeField;
    AdditionalInfo info;
    HashMap<String, Game> homeGames;
    HashMap<String, Game> awayGames;
    TeamBudget budget;
    //endregion

    /**
     * The constructor receives all needed information in order to generate an AdditionalInfo
     * object, and binds it with the team.
     * @param TID - team ID
     * @param homeField - the home field
     * @param budget - budget of the team
     * @param season - the season
     * @param owners - list of team's owners
     * @param coaches - list of team's coaches
     * @param managers - list of team's managers
     * @param players - list of team's players
     */
    public Team(int TID, Field homeField, TeamBudget budget, Season season, HashMap<String,TeamOwner>  owners, HashMap<String, Coach> coaches, HashMap<String, TeamManager> managers, HashMap<String, Player> players) {
        this.TID = TID;
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

    public Field getHomeField() {
        return homeField;
    }

    public HashMap<String, Game> getHomeGames() {
        return homeGames;
    }

    public HashMap<String, Game> getAwayGames() {
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

    public void setHomeGames(HashMap<String, Game> homeGames) {
        this.homeGames = homeGames;
    }

    public void setAwayGames(HashMap<String, Game> awayGames) {
        this.awayGames = awayGames;
    }


    //endregion

    //region Add/Remove from AdditionalInfo

    /**
     * Add a player to the players list
     * @param player - a new player
     */
    public void addPlayer(Player player) {
        info.addPlayer(player);
    }

    /**
     * Add a coach to the coachs list
     * @param coach - a new coach
     */
    public void addCoach(Coach coach) {
        info.addCoach(coach);
    }

    /**
     * Add a manager to the managers list
     * @param manager - a new manager
     */
    public void addManager(TeamManager manager) {
        info.addManager(manager);
    }

    /**
     * Add an owner to the owners list
     * @param owner - a new owner
     */
    public void addTeamOwner(TeamOwner owner) {
        info.addTeamOwner(owner);
    }
    /**
     * Remove a player from the players list
     * @param player - a player to remove
     */
    public void removePlayer(Player player) {
        info.removePlayer(player);
    }
    /**
     * Remove a coach from the coaches list
     * @param coach - a coach to remove
     */
    public void removeCoach(Coach coach) {
        info.removeCoach(coach);

    }

    /**
     * Remove a manager from the managers list
     * @param manager - a manager to remove
     */
    public void removeManager(TeamManager manager) {
        info.removeManager(manager);
    }
    /**
     * Remove an owner from the owners list
     * @param owner - an owner to remove
     */
    public void removeTeamOwner(TeamOwner owner) {
        info.removeTeamOwner(owner);
    }

    //endregion

    //region Add/Remove games

    /**
     *
     * Add a home game to the home games list
     * @param homeGame - a new home game
     */
    public void addHomeGame(Game homeGame) {
        if (!homeGames.containsKey(homeGame.getGID())) {
            homeGames.put(homeGame.getGID(), homeGame);
        }
    }
    /**
     *
     * Add an away game to the away games list
     * @param awayGame - a new away game
     */
    public void addAwayGame(Game awayGame) {
        if (!awayGames.containsKey(awayGame.getGID())) {
            awayGames.put(awayGame.getGID(), awayGame);
        }
    }

    /**
     *
     * Remove a home game from the home games list
     * @param homeGame - a home game to remove
     */
    public void removeHomeGame(Game homeGame) {
        homeGames.remove(homeGame.getGID());
    }
    /**
     *
     * Remove an away game from the away games list
     * @param awayGame - an away game to remove
     */
    public void removeAwayGame(Game awayGame) {
        awayGames.remove(awayGame.getGID());
    }
    //endregion

    //region Find methods
    /**
     * Given a user ID, finds the player in the team.
     * if the player is not in the team, returns NULL
     * @param PID - player ID
     * @return - player
     */
    public Player findPlayer(String PID){
        return info.findPlayer(PID);
    }

    /**
     * Given a user ID, finds the coach in the team.
     * if the coach is not in the team, returns NULL
     * @param CID - coach ID
     * @return - coach
     */
    public Coach findCoach(String CID){
        return info.findCoach(CID);
    }
    /**
     * Given a user ID, finds the manager in the team.
     * if the manager is not in the team, returns NULL
     * @param MID - manager ID
     * @return - manager
     */
    public TeamManager findManager(String MID){
        return info.findManager(MID);
    }
    /**
     * Given a user ID, finds the owner in the team.
     * if the owner is not in the team, returns NULL
     * @param OID - owner ID
     * @return - owner
     */
    public TeamOwner findTeamOwner(String OID){
        return info.findTeamOwner(OID);
    }
    /**
     * Given a game ID, finds the relevant home-game
     * if the game is not in the team, returns NULL
     * @param gid - game ID
     * @return - game
     */
    public Game findHomeGame(String gid){
        return homeGames.get(gid);
    }

    /**
     * Given a game ID, finds the relevant away-game
     * if the game is not in the team, returns NULL
     * @param gid - game ID
     * @return - game
     */
    public Game findAwayGame(String gid){
        return awayGames.get(gid);
    }
    //endregion
}
