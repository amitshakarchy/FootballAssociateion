package Users;

import AssociationAssets.Game;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
import Budget.AssociationBudget;
import Budget.TeamBudget;
import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import System.*;

public class RepresentativeFootballAssociation extends Fan implements Observer  {
    private GamesAssigningPolicy gamePolicy;
    private AssociationBudget associationBudget;
    private TeamBudget teamBudget;
    private HashMap<String, Boolean> NotificationTeamsExceedBudget; //String-teamName, Boolean- team exceed budget or not
    /**
     * Constructor
     * @param userName - Unique football association representative username
     * @param fName - The first name of the football association representative
     * @param lName -The last name of the football association representative
     * @param gamePolicy - the assigning game policy
     */
    public RepresentativeFootballAssociation(String userName, String fName, String lName, GamesAssigningPolicy gamePolicy) {
        super(userName, fName, lName);
        this.gamePolicy = gamePolicy;
        this.associationBudget = new AssociationBudget();
        this.NotificationTeamsExceedBudget= new HashMap<>();
    }

    /**
     * useCase #9.1 - Define new League
     * @param leagueName  - The name of the new league
     * @param games - The games in the league season
     * @param teams - The teams in the league season
     * @param scorePolicy - League score policy
     * @param gamePolicy  - League Game policy
     * @param year - The year's league season
     */
    public void addNewLeague(String leagueName, HashMap<String, Game> games, HashMap<String, Team> teams, ScoreTablePolicy scorePolicy, GamesAssigningPolicy gamePolicy, String year) {
        if(leagueName == null || games==null || teams==null || scorePolicy==null ||gamePolicy==null || year==null){
            return;
        }
        League newLeague = new League(leagueName);
        newLeague.setScoreTablePolicy(year, scorePolicy);
        newLeague.setAssigningPolicy(year, gamePolicy);
        setSeasonToLeague(newLeague, year, games, teams);
        FootballSystem.getInstance().addLeagueToDB(newLeague);
    }

    /**
     * useCase #9.2 - Define season to League by year
     * @param league
     * @param year - the year of the season
     * @param games- the games in league season
     * @param teams - the teams in league season
     */
    public void setSeasonToLeague(League league, String year, HashMap<String, Game> games, HashMap<String, Team> teams) {
        if(league== null || year==null || games==null || teams==null ){
            return;
        }
        Season newSeason = new Season(year);
        league.addSeasonToLeague(newSeason);
        league.addTeamsToLeague(year, teams);
        league.addGamesToLeague(year, games);
    }
    /**
     * useCase #9.3 - nominate referee by sign in and send him an invitation to Login
     * @param fName - referee's first name
     * @param lName - referee's last name
     * @param training -  training's referee ID
     */
    public void nominateReferee(String fName, String lName, EReferee training) {
        if(fName == null || lName == null || training == null){
            return;
        }
        String password = String.valueOf(new Random().nextInt(1000000000));
        String userName = signInReferee(fName,lName,password);
        Referee referee =  (Referee) FootballSystem.getInstance().creatingReferee(userName,fName, lName,training);
        referee.LoginInvitation(userName,password);
    }

    /**
     * @param fName
     * @param lName
     * @param password
     * @return a unique username
     */
    public String signInReferee(String fName,String lName ,String password){
        String userName ="";
        Fan user = null;
        int counter = 1;
        while (user == null) {
            userName = fName + lName + counter;
            user = FootballSystem.getInstance().signIn(userName,password,fName,lName);
            counter++;
        }
        return userName;
    }

    /**
     * useCase #9.3 - remove referee
     * @param refereeToRemove - the referee we remove from the system
     */
    public void removeReferee(Referee refereeToRemove) {
        if(refereeToRemove == null){ return; }
        FootballSystem.getInstance().removeUser(refereeToRemove.getUserName());
    }

    /**
     * useCase #9.4 - assign 3 referees (1 main and 2 sides) to judge the given game in the given league and season
     * @param mainRef
     * @param sideRef1
     * @param sideRef2
     * @param game
     */
    public void assignReferees(Referee mainRef, Referee sideRef1, Referee sideRef2, Game game) throws Exception {
        if(mainRef== null || sideRef1==null || sideRef2==null){
            return;
        }
        game.setMain(mainRef);
        game.setSide1(sideRef1);
        game.setSide2(sideRef2);
    }

    /**
     * useCase #9.6 - define assign game policy
     */
    public void SetGamesAssigningPolicy(GamesAssigningPolicy newGamePolicy) {
        if(newGamePolicy !=null) {
            gamePolicy.setPolicy(newGamePolicy);
        }
    }

    /**
     * useCase #9.7 - activate the Games Assigning
     */
    public void activateGamesAssigning() {
        gamePolicy.executePolicy();
    }

    /**
     * useCase #9.8
     * set rule for budget control by define threshHold
     */
    public void setTeamBudgetControlRules(Team team, Season season,double threshHold) {
        if(team == null || season == null || threshHold < 0){
            return;
        }
        TeamBudget teamBudget= new TeamBudget(team,season,threshHold);
        setTeamBudget(teamBudget);
        teamBudget.setThreshHold(threshHold);
    }

    /**
     * This update function called when a team exceeds its budget
     * @param o - TeamBudget Observable
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        if(o == teamBudget){
            NotificationTeamsExceedBudget.put(this.teamBudget.getTeam().getName(),true);
        }
    }

    /**
     * useCase #9.9 - define Tutu intakes
     * @param TutuIntakes
     */
    public void setAssociationBudgetTutuIntakes(double TutuIntakes ) {
        if(TutuIntakes < 0){
            return;
        }
        associationBudget.setTutuIntakes(TutuIntakes);
    }

    /**
     * useCase #9.9 - define registration fee
     * @param registrationFee
     */
    public void setAssociationBudgetRegistrationFee(double registrationFee) {
        if(registrationFee < 0 ){
            return;
        }
        associationBudget.setRegistrationFee(registrationFee);
    }

    /**
     * useCase #9.9 - set salary User
     * @param user
     * @param salaryUser
     */
    public void setAssociationBudgetSalaries(Fan user, Double salaryUser){
        if(user == null || salaryUser < 0){
            return;
        }
        associationBudget.addSalary(user, salaryUser);
    }

    public HashMap<String, Boolean> getTeamsExceedBudget() {
        return NotificationTeamsExceedBudget;
    }

    public void setTeamBudget(TeamBudget teamBudget){
        this.teamBudget= teamBudget;
    }

}