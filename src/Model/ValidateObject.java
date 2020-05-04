package Model;

import AssociationAssets.League;

import AssociationAssets.Season;
import System.*;


public class ValidateObject {

    static Search search;

    /**
     * Validates a presence of a league in the system
     * @param leagueName-
     * @return League object if valid
     * @throws RecordException - No such league name in the system
     */
    public static League getValidatedLeague(String leagueName) throws RecordException {
        League league= search.getLeagueByLeagueName(leagueName);
        if (league == null) {
            throw new RecordException("League name " + leagueName + " does not exist.");
        }
        return league;
    }

    /**
     * Validates a presence of a specific season in the system
     * @param leagueName-
     * @param seasonYear-
     * @return Season object if valid
     * @throws RecordException - No such Season in the required league
     */
    public static Season getValidatedSeason(String leagueName, String seasonYear) throws RecordException {
        Season season = search.getSeasonByYear(/*leagueName,*/ seasonYear);
        if (season == null) {
            throw new RecordException("Season " + seasonYear + " does not exist in the requested league.");
        }
        return season;
    }


}
