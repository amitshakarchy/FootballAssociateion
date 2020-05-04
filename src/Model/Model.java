package Model;

import AssociationAssets.*;
import Budget.TeamBudget;
import PoliciesAndAlgorithms.HeuristicGamesAssigningPolicy;
import PoliciesAndAlgorithms.ScoreTablePolicy1;
import PoliciesAndAlgorithms.ScoreTablePolicy2;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Users.*;
import System.*;

public class Model implements IModel {

    Search search;
    Object user;
    ValidateObject validate;
    static int TEAM_ID = 1;

    //region General
    /**
     * Returns User's full name in order to display it on screen if needed.
     *
     * @return - The user's full name.
     */
    @Override
    public String getUsersFullName() {
        if (user == null)
            return "";

        return ((Fan) user).getfName() + " " + ((Fan) user).getlName();
    }

    /**
     * Returns the user's type (Team Owner, Referee.....) in order to maintain authorizations.
     *
     * @return User's type
     */
    @Override
    public String UserType() {
        if (user == null)
            return "";

        String type = "";
        Object userObj = search.getUserByUserName(((Fan) user).getUserName());

        if (userObj instanceof Coach) {
            type = "Coach";
        } else if (userObj instanceof Player) {
            type = "Player";
        } else if (userObj instanceof Referee) {
            type = "Referee";
        } else if (userObj instanceof RepresentativeFootballAssociation) {
            type = "RepresentativeFootballAssociation";
        } else if (userObj instanceof SystemManager) {
            type = "SystemManager";
        } else if (userObj instanceof TeamManager) {
            type = "TeamManager";
        } else if (userObj instanceof TeamOwner) {
            type = "TeamOwner";
        }
        return type;
    }

    /*
     * Returns a list of games for a specific season and league.
     * May be used in order to let the user choose a game by it's ID.
     *
     * @return list of games, ordered by game's ID.
     * @throws RecordException - in case of no such league or season
     */
    @Override
    public String getGames(String leagueName, String seasonYear) throws RecordException {

        League league = ValidateObject.getValidatedLeague(leagueName);
        Season season = ValidateObject.getValidatedSeason(leagueName, seasonYear);

        StringBuilder gameList = new StringBuilder();
        for (Game game : league.getGames(season.getYear()).values()) {
            gameList.append("Game's ID: ").append(game.getGID()).append(", Date: ").append(game.getDate().toString()).append(";\n ");
        }
        return gameList.toString();
    }
    //endregion

    //region Login
    /**
     * Logs in using user's credentials. Assumes input was already validated for null and empty inputs.
     * After a successful login, Model saves the user's credentials and user-type in order to maintain authorizations.
     *
     * @param username - user's username. (should be Email address?)
     * @param password - password.
     * @return true or false for success or failure
     */
    @Override
    public boolean login(String username, String password) {
        Guest guest = new Guest();
        Fan tmpUser = guest.logInGuest(username, password);
        // In case login failed
        if (tmpUser == null) {
            return false;
        }
        // Save the user as an object
        user = search.getUserByUserName(tmpUser.getUserName());
        return true;
    }
    //endregion

    //region Team Management
    /**
     * Creates a new team out of the given details.
     * Assumes input was already validated for null and empty inputs.
     * Throws exception in any of the following cases: Team's name already exists, No such a season,
     *
     * @param name-       team's name
     * @param seasonYear-
     * @param fieldName-
     * @param fieldCity-
     * @throws RecordException- in case there is no such league or season or team owner.
     */
    @Override
    public boolean createTeam(String name, String leagueName, String seasonYear, String fieldName, String fieldCity) throws RecordException {

        // Only TeamOwner is allowed to create a team.
        if (!(user instanceof TeamOwner))
            return false;
        TeamOwner teamOwnerUser = (TeamOwner) user;

        Season season = ValidateObject.getValidatedSeason(leagueName, seasonYear);

        // Get an existing field or create one and add it TO fields DB
        Field field = search.getFieldByFieldName(fieldName);
        if (field == null) {
            field = FootballSystem.getInstance().createField(fieldName, fieldCity, 5000);
        }

        // Create a new team.
        Team newTeam = new Team(TEAM_ID++, name, season, field, null, teamOwnerUser);

        // Now need to add new data to the DB
        FootballSystem.getInstance().addTeamToDB(newTeam);

        return true;
    }

