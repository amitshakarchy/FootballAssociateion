package Users;
import AssociationAssets.AdditionalInfo;
import java.util.ArrayList;
import java.util.List;

public class TeamOwner extends Role {

    List<AdditionalInfo> additionalInfo;

    public TeamOwner(String userName, String firstName, String lastName) {
        super(userName, firstName, lastName);
        this.additionalInfo = new ArrayList<>();
    }
    public List<AdditionalInfo> getAdditionalInfo() {
        return additionalInfo;
    }
    public void setAdditionalInfo(List<AdditionalInfo> additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
    //    // use case 6.1 region
//    public Player addPlayer(String firstName, String lastName,
//                            Date birthday, EPlayerRole role) {
//        // TODO: 4/11/2020 ask yarin about the password
//        String password = "1234";
//        Player player = null;
//        String uName;
//        while(player == null){
//            uName = createNewUserName(firstName,lastName);
//
//            player = (Player) FootballSystem.getInstance()
//                    .signIn(uName, password, firstName, lastName, ERoleType.Player);
//        }
//        player.setRole(role);
//        // TODO: 4/11/2020 ask yuval to add setter
//        //player.setbDay(birthday);
//        additionalInfo.addPlayer(player);
//        return player;
//    } // use case 6.1
//    public TeamManager addTeamManger(String firstName, String lastName) {
//        // TODO: 4/11/2020 ask yarin about the password
//        String password = "1234";
//        TeamManager teamManager = null;
//        String uName;
//        while (teamManager == null) {
//            uName = createNewUserName(firstName, lastName);
//            teamManager = (TeamManager) FootballSystem.getInstance()
//                    .signIn(uName, password, firstName, lastName, ERoleType.TeamManager);
//        }
//        additionalInfo.addManager(teamManager);
//        return teamManager;
//    }// use case 6.1
//    public Coach addCoach(String firstName, String lastName,ETraining training,ECoachRole role) {
//        String password = String.valueOf(new Random().nextInt(100000000));
//        Coach coach = null;
//        String uName;
//        while (coach == null) {
//            uName = createNewUserName(firstName, lastName);
//            coach = (Coach) FootballSystem.getInstance()
//                    .signIn(uName, password, firstName, lastName, ERoleType.Coach);
//        }
//        coach.setRole(role);
//        coach.setTraining(training);
//        additionalInfo.addCoach(coach);
//        return coach;
//    }// use case 6.1
//    public Field addField(String name, String city,int capacity) {
//        Field field = new Field(name,city,capacity);
//        // TODO: 4/11/2020 wait for alon to add to team fields!!
//        //additionalInfo.getTeam().
//        return field;
//    }// use case 6.1
//    public void removePlayer(String uName){
//        // TODO: 4/11/2020 wait for alon to chagne
//    }
//    public void removeTeamManger(String uName){
//        // TODO: 4/11/2020 wait for alon to chagne
//    }
//    public void removeCoach(String uName){
//        // TODO: 4/11/2020 wait for alon to chagne
//    }
//    public void removeField(String fieldName){
//        // TODO: 4/11/2020 wait for alon to chagne
//    }
//    private String createNewUserName(String firstName, String lastName) {
//        userNameCounter++;
//        return firstName + lastName + userNameCounter;
//    }
//    // use case 6.1 end region
//    public boolean addTeamOwner(String uName){
//        User user = FootballSystem.getInstance().getUserByUserName(uName);
//        if(user instanceof Referee || user instanceof representativeFootballAssociation ||
//            user instanceof SystemManager) {
//            System.out.println("Team Owner can't be Referee / SystemManager / " +
//                    "representativeFootballAssociation");
//            return false;
//        }
//        // checking if the user is not a team owner of this team already.
//        else if(additionalInfo.getOwners().containsKey(uName)){
//            System.out.println("This user is already team owner on this team");
//            return false;
//        }
//        // TODO: 4/11/2020 add to additional info the teamOwner
//        return true;
//    }
//    //use case 6.2
//    public void removeTeamOwner(String uName){
//        // TODO: 4/11/2020 wait for amit to chagne remove function
//        // TODO: 4/11/2020 to check if this team owner was the one that set the team owner to be removed
//        //additionalInfo.removeTeamOwner();
//
//    }
//    // use case 6.3
//    public TeamManager addTeamMnager(String uName){
//        // checking if the user is not a team owner or a team manger of this team already.
//        if(additionalInfo.getOwners().containsKey(uName) ||
//                additionalInfo.getManagers().containsKey(uName)){
//            System.out.println("This user is already team owner or team manger on this team");
//            return null;
//        }
//        // TODO: 4/11/2020 taking care for the permissions of the manger
//        User user = FootballSystem.getInstance().getUserByUserName(uName);
//        user = new TeamManager(user.userName,user.fName,user.lName);
//        additionalInfo.addManager((TeamManager)user);
//        return (TeamManager)user;
//    } // user case 6.4
}
