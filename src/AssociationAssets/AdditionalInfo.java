package AssociationAssets;

import Users.*;

import java.util.HashMap;

public class AdditionalInfo {

    Team team;
    Season season;
    HashMap<String,TeamOwner> owners;
    HashMap<String, Coach> coaches;
    HashMap<String, TeamManager> managers;
    HashMap<String, Player> players;

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

    public void addTeamOwner(TeamOwner owner) {
        if (!owners.containsKey(owner.getUID())) {
            owners.put(owner.getUID(), owner);
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
    public void removeTeamOwner(TeamOwner owner) {
        if (owners.containsKey(owner.getUID())) {
            owners.remove(owner.getUID());
        }
    }

    public Player findPlayer(String pid) {
        if(players.containsKey(pid))
            return players.get(pid);
        else return null;
    }

    public Coach findCoach(String cid){
        if(coaches.containsKey(cid))
            return coaches.get(cid);
        else return null;
    }

    public TeamManager findManager(String mid){
        if(managers.containsKey(mid))
            return managers.get(mid);
        else return null;
    }

    public TeamOwner findTeamOwner(String oid){
        if(owners.containsKey(oid))
            return owners.get(oid);
        else return null;
    }


}
