package AssociationAssets;

import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;
import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;

/**
 * this class goal is to connect between pairs of league and season.
 */
public class SeasonLeagueBinder {

    Season season;
    League league;
    HashMap<String,Team> teams;
    HashMap<String, Game> games;
    HashMap<String /*team name*/,Integer/*points*/> leagueTable;
    ScoreTablePolicy scoreTablePolicy;// not in the constructor
    GamesAssigningPolicy assigningPolicy;// not in the constructor


    public SeasonLeagueBinder(Season season, League league) {
        this.season = season;
        this.league = league;
        teams= new HashMap<>();
        games= new HashMap<>();
        leagueTable = new HashMap<>();
    }


    //region Getters & Setters
    public void setAssigningPolicy(GamesAssigningPolicy assigningPolicy) { this.assigningPolicy = assigningPolicy; }
    public void setScoreTablePolicy(ScoreTablePolicy scoreTablePolicy) {
        this.scoreTablePolicy = scoreTablePolicy;
    }
    public ScoreTablePolicy getScoreTablePolicy() {
        return scoreTablePolicy;
    }
    public HashMap<String, Team> getTeams() { return teams; }
    public HashMap<String, Game> getGames() { return games; }

    public Season getSeason() {
        return season;
    }
    public HashMap<String, Integer> getLeagueTable() {
        return leagueTable;
    }

    public League getLeague() {
        return league;
    }

    public GamesAssigningPolicy getAssigningPolicy() {
        return assigningPolicy;
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
    //TODO remove game & remove team functions (not sure this is necessary)


    //endregion
}
