package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.UserDetails;

public interface ISystemOperationsBridge {
    /**
     * generates a new random registered user in the system and returns his details
     * @return The information the user entered when he signed up for the system
     */
    UserDetails getNewRegisteredUserForTest();

    /**
     * logs in to the system and validates login successfully
     * @param username - userName
     * @param pw - password
     * @return Returns true if login successful and false if not
     */
    boolean login(String username, String pw);

    /**
     * This function attempts to register a new user to the system,
     * if it returns true, otherwise returns false
     */
    boolean register(String userName, String password, String firstName, String lastName);

}
