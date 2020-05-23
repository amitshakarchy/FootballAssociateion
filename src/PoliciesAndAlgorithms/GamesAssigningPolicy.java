package PoliciesAndAlgorithms;


import AssociationAssets.Game;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import Users.Referee;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public abstract class GamesAssigningPolicy {

    public abstract HashMap<Integer, Game> executePolicy(HashMap<String, Team> teams, Map<String, Referee> refs, LocalDate date, Season season, League league) throws Exception;



}
