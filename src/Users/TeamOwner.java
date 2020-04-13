package Users;
import AssociationAssets.AdditionalInfo;
import AssociationAssets.Field;
import AssociationAssets.Season;
import AssociationAssets.Team;
import System.FootballSystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is the Team Owner Class.
 * Aouthors: Tair Cohen
 */
public class TeamOwner extends Fan {

    List<AdditionalInfo> additionalInfoList;

    /**
     * The constructor for TeamOwner class.
     * @param userName
     * @param firstName
     * @param lastName
     */
    public TeamOwner(String userName, String firstName, String lastName) {
        super(userName, firstName, lastName);
        this.additionalInfoList = new ArrayList<>();
    }

    //region Getters & Setters
    public List<AdditionalInfo> getAdditionalInfo() {
        return additionalInfoList;
    }
    public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
        this.additionalInfoList = additionalInfo;
    }
    // end region

    // use case 6.1 region

    /**
     * This function adds player to a team in a season.
     * the function checks if the player has already username and password,which means
     * he is sign up already in the system, if he does then
     * the function only creating new player, otherwise the function sign in the player with his details
     * and as well creating new player.
     * the function checks if the player is playing already in another team -
     * and if so, is not been added to the team.
     * @param team
     * @param season
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param birthday
     * @param ePlayerRole
     * @return true if the player created successfully.
     */
    public boolean addPlayer(Team team, Season season, String userName, String password, String firstName, String lastName, Date birthday, EPlayerRole ePlayerRole) {
        if (team == null || season == null) {
            System.out.println("team or season is null");
            return false;
        }
        // if the user exist
        if (FootballSystem.getInstance().existFanByUserName(userName)) {
            // check if this user name is playing in another team
            if(FootballSystem.getInstance().findPlayerAtTeamByUserName(userName)){
                System.out.println("this player is playing in another team, user name is: "+userName);
                return false;
            }
            // check if the additional info has this player already
            if (!setPlayerToAdditionalInfo(team, season, userName)) {
                return false;
            }
            FootballSystem.getInstance().creatingPlayer(userName, firstName, lastName, birthday, ePlayerRole);
        }
        // if the user doesnt exist - sign in & creation
        else {
            if (!setPlayerToAdditionalInfo(team, season, userName)) {
                return false;
            }
            if (!signIn(userName, password, firstName, lastName)) {
                return false;
            }
            FootballSystem.getInstance().creatingPlayer(userName, firstName, lastName, birthday, ePlayerRole);
        }
        return true;
    }

    /**
     * this function set the player to the correct additional info of a specific team in a season.
     * @param team
     * @param season
     * @param userName
     * @return true if the player was adding successfully
     */
    private boolean setPlayerToAdditionalInfo(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch == null) {
            return false;
        }
        if (!additionalInfoToSearch.addPlayer(userName)) {
            System.out.println("The player" + userName + "is already exist in the team" +
                    team + "in season" + season);
            return false;
        }
        return true;
    }

    /**
     * This function adds coach to a team in a season.
     * the function checks if the coach has already username and password,which means
     * he is sign up already in the system, if he does then
     * the function only creating new player, otherwise the function sign in the coach with his details
     * and as well creating new coach.
     * the function checks if the coach is coaching already in another team -
     * and if so, is not been added to the team.
     * @param team
     * @param season
     * @param userName
     * @param password
     * @param firstName
     * @param lastName
     * @param training
     * @param eCoachRole
     * @return true if the coach was added successfully to the team.
     */
    public boolean addCoach(Team team, Season season, String userName, String password, String firstName, String lastName, ETraining training, ECoachRole eCoachRole) {
        if (team == null || season == null) {
            System.out.println("team or season is null");
            return false;
        }
        // if the user exist
        if (FootballSystem.getInstance().existFanByUserName(userName)) {
            // check if this user name is coaching in another team
            if(FootballSystem.getInstance().findCoachAtTeamByUserName(userName)){
                System.out.println("this coach is coaching another team, user name is: "+userName);
                return false;
            }
            // check if the additional info has this coach already
            if (!setCoachToAdditionalInfo(team, season, userName)) {
                return false;
            }

            FootballSystem.getInstance().creatingCoach(userName, firstName, lastName, training, eCoachRole);
        }
        // if the user doesnt exist
        else {
            if (!setCoachToAdditionalInfo(team, season, userName)) {
                return false;
            }
            if (!signIn(userName, password, firstName, lastName)) {
                return false;
            }
            FootballSystem.getInstance().creatingCoach(userName, firstName, lastName, training, eCoachRole);
        }
        return true;
    }

    /**
     * this function set the coach to the correct additional info of a specific team in a season.
     * @param team
     * @param season
     * @param userName
     * @return true if the coach was adding successfully to the team.
     */
    private boolean setCoachToAdditionalInfo(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch == null) {
            return false;
        }
        if (!additionalInfoToSearch.addCoach(userName)) {
            System.out.println("The coach" + userName + "is already exist in the team" +
                    team + "in season" + season);
            return false;
        }
        return true;
    }

    public boolean addTeamManager(Team team, Season season, String userName, String password, String firstName, String lastName) {
        if (team == null || season == null) {
            System.out.println("team or season is null");
            return false;
        }
        // checking first if the user name is not team manger/owner in this team & season already.
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch.findManager(userName) == null &&
                additionalInfoToSearch.findTeamOwner(userName) == null) {
            // if the user exist
            if (FootballSystem.getInstance().existFanByUserName(userName)) {
                // check if the additional info has this  already
                if (!setTeamManagerToAdditionalInfo(team, season, userName)) {
                    return false;
                }
                FootballSystem.getInstance().creatingTeamManager(userName, firstName, lastName);
            }
            // if the user doesnt exist - need to sign in & create manager.
            else {
                if (!setTeamManagerToAdditionalInfo(team, season, userName)) {
                    return false;
                }
                if (!signIn(userName, password, firstName, lastName)) {
                    return false;
                }
                FootballSystem.getInstance().creatingTeamManager(userName, firstName, lastName);
            }
            return true;
        } else {
            System.out.println("The user " + userName + " is already team owner/manager on the team " +
                    team + " on the season " + season);
            return false;
        }
        // TODO: 4/12/2020 permissions of manager!
    }
    private boolean setTeamManagerToAdditionalInfo(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch == null) {
            return false;
        }
        if (!additionalInfoToSearch.addManager(userName,getUserName())) {
            System.out.println("The manager" + userName + "is already exist in the team" +
                    team + "in season" + season);
            return false;
        }
        return true;
    }
    public boolean addField(Team team, Season season, String name, String city, int capacity) {
        if (team == null || season == null) {
            System.out.println("team or season is null");
            return false;
        }
        Field field = new Field(name, city, capacity);
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        additionalInfoToSearch.getTeam().addField(field);
        FootballSystem.getInstance().addFieldToDB(field);
        return true;
    }
    public void removePlayer(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        additionalInfoToSearch.removePlayer(userName);
    }
    public void removeCoach(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        additionalInfoToSearch.removeCoach(userName);
    }
    public void removeTeamManager(Team team, Season season, String userNameToRemove) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        // checking if this team owner was the one that nominated the team manager
        if (additionalInfoToSearch.whoNominateTeamManager(userNameToRemove,getUserName())) {
            additionalInfoToSearch.removeManager(userNameToRemove,getUserName());
        }
    }
    public void removeField(Team team, Season season, String fieldName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        additionalInfoToSearch.getTeam().removeField(fieldName);
    }
    // use case 6.1 end region

    //use case 6.2 region
    public boolean nominateTeamOwner(Team team, Season season,String userName) {
        if (team == null || season == null) {
            System.out.println("team or season is null");
            return false;
        }
        // checking first if the user name is not team owner in this team and season already.
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch.findTeamOwner(userName) != null) {
            System.out.println("The user " + userName + " is already team owner on the team " +
                    team + " on the season " + season);
            return false;
        }
        // checking if the user is in the system.
        if (FootballSystem.getInstance().existFanByUserName(userName)) {
            Fan fan = FootballSystem.getInstance().getFanByUserName(userName);
            // check if the additional info has this teamOwner already
            if (!setTeamOwnerToAdditionalInfo(team, season, userName)) {
                return false;
            }
            FootballSystem.getInstance().creatingTeamOwner(userName,fan.getfName(),fan.getlName());
        }
        else{
            return false;
        }
        return true;
    }
    private boolean setTeamOwnerToAdditionalInfo(Team team, Season season, String userName) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        if (additionalInfoToSearch == null) {
            return false;
        }
        if (!additionalInfoToSearch.addTeamOwner(userName,getUserName())) {
            System.out.println("The team owner" + userName + "is already exist in the team" +
                    team + "in season" + season);
            return false;
        }
        return true;
    }
    // use case 6.2 end region

    // use case 6.3 region
    public void discardNominationForTeamOwner(Team team, Season season, String userNameToRemove) {
        AdditionalInfo additionalInfoToSearch = getAdditionalInfo(team, season);
        // checking if this team owner was the one that nominated the team manager
        if (additionalInfoToSearch.whoNominateTeamOwner(userNameToRemove,getUserName())) {
            additionalInfoToSearch.removeTeamOwner(userNameToRemove,getUserName());
        }
        // removing all nominations that the team owner to be removed had nominated
        additionalInfoToSearch.removeAllNominations(userNameToRemove);
    }
    // use case 6.3 end region

    // use case 6.4 region
    public boolean nominateTeamManager(Team team, Season season,String userName){
        Fan fan = FootballSystem.getInstance().getFanByUserName(userName);
        if(fan != null){
            return addTeamManager(team,season,userName,null,fan.getfName(),fan.getlName());
        }
        return false;
    }
    // use case 6.4 end region

    // use case 6.6 region
    public void closeTeam() {

    }
    // use case 6.6 end region

    // use case 6.7 region
    public void finnacelReport() {
    }
    // use case 6.7 end region


    private AdditionalInfo getAdditionalInfo(Team team, Season season) {
        AdditionalInfo additionalInfoToSearch = null;
        for (AdditionalInfo additionalInfo :
                this.additionalInfoList) {
            if (additionalInfo.getTeam().equals(team) && additionalInfo.getSeason().equals(season)) {
                additionalInfoToSearch = additionalInfo;
            }
        }
        return additionalInfoToSearch;
    }
    private boolean signIn(String userName, String password, String firstName, String lastName) {
        Fan fan = FootballSystem.getInstance().signIn(userName, password, firstName, lastName);
        // invalid inputs or username already exist.
        if (fan == null) {
            return false;
        }
        return true;
    }

}
