package Users;
import AssociationAssets.*;
import java.util.ArrayList;
import java.util.List;

public class TeamManager extends AUser {
    private List<AdditionalInfo> additionalInfoList;

    public TeamManager(String UID, String fName, String lName) {
        super(UID, fName, lName);
        this.additionalInfoList = new ArrayList<>();
    }

    public List<AdditionalInfo> getAdditionalInfoList() {
        return additionalInfoList;
    }

    public void setAdditionalInfoList(List<AdditionalInfo> additionalInfoList) {
        this.additionalInfoList = additionalInfoList;
    }

    public void addAdditionalInfo(AdditionalInfo additionalInfo) {
        if(additionalInfo != null) {
            this.additionalInfoList.add(additionalInfo);
        }
    }

    public void removeAdditionalInfo(AdditionalInfo additionalInfo) {
        if(additionalInfo != null) {
            this.additionalInfoList.remove(additionalInfo);
        }
    }


    public void setHomeField(Team team, Season season,Field homeField) {
        if(team == null || season == null){
            return;
        }
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if(additionalInfoToSearch != null) {
            additionalInfoToSearch.getTeam().setHomeField(homeField);
        }
    }

    private AdditionalInfo getAdditionalInfo(Team team, Season season) {
        AdditionalInfo additionalInfoToSearch = null;
        for (AdditionalInfo additionalInfo:
             this.additionalInfoList) {
            if(additionalInfo.getTeam().equals(team) && additionalInfo.getSeason().equals(season)) {
                additionalInfoToSearch = additionalInfo;
            }
        }
        return additionalInfoToSearch;
    }

    /**public void setBudget(Budget budget, Team team, Season season) {
        if(team == null || season == null){
            return;
        }
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if(additionalInfoToSearch != null) {
            additionalInfoToSearch.getTeam().setBudget(budget);
        }
    }
    **/
    public void addPlayer(Player player,Team team, Season season) {
            if(team == null || season == null){
                return;
            }
            AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
            if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().addPlayer(player);
            }
    }

    public void addCoach(Coach coach,Team team, Season season) {
            if(team == null || season == null){
                return;
            }
            AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
            if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().addCoach(coach);
            }
        }

    public void removePlayer(Player player,Team team, Season season) {
        if(team == null || season == null){
            return;
        }
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if(additionalInfoToSearch != null) {
            additionalInfoToSearch.getTeam().removePlayer(player);
        }
    }

    public void removeCoach(Coach coach,Team team, Season season) {
        if(team == null || season == null){
            return;
        }
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if(additionalInfoToSearch != null) {
            additionalInfoToSearch.getTeam().removeCoach(coach);
        }
    }

    public void addHomeGame(Game homeGame, Team team,Season season) {
        if(team == null || season == null){
                return;
        }
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().addHomeGame(homeGame);
            }
    }

    public void addAwayGame(Game awayGame, Team team,Season season) {
            if(team == null || season == null){
                return;
            }
            AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
            if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().addAwayGame(awayGame);
            }
    }

    public void removeHomeGame(Game homeGame, Team team,Season season) {
            if(team == null || season == null){
                return;
            }
            AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
            if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().removeHomeGame(homeGame);
            }
    }

    public void removeAwayGame(Game awayGame, Team team,Season season) {
            if(team == null || season == null){
                return;
            }
            AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
            if(additionalInfoToSearch != null) {
                additionalInfoToSearch.getTeam().removeAwayGame(awayGame);
            }
        }


    }


