package DB;

import AssociationAssets.Team;

import java.util.HashMap;

public class TeamDB {

    HashMap<Integer, Team> allTeams;

    public TeamDB() {
        this.allTeams = new HashMap<>();
    }
    public void addTeam(Team newTeam,int team){
        allTeams.put(team,newTeam);
    }
    public void removeTeam(int team){
        this.allTeams.remove(team);
    }

    public HashMap<Integer, Team> getAllTeams() {
        return allTeams;
    }
}