    /**
     * Closes a team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName - team's name
     * @return true or false for success / failure
     */
    @Override
    public boolean closeTeam(String teamName) {

        // Only System Manager is allowed to close a team.
        if (!(user instanceof SystemManager))
            return false;
        SystemManager systemManagerUser = (SystemManager) user;

        // Return false if team does not exist
        Team toClose = search.getTeamByTeamName(teamName);
        if (toClose == null)
            return false;

        // Close team
        systemManagerUser.closeTeam(teamName);

        // Make sure team is not active
        if (toClose.getIsActive()==ETeamStatus.INACTIVE)
            return true;

        else return false;
    }

    /**
     * Adds a player into an existing team. Assumes input was already validated for null and empty inputs.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param SeasonYear - team's name
     * @param Username   - username
     * @param Password   - password
     * @param firstName  - first name
     * @param lastName   - last name
     * @param bDate      - format: dd-mm-yyyy
     * @param role       - role
     * @return true or false for success / failure
     * @throws RecordException -  in case there is no such team or season, or in case the user is already present in the system.
     */
    @Override
    public boolean addPlayer(String teamName, String SeasonYear, String Username, String Password, String firstName, String lastName, String bDate, String role) throws RecordException {
        return false;
    }

    /**
     * Removes a player from the team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @param username   - username
     * @return true or false for success / failure
     */
    @Override
    public boolean removePlayer(String teamName, String seasonYear, String username) {
        return false;
    }

    /**
     * Adds a coach into an existing team. Assumes input was already validated for null and empty inputs.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param SeasonYear - Requested year in which the team plays
     * @param Username   - username
     * @param Password   - password
     * @param firstName  - first name
     * @param lastName   - last name
     * @param bDate      - format: dd-mm-yyyy
     * @param role       - role
     * @param training   - Coach's trining
     * @return true or false for success / failure
     * @throws RecordException - in case there is no such team or season, or in case the user is already present in the system.
     */
    @Override
    public boolean addCoach(String teamName, String SeasonYear, String Username, String Password, String firstName, String lastName, String bDate, String role, String training) throws RecordException {
        return false;
    }

    /**
     * Removes a coach from the team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @param username   - username
     * @return true or false for success / failure
     */
    @Override
    public boolean removeCoach(String teamName, String seasonYear, String username) {
        return false;
    }

    /**
     * Adds a Team Manager into an existing team. Assumes input was already validated for null and empty inputs.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param SeasonYear - Requested year in which the team plays
     * @param Username   - username
     * @param Password   - password
     * @param firstName  - first name
     * @param lastName   - last name
     * @return true or false for success / failure
     * @throws RecordException - in case there is no such team or season, or in case the user is already present in the system.
     */
    @Override
    public boolean addTeamManager(String teamName, String SeasonYear, String Username, String Password, String firstName, String lastName) throws RecordException {
        return false;
    }

    /**
     * Removes a Team Manager from the team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @param username   - username
     * @return true or false for success / failure
     */
    @Override
    public boolean removeTeamManager(String teamName, String seasonYear, String username) {
        return false;
    }

    /**
     * Adds a field into an existing team
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @param fieldName  - Field's name
     * @param city       - city
     * @return true or false for success / failure
     * @throws RecordException - in case team/season doesn't exist or a field name is already present in the system.
     */
    @Override
    public boolean addField(String teamName, String seasonYear, String fieldName, String city) throws RecordException {
        return false;
    }

    /**
     * Removes a Field from the team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @param fieldName  - Field's name
     * @return true or false for success / failure
     */
    @Override
    public boolean removeField(String teamName, String seasonYear, String fieldName) {
        return false;
    }

