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
     * @param league
     */
    public void addLeagueToSeason(League league) {
        if (!leagueBinders.containsKey(league.getLeagueName())) {
            SeasonLeagueBinder binder= league.getSeasonBinders().get(year);
            leagueBinders.put(league.getLeagueName(), binder);
        }
    }
    //endregion

}