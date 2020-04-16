package ServiceControllers;

import Users.*;

public class TeamOwnerController {

    TeamOwner owner;

    public TeamOwnerController(String username, String firstName, String lastName) {
        this.owner = new TeamOwner(username,firstName,lastName);
    }





}