    /**
     * Nominates a team Owner.
     * Assumes input was already validated for null and empty inputs.
     * Throws Exception if there is no such a user, team or season.
     *
     * @param teamName   - the requested team
     * @param SeasonYear - Requested year in which the team plays
     * @param Username   - Team Owner's user
     * @return true or false for success / failure
     * @throws RecordException- in case team/season/username doesn't exist .
     */
    @Override
    public boolean nominateTeamOwner(String teamName, String SeasonYear, String Username) throws RecordException {
        return false;
    }

    /**
     * Removes a nomination of a team owner.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - the requested team
     * @param SeasonYear - Requested year in which the team plays
     * @param Username   - Team Owner's user
     * @return true or false for success / failure
     */
    @Override
    public boolean discardNominationForTeamOwner(String teamName, String SeasonYear, String Username) {
        return false;
    }

    /**
     * Nominates a team Manager.
     * Assumes input was already validated for null and empty inputs.
     * TODO: think how to specify authorities
     *
     * @param teamName   - the requested team
     * @param SeasonYear - Requested year in which the team plays
     * @param Username   - Team Owner's user
     * @return true or false for success / failure
     * @throws RecordException - if there is no such a user, team or season.
     */
    @Override
    public boolean nominateTeamManager(String teamName, String SeasonYear, String Username) throws RecordException {
        return false;
    }

    //endregion


    /**
     * Receives a policy by its name for a specific season & league and sets it.
     *
     * @param policy     - "Simple Policy" or "Heuristic Policy"
     * @param leagueName -
     * @param seasonYear -
     * @return true for success, false for failure
     */
    @Override
    public boolean defineGameSchedulingPolicy(String policy, String leagueName, String seasonYear) throws RecordException {

        // Only Representative is allowed to define a policy.
        if (!(user instanceof TeamOwner))
            return false;
        TeamOwner teamOwnerUser = (TeamOwner) user;

        // Validate season & league
        ValidateObject.getValidatedSeason(leagueName, seasonYear);
        League league= ValidateObject.getValidatedLeague(leagueName);

        // Set requested policy
        switch (policy){
            case "Simple Policy":
                league.getSeasonBinders().get(seasonYear).setAssigningPolicy(new SimpleGamesAssigningPolicy());
                break;

            case "Heuristic Policy":
                league.getSeasonBinders().get(seasonYear).setAssigningPolicy(new HeuristicGamesAssigningPolicy());
                break;
        }
        return true;
    }

    /**
     * Receives a policy by its name for a specific season & league and sets it.
     *
     * @param policy     - "Policy 1" "Policy 2"
     * @param leagueName -
     * @param seasonYear -
     * @return true for success, false for failure
     */
    @Override
    public boolean defineScoreTablePolicy(String policy, String leagueName, String seasonYear) throws RecordException {

        // Only Representative is allowed to define a policy.
        if (!(user instanceof RepresentativeFootballAssociation))
            return false;
        RepresentativeFootballAssociation RepUser = (RepresentativeFootballAssociation) user;

        // Validate season & league
        ValidateObject.getValidatedSeason(leagueName, seasonYear);
        League league= ValidateObject.getValidatedLeague(leagueName);

        // Set requested policy
        switch (policy){
            case "Policy 1":
                //RepUser.SetGamesAssigningPolicy();
                league.getSeasonBinders().get(seasonYear).setScoreTablePolicy(new ScoreTablePolicy1());
                break;

            case "Policy 2":
                league.getSeasonBinders().get(seasonYear).setScoreTablePolicy(new ScoreTablePolicy2());
                break;
        }
        return true;
    }

    /**
     * Activates Game Scheduling Algorithm for a specific season & league and sets it.
     *
     * @param leagueName -
     * @param seasonYear -
     * @return true for success, false for failure
     */
    @Override
    public boolean runGameSchedulingAlgorithm(String leagueName, String seasonYear) {

        // Only Representative is allowed to define a policy.
        if (!(user instanceof TeamOwner))
            return false;
        TeamOwner teamOwnerUser = (TeamOwner) user;


        return false;
    }
}
