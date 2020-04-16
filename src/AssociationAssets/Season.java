package AssociationAssets;

import java.util.HashMap;
import System.*;
/**
 * represent a season of football games.
 */
public class Season {

    String year;
    HashMap<String, SeasonLeagueBinder> leagueBinders; // Hold pairs of < league name,binder between the league and the season >
    HashMap<String,AdditionalInfo> teamAdditionalInfo;

    public Season(String year){
        this.year = year;
        leagueBinders= new HashMap<>();
        teamAdditionalInfo= new HashMap<>();
        // Write to the log
        Logger.getInstance().addActionToLogger("Season was created. Season's Year: "+year+".");
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
        leagueBinders.remove(league.getLeagueName());
    }

    /**
     *  adding team to the corresponding additionalInfo object
     * @param teamName
     * @param team
     */
    public void addTeamToSeason(String teamName, HashMap<String, AdditionalInfo> team) {
        this.teamAdditionalInfo.put(teamName,team.get(this.year));
    }

    public String viewProfile() {
        StringBuilder str= new StringBuilder("Season's year: " + this.year + "\n" +
                "Season's Leagues:\n");
        for (String league:leagueBinders.keySet()) {
            str.append(league).append(";");
        }
        return str.toString();
    }
}