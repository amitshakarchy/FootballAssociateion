package Users;

import AssociationAssets.Game;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.Team;
//import Budget.AssociationBudget;
import Budget.TeamBudget;
import DB.LeagueDB;
import PoliciesAndAlgorithms.GamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy;
import java.util.HashMap;
import java.util.Random;

public class representativeFootballAssociation extends Fan {
    GamesAssigningPolicy gamePolicy;
    //AssociationBudget associationBudget;
    HashMap<Integer, Boolean> teamsExceedBudget; //Integer-teamID, Boolean- team exceed budget or not
    LeagueDB leagueDB;
    /**
     * Constructor
     * @param userName - Unique football association representative username
     * @param fName - The first name of the football association representative
     * @param lName -The last name of the football association representative
     * @param gamePolicy - the assigning game policy
     */
    public representativeFootballAssociation(String userName, String fName, String lName, GamesAssigningPolicy gamePolicy) {
        super(userName, fName, lName);
        this.gamePolicy = gamePolicy;
      //  this.associationBudget = new AssociationBudget();
        this.teamsExceedBudget= new HashMap<>();
        this.leagueDB = new LeagueDB();
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
        League newLeague = new League(leagueName);
        newLeague.setScoreTablePolicy(year, scorePolicy);
        newLeague.setAssigningPolicy(year, gamePolicy);
        setSeasonToLeague(newLeague, year, games, teams);
        leagueDB.addLeague(newLeague,newLeague.getLeagueName());
    }

    /**
     * useCase #9.2 - Define season to League by year
     * @param league
     * @param year - the year of the season
     * @param games- the games in league season
     * @param teams - the teams in league season
     */
    public void setSeasonToLeague(League league, String year, HashMap<String, Game> games, HashMap<String, Team> teams) {
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
        String password = String.valueOf(new Random().nextInt(100000000));
        String userName = randomUserName(fName,lName);
        //  Referee referee= (Referee) FootballSystem.getInstance().signIn(userName, password, fName, lName,EUserType.Referee);
        //  referee.setTraining(training);
        //  referee.LoginInvitation(userName,password);
    }

    /**
     * @param fName
     * @param lName
     * @return a unique username
     */
    public String randomUserName(String fName, String lName) {
        String userName ="";
        Referee referee = null;
        int counter = 1;
        while (referee == null) {
            userName = fName + lName + counter;
            //        referee = (Referee) FootballSystem.getInstance().login(fName, lName);
            counter++;
        }
        return userName;
    }

    /**
     * useCase #9.3 - remove referee
     * @param refereeToRemove - the referee we remove from the system
     */
    public void removeReferee(Referee refereeToRemove) {
//      FootballSystem.getInstance().removeUser(refereeToRemove.getUserName);
    }

    /**
     * useCase #9.4 - assign 3 referees (1 main and 2 sides) to judge the given game in the given league and season
     * @param mainRef
     * @param sideRef1
     * @param sideRef2
     * @param game
     */
    public void assignReferees(Referee mainRef, Referee sideRef1, Referee sideRef2, Game game) throws Exception {
        game.setMain(mainRef);
        game.setSide1(sideRef1); 
        game.setSide2(sideRef2);
    }

    //TODO:Amit&Alon add setPolicy function in GamesAssigningPolicy
    /**
     * // useCase #9.6 - define assign game policy
     */
    public void SetGamesAssigningPolicy(GamesAssigningPolicy newGamePolicy) {
//           gamePolicy.setPolicy(newGamePolicy);
    }

    //TODO:Amit&Alon add applyPolicy function in GamesAssigningPolicy
    /**
     * useCase #9.7 - activate the Games Assigning
     */
    public void activateGamesAssigning() {
        //gamePolicy.applyPolicy();
    }

    /**
     * useCase #9.8
     * Defines rules for budget control over given team and season by set threshold and rule
     * that determine whether the group has exceeded its budget. if so, activate alert to representativeFootballAssociation
     */
    public void setTeamBudgetControlRules(Team team, Season season,double threshHold) {
        TeamBudget teamBudget= new TeamBudget(team,season,threshHold);
        teamBudget.setThreshHold(threshHold);
        SetTeamBudgetExceedRule(teamBudget);
    }
    public void SetTeamBudgetExceedRule(TeamBudget teamBudget){
        if((1+teamBudget.getThreshHold())*teamBudget.totalIncomes() < teamBudget.totalOutcomes()){
            AlertTeamExceedBudget(teamBudget.getTeam().getTID(), true);
        }
    }
    public void AlertTeamExceedBudget(int teamID,  Boolean bool){

        this.teamsExceedBudget.put(teamID, bool);
    }

    /**
     * useCase #9.9 - define Tutu intakes
     * @param TutuIntakes
     */
    public void setAssociationBudgetTutuIntakes(double TutuIntakes ) {
      //  associationBudget.setTutuIntakes(TutuIntakes);
    }

    /**
     * useCase #9.9 - define registration fee
     * @param registrationFee
     */
    public void setAssociationBudgetRegistrationFee(double registrationFee) {
    //    associationBudget.setRegistrationFee(registrationFee);
    }

    /**
     * useCase #9.9 - set salary User
     * @param user
     * @param salaryUser
     */
    //public void setAssociationBudgetSalaries(AUser user, Double salaryUser){
    //    associationBudget.addSalary(user, salaryUser);
    //}

}
