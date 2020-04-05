package AssociationAssets;
import Users.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdditionalInfo {
    TeamManager manager;
    TeamOwner owner;
    Coach coach;
    Map<Integer,Player> playerMap;
    Team team;
    Season season;

    public AdditionalInfo(TeamManager manager, TeamOwner owner, Coach coach, HashMap playerMap, Team team, Season season) {
        this.manager = manager;
        this.owner = owner;
        this.coach = coach;
        this.playerMap = playerMap;
        this.team = team;
        this.season = season;
    }

    /**
     * this function adding player to the player list of this team in specif season
     * @param player
     */
    public void addPlayer(Player player){
    }
}
