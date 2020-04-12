package AssociationAssets;

import Users.*;
import System.FootballSystem;
import java.util.HashMap;
import java.util.HashSet;

public class AdditionalInfo {

    //region Fields
    Team team;
    Season season;
    HashSet<String> owners;
    HashSet<String> coaches;
    HashSet<String> managers;
    HashSet<String> players;
    //endregion


    public AdditionalInfo(Team team, Season season, HashSet<String> owners, HashSet<String> coaches, HashSet<String> managers, HashSet<String> players) {
        this.team = team;
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
    //endregion

    /**
     * Adds a player to the team, if he is not exist.
     * @param player , the user name of the player
     */
    public void addPlayer(String player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    /**
     * Adds a coach to the team, if he is not exist.
     * @param coach , the user name of the coach
     */
    public void addCoach(String coach) {
        if (!coaches.contains(coach)) {
            coaches.add(coach);
        }
    }

    /**
     * Adds a TeamManager to the team, if he is not exist.
     * @param teamManager , the user name of teamManager.
     */
    public void addManager(String teamManager) {
            if (!managers.contains(teamManager)) {
                managers.add(teamManager);
            }
    }

    /**
     * Adds a TeamManager to the team, if he is not exist.
     * @param teamManager , the user name of teamManager.
     */
    public void addTeamOwner(String teamManager) {
        if (!managers.contains(teamManager)) {
            managers.add(teamManager);
        }
    }


    /**
     * Removes a player from the team, if he exists.
     * @param playerUName - a player to remove from the team
     */
    public void removePlayer(String playerUName) {
        if (players.contains(playerUName)) {
            players.remove(playerUName);
        }
    }
    /**
     * Removes a coach from the team, if he exists.
     * @param coachUserName - String coach ID to remove from the team
     */
    public void removeCoach(String coachUserName) {
        if (coaches.contains(coachUserName)) {
            coaches.remove(coachUserName);
        }

    }
    /**
     * Removes a manager from the team, if he exists.
     * @param managerUserName - a manager to remove from the team
     */
    public void removeManager(String managerUserName) {
        if (managers.contains(managerUserName)) {
            managers.remove(managerUserName);
        }
    }

    /**
     * Removes an owner from the team, if he exists.
     * @param ownerUserName - an owner to remove from the team
     */
    public void removeTeamOwner(String ownerUserName) {
        if (owners.contains(ownerUserName)) {
            owners.remove(ownerUserName);
        }
    }


    /**
     * find a player in the team, given its username.
     * If he does not exist, returns NULL
     * @param username - player's username
     * @return
     */
    public Player findPlayer(String username) {
        if(players.contains(username))
            return FootballSystem.getInstance().getUserByUserName(username);
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
