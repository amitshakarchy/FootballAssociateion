package AssociationAssets;

import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import System.*;

public class League {

    String name;
    HashMap<String, SeasonLeagueBinder> seasonBinders; // Hold pairs of <year,binder between this year's season and league>
    GamesAssigningPolicy assigningPolicy;


    public League(String name) {
        /*
        LocalDate date = LocalDate.now();
        LocalTime now = LocalTime.now();
        // Write to the log
        Logger.getInstance().addActionToLogger(date + " " + now + "League was created. League Name: "+name+".");
         */
        this.name = name;
        seasonBinders = new HashMap<>();
    }

    //region Getters & Setters
    public GamesAssigningPolicy getAssigningPolicy() {
        return assigningPolicy;
    }


    public void setAssigningPolicy(String year, GamesAssigningPolicy assigningPolicy) {
        if (seasonBinders.containsKey(year)) {
            SeasonLeagueBinder binder = seasonBinders.get(year);
            binder.setAssigningPolicy(assigningPolicy);
        }
    }

    public void setScoreTablePolicy(String year, ScoreTablePolicy scoreTablePolicy) {
        if (seasonBinders.containsKey(year)) {
            SeasonLeagueBinder binder = seasonBinders.get(year);
            binder.setScoreTablePolicy(scoreTablePolicy);
        }
    }

    public ScoreTablePolicy getScoreTablePolicy(String year) {
        return seasonBinders.get(year).getScoreTablePolicy();
    }

    public HashMap<String, Team> getTeams(String year) {
        return seasonBinders.get(year).getTeams();
    }

    public HashMap<String, Game> getGames(String year) {
        return seasonBinders.get(year).getGames();
    }

    public String getLeagueName() {
        return name;
    }

    public Map<String, SeasonLeagueBinder> getSeasonBinders() {
        return this.seasonBinders;
    }

    public void setSeasonBinders(HashMap<String, SeasonLeagueBinder> seasonBinders) {
        this.seasonBinders = seasonBinders;
    }

    //endregion

    //region Adders

    /**
     * Add a binder to the relevant hashmap
     * @param year - season's year
     * @param binder -  new binder
     */
    public void addSeasonLeagueBinder(String year, SeasonLeagueBinder binder){
        seasonBinders.put(year,binder);
    }


    /**
     * @param season - the association representative should know and provide the season in order to bind correctly.
     *               this function also call to same function in season class, in order to make
     *               the same connection with the same binder.
     */
    public void addSeasonToLeague(Season season) {
        if (!seasonBinders.containsKey(season.getYear())) {
            SeasonLeagueBinder binder = new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(), binder);
            season.addLeagueToSeason(name, seasonBinders);
        }
    }

    /**
     * @param year - the association representative should know and provide the year in order to bind to the right season
     */
    public void addSeasonToLeague(String year) {
        if (!seasonBinders.containsKey(year)) {
            Season season = new Season(year);
            SeasonLeagueBinder binder = new SeasonLeagueBinder(season, this);
            seasonBinders.put(season.getYear(), binder);
        }
    }

    public void addTeamsToLeague(String year, HashMap<String, Team> teams) {
        if (this.seasonBinders.containsKey(year)) {
            seasonBinders.get(year).addTeamsToLeague(teams);
        }
    }

    public void addGamesToLeague(String year, HashMap<String, Game> Games) {
        seasonBinders.get(year).addGamesToLeague(Games);
    }

    public String viewProfile() {

        StringBuilder str = new StringBuilder("League name: " + this.name + "\n" +
                "Seasons under this league: \n");
        for (String year : seasonBinders.keySet()) {
            str.append(year).append("; ");
        }
        return str.toString();
    }
    //endregion

}

