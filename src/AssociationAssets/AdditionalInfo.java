package AssociationAssets;

import Users.*;

import java.util.HashMap;

public class AdditionalInfo {

    //region Fields
    Team team;
    Season season;
    HashMap<String,TeamOwner> owners;
    HashMap<String, Coach> coaches;
    HashMap<String, TeamManager> managers;
    HashMap<String, Player> players;
    //endregion

    /**
     * Constructor.
     * @param season - The season in which the game is in.
     * @param owners - A Hashmap of all team owners.
     * @param coaches - A Hashmap of all team coaches.
     * @param managers - A Hashmap of all team managers.
     * @param players - A Hashmap of all team players.
     */
    public AdditionalInfo(Season season, HashMap<String,TeamOwner> owners, HashMap<String, Coach> coaches, HashMap<String, TeamManager> managers, HashMap<String, Player> players) {
        this.season = season;
        this.owners = owners;
        this.coaches = coaches;
        this.managers = managers;
        this.players = players;
    }


    //region Getters & Setters
    public Team getTeam() {

        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public HashMap<String,TeamOwner> getOwners() {
        return owners;
    }

    public void setOwners(HashMap<String,TeamOwner> owner) {
        this.owners = owners;
    }

    public HashMap<String, Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(HashMap<String, Coach> coaches) {
        this.coaches = coaches;
    }

    public HashMap<String, TeamManager> getManagers() {
        return managers;
    }

    public void setManagers(HashMap<String, TeamManager> managers) {
        this.managers = managers;
    }

    public HashMap<String, Player> getPlayers() {
        return players;
    }

    public void setPlayers(HashMap<String, Player> players) {
        this.players = players;
    }
    //endregion

    /**
     * Adds a player to the team, if he is not exist.
     * @param player - a new player to add to the team
     */
    public void addPlayer(Player player) {
        if (!players.containsKey(player.getUserName())) {
            players.put(player.getUserName(), player);
        }
    }
    /**
     * Adds a coach to the team, if he is not exist.
     * @param coach - a new coach to add to the team
     */
    public void addCoach(Coach coach) {
        if (!coaches.containsKey(coach.getUserName())) {
            coaches.put(coach.getUserName(), coach);
        }
    }
    /**
     * Adds a manager to the team, if he is not exist.
     * @param manager - a new manager to add to the team
     */
    public void addManager(TeamManager manager) {
        if (!managers.containsKey(manager.getUserName())) {
            managers.put(manager.getUserName(), manager);
        }
    }
    /**
     * Adds an owner to the team, if he is not exist.
     * @param owner - a new owner to add to the team
     */
    public void addTeamOwner(TeamOwner owner) {
        if (!owners.containsKey(owner.getUserName())) {
            owners.put(owner.getUserName(), owner);
        }
    }
    /**
     * Removes a player from the team, if he exists.
     * @param player - a player to remove from the team
     */
    public void removePlayer(Player player) {
        if (players.containsKey(player.getUserName())) {
            players.remove(player.getUserName());
        }
    }
    /**
     * Removes a coach from the team, if he exists.
     * @param coach - a coach to remove from the team
     */
    public void removeCoach(Coach coach) {
        if (coaches.containsKey(coach.getUserName())) {
            coaches.remove(coach.getUserName());
        }

    }
    /**
     * Removes a manager from the team, if he exists.
     * @param manager - a manager to remove from the team
     */
    public void removeManager(TeamManager manager) {
        if (managers.containsKey(manager.getUserName())) {
            managers.remove(manager.getUserName());
        }
    }

    /**
     * Removes an owner from the team, if he exists.
     * @param owner - an owner to remove from the team
     */
    public void removeTeamOwner(TeamOwner owner) {
        if (owners.containsKey(owner.getUserName())) {
            owners.remove(owner.getUserName());
        }
    }


    /**
     * find a player in the team, given its username.
     * If he does not exist, returns NULL
     * @param username - player's username
     * @return
     */
    public Player findPlayer(String username) {
        if(players.containsKey(username))
            return players.get(username);
        else return null;
    }
    /**
     * find a coach in the team, given its username.
     * If he does not exist, returns NULL
     * @param username - player's username
     * @return
     */
    public Coach findCoach(String username){
        if(coaches.containsKey(username))
            return coaches.get(username);
        else return null;
    }
    /**
     * find a manager in the team, given its username.
     * If he does not exist, returns NULL
     * @param username - player's username
     * @return
     */
    public TeamManager findManager(String username){
        if(managers.containsKey(username))
            return managers.get(username);
        else return null;
    }
    /**
     * find an owner in the team, given its username.
     * If he does not exist, returns NULL
     * @param username - player's username
     * @return
     */
    public TeamOwner findTeamOwner(String username){
        if(owners.containsKey(username))
            return owners.get(username);
        else return null;
    }


}
