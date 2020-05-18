package PoliciesAndAlgorithms;

import AssociationAssets.Game;
import AssociationAssets.Team;
import Users.EReferee;
import Users.Referee;

import java.util.HashMap;
import java.util.Map;

public class SimpleGamesAssigningPolicy extends GamesAssigningPolicy {
    /**
     * constructor
     */
    public SimpleGamesAssigningPolicy() {

    }

    /**
     * activate SimpleGamesAssigningPolicy
     */
    public HashMap<Integer, Game> executePolicy(HashMap<String, Team> teams, Map<String, Referee> refs) throws Exception {
        HashMap <Integer,Game> games = new HashMap<>();
        int refereeCount = refs.size();
        int mainRefereeCount = countMainReferees(refs);
        int teamsCount = teams.size();
        if(refereeCount == 0) throw new Exception("There is no main referee, can't assign games");
        else if(refereeCount < 4) throw new Exception("There is not enough referees to assign any game");


        return games;
    }

    private int countMainReferees(HashMap<String, Referee> referees) {
        int ans = 0;
        for (Map.Entry<String ,Referee> entry: referees.entrySet()) {
            if(entry.getValue().getTraining() == EReferee.MAIN){
                ans++;
            }
        }
        return ans;
    }
}
