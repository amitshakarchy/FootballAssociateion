package Users;

import AssociationAssets.AdditionalInfo;
import AssociationAssets.Field;

import java.util.HashMap;

public class TeamOwner extends AUser {

    AdditionalInfo additionalInfo;
    HashMap<String, Object> teamPropertyHashMap;

    public TeamOwner(String UID, String fName, String lName) {
        super(UID, fName, lName);
    }

    /**
     * this function adding new team property to the team.
     * team properties are: team manger, coach, player of field.
     * @param teamProperty
     */
    public void addTeamProperty(Object teamProperty) {
        if (teamProperty instanceof AUser) {
            if (this.teamPropertyHashMap.containsKey(((AUser) teamProperty).getUID())) {
                System.out.println("This team property" + ((AUser) teamProperty).getUID() +
                        " already exists in this team ");
            } else {
                this.teamPropertyHashMap.put(((AUser) teamProperty).getUID(), teamProperty);
            }
        } else if (teamProperty instanceof Field) {
            // TODO: 4/5/2020 wait for field implementation 
        } // use case 6.1
    }

    /**
     * this function removing the required team property.
     * team properties are: team manger, coach, player of field.
     * @param teamProperty
     */
    public void removeTeamProperty(Object teamProperty) {
        if (teamProperty instanceof AUser) {
            this.teamPropertyHashMap.remove(((AUser) teamProperty).getUID());
        }
        else if(teamProperty instanceof Field){
            // TODO: 4/5/2020 wait for field implementation
        }
    }// use case 6.1

    public void nominateTeamOwner(AUser user){
    }
}
