package Model;

import AssociationAssets.*;
import PoliciesAndAlgorithms.*;
import Users.*;
import System.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Model implements IModel {

    Search search;
    Object user;
    static int TEAM_ID = 1;

    public Model() {
        search= new Search();
    }


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
    public LinkedList<Pair<String,Integer>> getGames(String leagueName, String seasonYear) throws RecordException {
        LinkedList<Pair<String,Integer>> allGames= new LinkedList<Pair<String, Integer>>();
        League league = ValidateObject.getValidatedLeague(leagueName);
        Season season = ValidateObject.getValidatedSeason(leagueName, seasonYear);
            for (Game game : league.getGames(season.getYear()).values()) {
                    allGames.add(new Pair<String, Integer>(game.getDate().toString(),game.getGID()));
            }
            return allGames;
        }


    /**
     * Returns a list of events of a specific game.
     * May be used in order to let the user choose an event by it's ID.
     *
     * @param gameID
     * @return list of events, ordered by event's ID.
     */
    @Override
    public LinkedList<Pair<String,Integer>> getEvents(int gameID) throws RecordException {
        Game game = ValidateObject.getValidatedGame(gameID);
        LinkedList<Pair<String,Integer>> allEvents= new LinkedList<>();
            String eventList = "";
            int i = 0;
            for (Event event : game.getEvents()) {
                allEvents.add(new Pair<String, Integer>
                        ("Type: " +event.getEventType()+ ", Description: "+event.getDescription(),i));

                i++;
            }
            return allEvents;
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
     * @throws RecordException- in case there is no such league or season or team owner.
     */
    @Override
    public boolean createTeam(String name, String leagueName, String seasonYear, String fieldName) throws RecordException {

        // Only TeamOwner is allowed to create a team.
        if (!(user instanceof TeamOwner))
            return false;
        TeamOwner teamOwnerUser = (TeamOwner) user;

        Season season = ValidateObject.getValidatedSeason(leagueName, seasonYear);

        // Get an existing field or create one and add it TO fields DB
        Field field = search.getFieldByFieldName(fieldName);
//        if (field == null) {
//            field = FootballSystem.getInstance().createField(fieldName, fieldCity, 5000);
//        }

        // Create a new team.
        Team newTeam = new Team(TEAM_ID, name, season, field, null, teamOwnerUser);
        TEAM_ID++;
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
        return toClose.getIsActive() == ETeamStatus.INACTIVE;
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


    //region Policy Management
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
        if (!(user instanceof RepresentativeFootballAssociation))
            return false;
        RepresentativeFootballAssociation repUser = (RepresentativeFootballAssociation) user;

        // Validate season & league
        Season season=ValidateObject.getValidatedSeason(leagueName, seasonYear);
        League league= ValidateObject.getValidatedLeague(leagueName);

        // Set requested policy
        switch (policy){
            case "Simple Policy":
                repUser.SetGamesAssigningPolicy(new SimpleGamesAssigningPolicy(), league,season);
                break;

            case "Heuristic Policy":
                repUser.SetGamesAssigningPolicy(new HeuristicGamesAssigningPolicy(), league,season);
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
        RepresentativeFootballAssociation repUser = (RepresentativeFootballAssociation) user;

        // Validate season & league
        Season season= ValidateObject.getValidatedSeason(leagueName, seasonYear);
        League league= ValidateObject.getValidatedLeague(leagueName);

        // Set requested policy
        switch (policy){
            case "Policy 1":
                repUser.SetScoreTablePolicy(new ScoreTablePolicy1(), league,season);
                break;

            case "Policy 2":
                repUser.SetScoreTablePolicy(new ScoreTablePolicy2(), league,season);
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
        if (!(user instanceof RepresentativeFootballAssociation))
            return false;
        RepresentativeFootballAssociation repUser = (RepresentativeFootballAssociation) user;
        repUser.activateGamesAssigning();

        return true;
    }
    //endregion


    //region Game Management

    /**
     * Adds an event into a existing game item
     *
     * @param gameID      - should use "getGames" method first in order to receive the correct gameID
     * @param eventType   - out of event type enum
     * @param description Freestyle Description
     * @return
     */
    @Override
    public boolean addEvent(int gameID, String eventType, String description) throws RecordException {

        // Only Referee is allowed to add an event.
        if (!(user instanceof Referee))
            return false;

        ValidateObject.getValidatedGame(gameID);

        Referee referee= (Referee)user;
        referee.addEventToAssignedGame(gameID,EEventType.valueOf(eventType),description);

        return true;
    }


    /**
     * Updates an event of an existing game item
     *
     * @param gameID      - should use "getGames" method first in order to receive the correct gameID
     * @param eventIndex  - should use "getEvents" method first
     * @param eventType   - out of event type enum
     * @param description Freestyle Description
     * @return
     */
    @Override
    public boolean updateEvent(int gameID, int eventIndex, String eventType, String description) throws RecordException {
        // Only Referee is allowed to update an event.
        if (!(user instanceof Referee))
            return false;

        ValidateObject.getValidatedGame(gameID);

        Referee referee= (Referee)user;
        referee.updateEventToAssignedGame(gameID,eventIndex,EEventType.valueOf(eventType),description);

        return true;
    }

    /**
     * Removes an event from an existing game item
     *
     * @param gameID     - should use "getGames" method first in order to receive the correct gameID
     * @param eventIndex - should use "getEvents" method first
     * @return
     */
    @Override
    public boolean removeEvent(int gameID, int eventIndex) throws RecordException {
        // Only Referee is allowed to remove an event.
        if (!(user instanceof Referee))
            return false;

        ValidateObject.getValidatedGame(gameID);
        Referee referee= (Referee)user;
        referee.removeEventFromAssignedGame(gameID,eventIndex);

        return true;
    }

    /**
     * Updates an event of an existing game item, until 5 hours after game is finished.
     * Can be preformed only by main referee.
     *
     * @param gameID      - should use "getGames" method first in order to receive the correct gameID
     * @param eventIndex  - should use "getEvents" method first
     * @param eventType   - out of event type enum
     * @param description Freestyle Description
     * @return
     */
    @Override
    public boolean updateEventAfterGameOver(int gameID, int eventIndex, String eventType, String description) throws RecordException {
        // Only Referee is allowed to remove an event.
        if (!(user instanceof Referee))
            return false;

        ValidateObject.getValidatedGame(gameID);
        Referee referee= (Referee)user;
        referee.editEventsAfterGameOver(gameID,eventIndex, EEventType.valueOf(eventType), description);

        return true;
    }

    /**
     * Updates an event of an existing game item, until 5 hours after game is finished.
     * Can be preformed only by main referee.
     *
     * @param gameID     - should use "getGames" method first in order to receive the correct gameID
     * @param eventIndex - should use "getEvents" method first
     * @return
     */
    @Override
    public boolean removeEventAfterGameOver(int gameID, int eventIndex) throws RecordException {
        // Only Referee is allowed to remove an event.
        if (!(user instanceof Referee))
            return false;

        ValidateObject.getValidatedGame(gameID);
        Referee referee= (Referee)user;
        referee.removeEventsAfterGameOver(gameID,eventIndex);

        return true;
    }

    /**
     * Export an Excel report holds all events in the required game
     *  @param gameID     -
     * @param pathToSave -
     * @param reportName -
     * @return
     */
    @Override
    public boolean exportGameReport(int gameID, String pathToSave, String reportName) throws RecordException {


        return true;
    }

    /**
     * Change a game's location. Should send a notification to all related referees.
     *
     * @param gameID       -
     * @param newFieldName -
     * @return
     */
    @Override
    public boolean changeGameLocation(int gameID, String newFieldName) {
        return false;
    }

    /**
     * Change a game's date. Should send a notification to all related referees.
     *
     * @param gameID  -
     * @param newDate -
     * @return
     */
    @Override
    public boolean changeGameDate(int gameID, String newDate) {
        return false;
    }
    //endregion




}