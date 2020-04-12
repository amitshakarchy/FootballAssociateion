package AssociationAssets;

import System.FootballSystem;
import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class AdditionalInfo {

    //region Fields
    Team team;
    Season season;
    HashMap<String/*username of the teamOwner who nominate*/
            , ArrayList<String> /*user names list of the team Owners*/> owners;
    HashSet<String> coaches;
    HashMap<String/*username of the teamOwner who nominate*/
            , ArrayList<String> /*user names list of the team mangers*/> managers;
    HashSet<String> players;
    HashSet<String> teamManagersHashSet;
    HashSet<String> teamOwnersHashSet;

    //endregion


    public AdditionalInfo(Team team, Season season, HashMap<String, ArrayList<String>> owners, HashSet<String> coaches, HashMap<String, ArrayList<String>> managers, HashSet<String> players) {
        this.team = team;
        this.season = season;
        this.owners = owners;
        this.coaches = coaches;
        this.managers = managers;
        this.players = players;
        this.teamManagersHashSet = new HashSet<>();
        this.teamOwnersHashSet = new HashSet<>();
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
     *
     * @param player , the user name of the player
     */
    public boolean addPlayer(String player) {
        if (!players.contains(player)) {
            players.add(player);
            return true;
        }
        return false;
    }

    /**
     * Adds a coach to the team, if he is not exist.
     *
     * @param coach , the user name of the coach
     */
    public boolean addCoach(String coach) {
        if (!coaches.contains(coach)) {
            coaches.add(coach);
            return true;
        }
        return false;
    }

    /**
     * Adds a TeamManager to the team, if he is not exist.
     *
     * @param teamManager           , the user name of teamManager.
     * @param teamOwnerWhoNominate, the user name of the team owner who nominated this team
     *                              manager.
     */
    public boolean addManager(String teamManager, String teamOwnerWhoNominate) {
        ArrayList temp = new ArrayList();
        if (managers.containsKey(teamOwnerWhoNominate)) {
            temp = managers.get(teamOwnerWhoNominate);
            // this team manager already exists in this team & season
            if (temp.contains(teamManager)) {
                return false;
            }
            temp.add(teamManager);
            managers.put(teamOwnerWhoNominate, temp);
            this.teamManagersHashSet.add(teamManager);
        } else {
            temp.add(teamManager);
            managers.put(teamOwnerWhoNominate, temp);
            this.teamManagersHashSet.add(teamManager);
        }
        return true;
    }

    /**
     * Adds a TeamOwner to the team, if he is not exist.
     *
     * @param teamOwner,            the user name of teamOwner.
     * @param teamOwnerWhoNominate, the user name of the teamOwner who nominate this team owner.
     */
    public boolean addTeamOwner(String teamOwner, String teamOwnerWhoNominate) {
        ArrayList temp = new ArrayList();
        if (owners.containsKey(teamOwnerWhoNominate)) {
            temp = owners.get(teamOwnerWhoNominate);
            // this team owner already exists in this team & season
            if (temp.contains(teamOwner)) {
                return false;
            }
            temp.add(teamOwner);
            owners.put(teamOwnerWhoNominate, temp);
            this.teamOwnersHashSet.add(teamOwner);
        } else {
            temp.add(teamOwner);
            owners.put(teamOwnerWhoNominate, temp);
            this.teamOwnersHashSet.add(teamOwner);
        }
        return true;
    }


    /**
     * Removes a player from the team, if he exists.
     *
     * @param playerUName - a player to remove from the team
     */
    public void removePlayer(String playerUName) {
        if (players.contains(playerUName)) {
            players.remove(playerUName);
        }
    }

    /**
     * Removes a coach from the team, if he exists.
     *
     * @param coachUserName - String coach ID to remove from the team
     */
    public void removeCoach(String coachUserName) {
        if (coaches.contains(coachUserName)) {
            coaches.remove(coachUserName);
        }

    }

    /**
     * Removes a manager from the team, if he exists.
     *
     * @param managerUserName - a manager to remove from the team
     */
    public void removeManager(String managerUserName, String userNameWhoNominated) {
        if (managers.containsKey(userNameWhoNominated)) {
            managers.get(userNameWhoNominated).remove(managerUserName);
        }
    }

    /**
     * Removes an owner from the team, if he exists.
     *
     * @param ownerUserName - an owner to remove from the team
     */
    public void removeTeamOwner(String ownerUserName, String userNameWhoNominated) {
        if (owners.containsKey(userNameWhoNominated)) {
            owners.get(userNameWhoNominated).remove(ownerUserName);
        }
    }


    /**
     * find a player in the team, given its username.
     * If he does not exist, returns NULL
     *
     * @param username - player's username
     * @return
     */
    public Player findPlayer(String username) {
        if (players.contains(username))
            return (Player) FootballSystem.getInstance().getFanByUserName(username);
        else return null;
    }

    /**
     * find a coach in the team, given its username.
     * If he does not exist, returns NULL
     *
     * @param username - player's username
     * @return
     */
    public Coach findCoach(String username) {
        if (coaches.contains(username))
            return (Coach) FootballSystem.getInstance().getFanByUserName(username);
        else return null;
    }

    /**
     * find a manager in the team, given its username.
     * If he does not exist, returns NULL
     *
     * @param username - player's username
     * @return
     */
    public TeamManager findManager(String username) {
        if (teamManagersHashSet.contains(username)) {
            return (TeamManager) FootballSystem.getInstance().getFanByUserName(username);
        }
        return null;
    }

    /**
     * find an owner in the team, given its username.
     * If he does not exist, returns NULL
     *
     * @param username - player's username
     * @return
     */
    public TeamOwner findTeamOwner(String username) {
        if (teamOwnersHashSet.contains(username)) {
            return (TeamOwner) FootballSystem.getInstance().getFanByUserName(username);
        }
        return null;
    }

    public boolean whoNominateTeamManager(String teamOwner, String userNameWhoNominated) {
        if (this.managers.containsKey(userNameWhoNominated)) {
            if (this.managers.get(userNameWhoNominated).contains(teamOwner)) {
                return true;
            }
        }
        return false;
    }

    public boolean whoNominateTeamOwner(String teamManager, String userNameWhoNominated) {
        if (this.owners.containsKey(userNameWhoNominated)) {
            if (this.owners.get(userNameWhoNominated).contains(teamManager)) {
                return true;
            }
        }
        return false;
    }

    public void removeAllNominations(String userNameWhoNominated) {
        if (this.managers.containsKey(userNameWhoNominated)) {
            this.managers.get(userNameWhoNominated).clear();
        }
        if (this.owners.containsKey(userNameWhoNominated)) {
            this.owners.get(userNameWhoNominated).clear();
        }
    }
}
