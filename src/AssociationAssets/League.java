package AssociationAssets;

import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;
import java.util.HashMap;
import java.util.Map;

public class League {

    String name;
    HashMap <String,SeasonLeagueBinder> seasonBinders; // Hold pairs of <year,binder between this year's season and league>
    GamesAssigningPolicy assigningPolicy;


    public League(String name) {
        this.name = name;
        seasonBinders= new HashMap<>();
    }

    //region Getters & Setters
    public GamesAssigningPolicy getAssigningPolicy() {
        return assigningPolicy;
    }


    public void setAssigningPolicy(String year, GamesAssigningPolicy assigningPolicy){
       if(seasonBinders.containsKey(year)){
           SeasonLeagueBinder binder= seasonBinders.get(year);
           binder.setAssigningPolicy(assigningPolicy);
       }
    }

    public void setScoreTablePolicy(String year, ScoreTablePolicy scoreTablePolicy){
        if(seasonBinders.containsKey(year)){
            SeasonLeagueBinder binder= seasonBinders.get(year);
            binder.setScoreTablePolicy(scoreTablePolicy);
        }
    }
    public String getLeagueName() {
        return name;
    }
    public Map<String, SeasonLeagueBinder> getSeasonBinders() {
        return this.seasonBinders;
    }
    //endregion

    //region Adders
    /**
     *
     * @param season - the association representative should know and provide the season in order to bind correctly
     */
    public void addSeasonToLeague(Season season){
        if(!seasonBinders.containsKey(season.getYear())) {
            SeasonLeagueBinder binder= new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(),binder);
        }
    }

    /**
     *
     * @param year - the association representative should know and provide the year in order to bind to the right season
     */
    public void addSeasonToLeague(String year){
        if(!seasonBinders.containsKey(year)) {
            Season season = new Season(year);
            SeasonLeagueBinder binder= new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(),binder);
        }
    }

    public void addTeamsToLeague(String year,HashMap<String,Team>  teams){
            seasonBinders.get(year).addTeamsToLeague(teams);
    }

    public void addGamesToLeague(String year,HashMap<String,Game> Games){
        seasonBinders.get(year).addGamesToLeague(Games);
    }
    //endregion

}

