package AssociationAssets;

import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;
import java.util.HashMap;
import java.util.Map;

/**
 * Class league represents one type of league, reoccurring every year.
 * It holds a bind between a league and a season, defined by its year.
 * Aouthors: Amit Shakarchy, Alon Gutman
 */
public class League {

    //region Fields
    String name;
    HashMap <String,SeasonLeagueBinder> seasonBinders; // Hold pairs of <year,binder between this year's season and league>
    //endregion

    /**
     * Constructor. receives a league's name, and initializes the league's season binder hashmap.
     * @param name - name of the new league.
     */
    public League(String name) {
        this.name = name;
        seasonBinders= new HashMap<>();
    }

    //region Getters & Setters

    /**
     * Returns the Games Assigning Policy of a specific season in the league
     * @param year - A season's year
     * @return - Games Assigning Policy
     */
    public GamesAssigningPolicy getAssigningPolicy(String year) {
        return seasonBinders.get(year).getAssigningPolicy();
    }

    /**
     *  Set's the games assigning policy, in a specific season in the league.
     * @param year - A season's year
     * @param assigningPolicy - Games Assigning Policy
     */
    public void setAssigningPolicy(String year, GamesAssigningPolicy assigningPolicy){
       // Check if the league has the given season in it.
        if(seasonBinders.containsKey(year)){
           SeasonLeagueBinder binder= seasonBinders.get(year);
           binder.setAssigningPolicy(assigningPolicy);
       }
    }

    /**
     * Set's the Score Table Policy, in a specific season in the league.
     * @param year - A season's year
     * @param scoreTablePolicy - The Score Table Policy
     */
    public void setScoreTablePolicy(String year, ScoreTablePolicy scoreTablePolicy){
        // Check if the league has the given season in it.
        if(seasonBinders.containsKey(year)){
            SeasonLeagueBinder binder= seasonBinders.get(year);
            binder.setScoreTablePolicy(scoreTablePolicy);
        }
    }
    /**
     * Returns the Score Table Policy of a specific season in the league
     * @param year - A season's year
     * @return - Score Table Policy
     */
    public  ScoreTablePolicy getScoreTablePolicy(String year){
        return seasonBinders.get(year).getScoreTablePolicy();
    }
    /**
     * Returns the Teams of a specific season in the league
     * @param year - A season's year
     * @return - Teams
     */
    public HashMap<String,Team> getTeams(String year){
        return seasonBinders.get(year).getTeams();
    }
    /**
     * Returns the Games of a specific season in the league
     * @param year - A season's year
     * @return - Games
     */
    public HashMap<String,Game> getGames(String year){
        return  seasonBinders.get(year).getGames();
    }

    /**
     * Returns the league's name
     * @return - name
     */
    public String getLeagueName() {
        return name;
    }

    /**
     * Returns the league's season Binders
     * @return - season Binders
     */
    public Map<String, SeasonLeagueBinder> getSeasonBinders() {
        return this.seasonBinders;
    }
    //endregion

    //region Adders
    /**
     * Adds a new season to an existing league. This method receives a season.
     * @param season - the association representative should know and provide the season in order to bind correctly
     */
    public void addSeasonToLeague(Season season){
        if(!seasonBinders.containsKey(season.getYear())) {
            SeasonLeagueBinder binder= new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(),binder);
        }
    }

    /**
     * Adds a new season to an existing league. This method creates the season.
     * @param year - the association representative should know and provide the year in order to bind to the right season
     */
    public void addSeasonToLeague(String year){
        if(!seasonBinders.containsKey(year)) {
            Season season = new Season(year);
            SeasonLeagueBinder binder= new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(),binder);
        }
    }

    /**
     * Add teams to the league
     * @param year - A season's year
     * @param teams - All teams in the league, in a specific season
     */
    public void addTeamsToLeague(String year,HashMap<String,Team>  teams){
            seasonBinders.get(year).addTeamsToLeague(teams);
    }

    /**
     * Add games to the league
     * @param year - A season's year
     * @param games - All Games in the league, in a specific season
     */
    public void addGamesToLeague(String year,HashMap<String,Game> games){
        seasonBinders.get(year).addGamesToLeague(games);
    }
    //endregion

}

