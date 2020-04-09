package AssociationAssets;

import Users.*;

import java.util.HashMap;

public class AdditionalInfo {

    Team team;
    Season season;
    TeamOwner owner;
    HashMap<String, Coach> coaches;
    HashMap<String, TeamManager> managers;
    HashMap<String, Player> players;

    public AdditionalInfo(Team team, Season season, TeamOwner owner, HashMap<String, Coach> coaches, HashMap<String, TeamManager> managers, HashMap<String, Player> players) {
        this.team = team;
        this.season = season;
        this.owner = owner;
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

    public TeamOwner getOwner() {
        return owner;
    }

    public void setOwner(TeamOwner owner) {
        this.owner = owner;
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

    public void addPlayer(Player player) {
        if (!players.containsKey(player.getUID())) {
            players.put(player.getUID(), player);
        }
    }

    public void addCoach(Coach coach) {
        if (!coaches.containsKey(coach.getUID())) {
            coaches.put(coach.getUID(), coach);
        }
    }

    public void addManager(TeamManager manager) {
        if (!managers.containsKey(manager.getUID())) {
            managers.put(manager.getUID(), manager);
        }
    }

    public void removePlayer(Player player) {
        if (players.containsKey(player.getUID())) {
            players.remove(player.getUID());
        }
    }

    public void removeCoach(Coach coach) {
        if (coaches.containsKey(coach.getUID())) {
            coaches.remove(coach.getUID());
        }

    }

    public void removeManager(TeamManager manager) {
        if (managers.containsKey(manager.getUID())) {
            managers.remove(manager.getUID());
        }
    }
}
