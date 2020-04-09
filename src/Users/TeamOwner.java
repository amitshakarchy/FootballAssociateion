package Users;

import AssociationAssets.AdditionalInfo;
import AssociationAssets.Field;

import java.util.HashMap;

public class TeamOwner extends AUser {

    AdditionalInfo additionalInfo;

    public TeamOwner(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }
    /**
     * this function adding new team property to the team.
     * team properties are: team manger, coach, player of field.
     * @param teamProperty
     */
    public void addTeamProperty(Object teamProperty) {
        if(teamProperty instanceof Coach) {
            additionalInfo.addCoach((Coach) teamProperty);
        }
        else if(teamProperty instanceof Player){
            additionalInfo.addPlayer((Player)teamProperty);
        }
        else if(teamProperty instanceof TeamManager){
            additionalInfo.addManager((TeamManager)teamProperty);
        }
        else if(teamProperty instanceof Field){
            // TODO: 4/7/2020 add field to additional info
        }
        // use case 6.1
    }

    /**
     * this function removing the required team property.
     * team properties are: team manger, coach, player of field.
     * @param teamProperty
     */
    public void removeTeamProperty(Object teamProperty) {
        if(teamProperty instanceof Coach) {
            additionalInfo.removeCoach((Coach) teamProperty);
        }
        else if(teamProperty instanceof Player){
            additionalInfo.removePlayer((Player)teamProperty);
        }
        else if(teamProperty instanceof TeamManager){
            additionalInfo.removeManager((TeamManager)teamProperty);
        }
        else if(teamProperty instanceof Field){
            // TODO: 4/7/2020 remove field from additional info
        }
    }// use case 6.1


    /**
     * this function nominates a user to be a team owner of this team.
     * @param user
     */
    public void nominateTeamOwner(AUser user){
        if(user != null){
            // TODO: 4/7/2020 check if the user is not a team owener in this team already
            // TODO: 4/7/2020 additionInfo.addTeamOwner
        }
    }
}
