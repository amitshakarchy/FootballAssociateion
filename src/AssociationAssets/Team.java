package AssociationAssets;

import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Class Team represents a team. It holds a team's home and away games, it's players and
 * all other team assets.
 */
public class Team {

    //region Fields
    int TID;
    String name;
    Season currentSeason;
    Field mainField;
    HashMap<String/*field name*/,Field> fields;
    HashMap<String, ArrayList<String>> owners;
    HashMap<String/*season Year*/,AdditionalInfo> additionalInfoWithSeasons ;
    HashSet<String> coaches;
    HashMap<String, ArrayList<String>> managers;
    //HashMap<Integer, Game> homeGames;
    // HashMap<Integer, Game> awayGames;
    //endregion

    /**
     * The constructor receives all needed information in order to generate an AdditionalInfo
     * object, and binds it with the team.
     * @param TID
     * @param homeField
     * @param owners
     * @param coaches
     * @param managers
     * @param players
     */
    public Team(int TID, String name,Season season, Field homeField, HashMap<String, ArrayList<String>>  owners, HashSet<String> coaches, HashMap<String, ArrayList<String>> managers, HashSet<String> players) {
        this.TID = TID;
        this.name = name;
        this.currentSeason = season;
        this.owners = owners;
        this.managers = managers;
        this.mainField = homeField;
        fields = new HashMap<>();
     //   this.homeGames = new HashMap<>();
    //    this.awayGames = new HashMap<>();
        additionalInfoWithSeasons = new HashMap<>();
    }

    //region Getters
    public int getTID() {
        return TID;
    }
    public String getName() {
        return name;
    }
    public Field getHomeField() {
        return mainField;
    }
    public HashMap<String, Field> getFields() { return fields; }

    public Season getCurrentSeasonSeason() {
        return currentSeason;
    }


    //endregion


    //region Setters
    public void setHomeField(Field homeField) {
        this.mainField = homeField;
    }


    public void setMainField(Field mainField) {
        this.mainField = mainField;
    }


    //region Find methods
    /**
     * Given a user ID, finds the player in the team.
     * if the player is not in the team, returns NULL
     * @param PID
     * @return
     */
    public Player findPlayer(String PID){
        Player p=info.findPlayer(PID);
        return p;
    }

    /**
     * Given a user ID, finds the coach in the team.
     * if the coach is not in the team, returns NULL
     * @param CID
     * @return
     */
    public Coach findCoach(String CID){
        Coach c=info.findCoach(CID);
        return c;
    }
    /**
     * Given a user ID, finds the manager in the team.
     * if the manager is not in the team, returns NULL
     * @param MID
     * @return
     */
    public TeamManager findManager(String MID){
        TeamManager m=info.findManager(MID);
        return m;
    }
    /**
     * Given a user ID, finds the owner in the team.
     * if the owner is not in the team, returns NULL
     * @param OID
     * @return
     */
    public TeamOwner findTeamOwner(String OID){
        TeamOwner o=info.findTeamOwner(OID);
        return o;
    }
    /**
     * Given a game ID, finds the relevant home-game
     * if the game is not in the team, returns NULL
     * @param gid
     * @return
     */
    public Game findHomeGame(int gid){
        return homeGames.get(gid);
    }

    /**
     * Given a game ID, finds the relevant away-game
     * if the game is not in the team, returns NULL
     * @param gid
     * @return
     */
    public Game findAwayGame(int gid){
        return awayGames.get(gid);
    }
    //endregion

    public void addSeasonToTeam(Season season){
        if(!additionalInfoWithSeasons.containsKey(season.getYear())) {
            AdditionalInfo additionalInfo= new AdditionalInfo(this,currentSeason,owners,coaches,ma );
            additionalInfo.put(season.getYear(),binder);
            season.addLeagueToSeason(name,seasonBinders);
        }
    }
}
