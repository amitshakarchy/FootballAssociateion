package AssociationAssets;

import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.HashMap;

public class Team {

    int TID;
    Field homeField;
    AdditionalInfo info;
    HashMap<String, Game> homeGames;
    HashMap<String, Game> awayGames;
    Budget budget;

    public Team(int TID, Field homeField, Budget budget, Season season, TeamOwner owner, HashMap<String, Coach> coaches, HashMap<String, TeamManager> managers, HashMap<String, Player> players) {
        this.TID = TID;
        this.homeField = homeField;
        this.budget = budget;
        this.homeGames = new HashMap<>();
        this.awayGames = new HashMap<>();
        this.info = new AdditionalInfo(this, season, owner, coaches, managers, players);
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

    public Budget getBudget() {
        return budget;
    }

    public Season getSeason() {
        return info.getSeason();
    }

    public TeamOwner getOwner() {

        return info.getOwner();
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

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public void setTeam(Team team) {

        info.setTeam(team);
    }

    public void setSeason(Season season) {

        info.setSeason(season);
    }

    public void setOwner(TeamOwner owner) {

        info.setOwner(owner);
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
    //endregion

    //region Add/Remove from AdditionalInfo
    public void addPlayer(Player player) {
        info.addPlayer(player);
    }

    public void addCoach(Coach coach) {
        info.addCoach(coach);
    }

    public void addManager(TeamManager manager) {
        info.addManager(manager);
    }

    public void removePlayer(Player player) {
        info.removePlayer(player);
    }

    public void removeCoach(Coach coach) {
        info.removeCoach(coach);

    }

    public void removeManager(TeamManager manager) {
        info.removeManager(manager);
    }
    //endregion

    //region Add/Remove games
    public void addHomeGame(Game homeGame) {
        if (!homeGames.containsKey(homeGame.getGID())) {
            homeGames.put(homeGame.getGID(), homeGame);
        }
    }

    public void addAwayGame(Game awayGame) {
        if (!awayGames.containsKey(awayGame.getGID())) {
            awayGames.put(awayGame.getGID(), awayGame);
        }
    }

    public void removeHomeGame(Game homeGame) {
        if (homeGames.containsKey(homeGame.getGID())) {
            homeGames.remove(homeGame.getGID());
        }
    }

    public void removeAwayGame(Game awayGame) {
        if (awayGames.containsKey(awayGame.getGID())) {
            awayGames.remove(awayGame.getGID());
        }
    }
    //endregion


}
