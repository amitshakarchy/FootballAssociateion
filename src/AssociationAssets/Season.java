package AssociationAssets;

import java.util.HashMap;

/**
 * represent a season of football games.
 */
public class Season {

    String year;
    HashMap<String, SeasonLeagueBinder> leagueBinders; // Hold pairs of < league name,binder between the league and the season >

    public Season(String year){
        this.year = year;
        leagueBinders= new HashMap<>();
    }

    //region Setter baseter
    public String getYear() {
        return year;
    }
    //endregion

    //region Adders

    /**
     * adding league to the corresponding leagueBinder object
     * @param leagueName
     * @param bindersHashMap
     */
    public void addLeagueToSeason(String leagueName, HashMap<String, SeasonLeagueBinder> bindersHashMap) {

        this.leagueBinders.put(leagueName,bindersHashMap.get(this.year));
    }
    //endregion
    public void removeLeagueFromSeason(League league) {
        if (leagueBinders.containsKey(league.getLeagueName())) {
            leagueBinders.remove(league.getLeagueName());
        }
    }
}