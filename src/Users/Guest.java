package Users;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import System.*;

import java.awt.*;

public class Guest {

    /**
     * constructor
     */
    public Guest() {
    }

    /**
     * UC 2.2 -  Guest's sign in
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     */
    public Fan signInGuest(String userName, String password, String firstName, String lastName){
        Fan newFan= FootballSystem.getInstance().signIn(userName, password,firstName, lastName );
        logInGuest(userName,password);
        return newFan;
    }

    /**
     * useCase 2.3 - Guest's login
     * @param userName
     * @param password
     */
    public Fan logInGuest(String userName, String password){
        Fan user = FootballSystem.getInstance().login(userName,password);
        return user;
    }

    /**
     * useCase 2.4- Guest viewing information about the given coach
     * @param coach
     */
    public void viewInformationCoach(Coach coach){
        if(coach == null){
            return;
        }
        coach.viewProfile();
    }

    /**
     * useCase 2.4- Guest viewing information about the given player
     * @param player
     */
    public void viewInformationPlayer(Player player){
        if(player==null){
            return;
        }
        player.viewProfile();
    }

    /**
     * useCase 2.4 - Guest viewing information about the given team
     * @param team
     */
    public void viewInformationTeam(Team team){
        if(team == null){
            return;
        }
        team.viewProfile();
    }

    /**
     * useCase 2.4 - Guest viewing information about the given league
     * @param league
     */
    public void viewInformationLeague(League league){
        if(league == null){
            return;
        }
        league.viewProfile();
    }

    /**
     * useCase 2.4 - Guest viewing information about the given season
     * @param season
     */
    public void viewInformationSeason(Season season){
        if(season == null){
            return;
        }
        season.viewProfile();
    }

    /**
     * /////////////??????????
     * useCase 2.5 - search by a given key word,
     * if the search finds result return true, else return false
     * @param searchKey
     */
    public void search(String searchKey){
        if(searchKey == null) {
            return;
        }
        if(FootballSystem.getInstance().existFanByUserName(searchKey)) {
            return;
        }
        else if(FootballSystem.getInstance().existLeagueByName(searchKey)){
            return;
        }
    }

    protected String searchByName(String name) {
        return null;
    }

    protected String searchByCategory(String categoryName) {
        return null;

    }

    protected String searchByKeyWord(String keyWord) {
        return null;
    }
}