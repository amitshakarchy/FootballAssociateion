package Model;

import AssociationAssets.*;
import Budget.TeamBudget;
import Users.*;
import System.*;

public class Model implements IModel {

    Search search;
    Object user;
    static int TEAM_ID=1;

    /**
     * Returns User's full name in order to display it on screen if needed.
     *
     * @return - The user's full name.
     */
    @Override
    public String getUsersFullName() {
        if (user == null)
            return "";

        return ((Fan)user).getfName() + " " + ((Fan)user).getlName();
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
        Object userObj = search.getUserByUserName(((Fan)user).getUserName());

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

        League league = search.getLeagueByLeagueName(leagueName);
        if (league == null) {
            throw new RecordException("League name " + leagueName + " does not exist.");
        }

        Season season = search.getSeasonByYear(seasonYear);
        if (season == null) {
            throw new RecordException("Season " + seasonYear + " does not exist in the requested league.");
        }

        String gameList = "";
        for (Game game : league.getGames(season.getYear()).values()) {
            gameList += "Game's ID: " + game.getGID() + ", Date: " + game.getDate().toString() + ";\n ";
        }
        return gameList;
    }

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
        Fan tmpUser= guest.logInGuest(username, password);
        // In case login failed
        if (tmpUser == null) {
            return false;
        }
        // Save the user as an object
        user=search.getUserByUserName(tmpUser.getUserName());
        return true;
    }

    /**
     * Creates a new team out of the given details.
     * Assumes input was already validated for null and empty inputs.
     * Throws exception in any of the following cases: Team's name already exists, No such a season,
     *
     * @param name-          team's name
     * @param seasonYear-
     * @param fieldName-
     * @param fieldCity-
     * @param teamOwnerUserName-
     * @throws RecordException- in case there is no such league or season or team owner.
     */
    @Override
    public boolean createTeam(String name, String leagueName, String seasonYear, String fieldName, String fieldCity, String teamOwnerUserName) throws RecordException {
        // Assumes the user is a team owner ans has the authority to perform this action.
        TeamOwner teamOwnerTmp= (TeamOwner)user;
        League league = search.getLeagueByLeagueName(leagueName);
        if (league == null) {
            throw new RecordException("League name " + leagueName + " does not exist.");
        }
        Season season= search.getSeasonByYear(seasonYear);
        if (season == null) {
            throw new RecordException("Season " + seasonYear + " does not exist in the requested league.");
        }
        Object teamOwnerObj=search.getUserByUserName(teamOwnerUserName);
        if(teamOwnerObj==null){
            throw  new RecordException("User "+teamOwnerUserName +"does not exist.");
        }

        Field field=search.getFieldByFieldName(fieldName);
        if(field==null){
            field= FootballSystem.getInstance().createField(fieldName,fieldCity,5000);
        }
        Team newTeam= new Team(TEAM_ID++,name,season,field,null,null);
        teamOwnerTmp.nominateTeamOwner(newTeam,season,teamOwnerUserName);

        return false;
    }




    /**
     * Closes a team.
     * Assumes input was already validated for null and empty inputs.
     *
     * @param teamName   - team's name
     * @param seasonYear - team's name
     * @return true or false for success / failure
     */
    @Override
    public boolean closeTeam(String teamName, String seasonYear) {
        return false;
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
}
