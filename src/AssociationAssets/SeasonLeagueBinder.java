package AssociationAssets;

import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;

import java.util.HashMap;

/**
 * this class goal is to connect between pairs of league and season.
 */
public class SeasonLeagueBinder {

    Season season;
    League league;

    HashMap<String,Team> teams;
    HashMap<String, Game> games;
    ScoreTablePolicy scoreTablePolicy;// not in the constructor
    GamesAssigningPolicy assigningPolicy;// not in the constructor


    public SeasonLeagueBinder(Season season, League league) {
        this.season = season;
        this.league = league;
        teams= new HashMap<>();
        games= new HashMap<>();
    }


    //region Getters & Setters
    public void setAssigningPolicy(GamesAssigningPolicy assigningPolicy) { this.assigningPolicy = assigningPolicy; }
    public void setScoreTablePolicy(ScoreTablePolicy scoreTablePolicy) {
        this.scoreTablePolicy = scoreTablePolicy;
    }
    public ScoreTablePolicy getScoreTablePolicy() {
        return scoreTablePolicy;
    }
    //endregion


    //region Adders

    /**
     * adding teams to this specific combination of season and league
     * @param teams
     */
    public void addTeamsToLeague(HashMap<String, Team> teams) {
        this.teams.putAll(teams);
    }
    /**
     * adding games to this specific combination of season and league
     * @param games
     */
    public void addGamesToLeague(HashMap<String, Game> games) {
        this.games.putAll(games);
    }
    //endregion
}
