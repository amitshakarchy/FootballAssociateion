package PoliciesAndAlgorithms;


import AssociationAssets.Game;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import Users.Referee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class HeuristicGamesAssigningPolicy extends GamesAssigningPolicy {




    @Override
    public HashMap<Integer, Game> executePolicy(HashMap<String, Team> teams, Map<String, Referee> refs, LocalDate date, Season season, League league) throws Exception {
        return null;
    }
}



