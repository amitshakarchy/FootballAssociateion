package DB;

import AssociationAssets.League;

import java.util.HashMap;

public class LeagueDB {
    HashMap<String, League> allLeagues;

    public LeagueDB() {
        this.allLeagues = new HashMap<>();
    }
    public void addLeague(League newLeague,String leagueName){
        allLeagues.put(leagueName,newLeague);
    }
}
