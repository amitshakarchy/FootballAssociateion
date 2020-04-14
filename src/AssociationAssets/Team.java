package AssociationAssets;

import Users.Coach;
import Users.Player;
import Users.TeamManager;
import Users.TeamOwner;

import java.util.ArrayList;
import java.util.HashMap;

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
    HashMap<String/*field name*/, Field> fields;
    HashMap<String/*season Year*/, AdditionalInfo> additionalInfoWithSeasons;
    HashMap<String/*GID*/, Game> homeGames;
    HashMap<String/*GID*/, Game> awayGames;
    TeamBudget budget;
    ETeamStatus isActive;
    TeamOwner teamOwner;
    //endregion


    public Team(int TID, String name, Season currentSeason, Field mainField, TeamBudget budget,TeamOwner teamOwner) {
        this.TID = TID;
        this.name = name;
        this.currentSeason = currentSeason;
        this.mainField = mainField;
        this.budget = budget;
        this.homeGames = new HashMap<>();
        this.awayGames = new HashMap<>();
        // TODO: 4/13/2020  need to check that this team owner is not owner of another team already
        this.isActive = ETeamStatus.ACTIVE;
        this.fields = new HashMap<>();
        fields.put(mainField.getName(), mainField);
        this.additionalInfoWithSeasons = new HashMap<>();
        this.teamOwner  = teamOwner;
    }

    //region Getters & Setters

    public int getTID() {
        return TID;
    }

    public String getName() {
        return name;
    }

    public Season getCurrentSeason() {
        return currentSeason;
    }

    public Field getMainField() {
        return mainField;
    }

    public HashMap<String, Field> getFields() {
        return fields;
    }

    public HashMap<String, AdditionalInfo> getAdditionalInfoWithSeasons() {
        return additionalInfoWithSeasons;
    }

    public HashMap<String, Game> getHomeGames() {
        return homeGames;
    }

    public HashMap<String, Game> getAwayGames() {
        return awayGames;
    }

    public TeamBudget getBudget() {
        return budget;
    }

    public ETeamStatus getIsActive() {
        return isActive;
    }

    public void setTID(int TID) {
        this.TID = TID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentSeason(Season currentSeason) {
        this.currentSeason = currentSeason;
    }

    public void setFields(HashMap<String, Field> fields) {
        this.fields = fields;
    }

    public void setAdditionalInfoWithSeasons(HashMap<String, AdditionalInfo> additionalInfoWithSeasons) {
        this.additionalInfoWithSeasons = additionalInfoWithSeasons;
    }

    public void setHomeGames(HashMap<String, Game> homeGames) {
        this.homeGames = homeGames;
    }

    public void setAwayGames(HashMap<String, Game> awayGames) {
        this.awayGames = awayGames;
    }

    public void setBudget(TeamBudget budget) {
        this.budget = budget;
    }

    public void setMainField(Field mainField) {
        this.mainField = mainField;
    }

    public void setIsActive(ETeamStatus isActive) {
        this.isActive = isActive;
    }

    public TeamOwner getTeamOwner() {
        return teamOwner;
    }

    //endregion
    /**
     * Given a user ID, finds the player in the team.
     * if the player is not in the team, returns NULL
     *
     * @param PID
     * @return
     **/
    public Player findPlayer(String PID) {
        AdditionalInfo info = this.additionalInfoWithSeasons.get(currentSeason.getYear());
        if(info == null){
            return null;
        }
        return info.findPlayer(PID);
    }

    /**
     * Given a user ID, finds the coach in the team.
     * if the coach is not in the team, returns NULL
     *
     * @param CID
     * @return
     **/
    public Coach findCoach(String CID) {
        AdditionalInfo info = this.additionalInfoWithSeasons.get(currentSeason.getYear());
        if(info == null){
            return null;
        }
        return info.findCoach(CID);
    }

    /**
     * Given a user ID, finds the manager in the team.
     * if the manager is not in the team, returns NULL
     *
     * @param MID
     * @return
     */
    public TeamManager findManager(String MID) {
        AdditionalInfo info = this.additionalInfoWithSeasons.get(currentSeason.getYear());
        if(info == null){
            return null;
        }
        return info.findManager(MID);
    }

    /**
     * Given a user ID, finds the owner in the team.
     * if the owner is not in the team, returns NULL
     *
     * @param OID
     * @return
     **/
    public TeamOwner findTeamOwner(String OID) {
        AdditionalInfo info = this.additionalInfoWithSeasons.get(currentSeason.getYear());
        if(info == null){
            return null;
        }
        return info.findTeamOwner(OID);
    }

    /**
     * Given a game ID, finds the relevant home-game
     * if the game is not in the team, returns NULL
     *
     * @param gid
     * @return
     **/
    public Game findHomeGame(int gid) {
        return homeGames.get(gid);
    }

    /**
     * Given a game ID, finds the relevant away-game
     * if the game is not in the team, returns NULL
     *
     * @param gid
     * @return
     */
    public Game findAwayGame(int gid) {
        return awayGames.get(gid);
    }
    //endregion

    public void addSeasonToTeam(Season season) {
        if (!additionalInfoWithSeasons.containsKey(season.getYear())) {
            AdditionalInfo additionalInfo = new AdditionalInfo(this, currentSeason);
            additionalInfoWithSeasons.put(season.getYear(), additionalInfo);
            season.addTeamToSeason(name, additionalInfoWithSeasons);
            this.teamOwner.addAdditionalInfo(additionalInfo);
        }
    }

    public void addField(Field field){
        if(field == null){
            return;
        }
        if(!this.fields.containsKey(field.getName())){
            this.fields.put(field.getName(),field);
        }
    }

    public void removeField(String fieldName){
        this.fields.remove(fieldName);
    }


    @Override
    public String toString() {
        return "Team{" +
                "TID=" + TID +
                ", name='" + name + '\'' +
                ", currentSeason=" + currentSeason +
                ", mainField=" + mainField +
                ", fields=" + fields +
                ", additionalInfoWithSeasons=" + additionalInfoWithSeasons +
                ", homeGames=" + homeGames +
                ", awayGames=" + awayGames +
                ", budget=" + budget +
                ", isActive=" + isActive +
                ", teamOwner=" + teamOwner +
                '}';
    }
}
