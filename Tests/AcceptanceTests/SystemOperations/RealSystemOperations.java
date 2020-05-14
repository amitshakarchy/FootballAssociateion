package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.TeamDetails;
import AcceptanceTests.DataObjects.UserDetails;
import AssociationAssets.Field;
import AssociationAssets.League;
import AssociationAssets.Season;
import System.FootballSystem;
import Model.*;

import javax.security.auth.login.FailedLoginException;

public class RealSystemOperations implements ISystemOperationsBridge{
    private final Model model;
    private static int randomNumberForUser = 0;
    private static int getRandomNumberForTeam = 0;

    public RealSystemOperations() {
        this.model = new Model();
        initDB();
    }

    private void initDB() {
        Season season = new Season("2020");
        Season season1 = new Season("2021");
        FootballSystem.getInstance().addSeasonToDB(season);
        FootballSystem.getInstance().addSeasonToDB(season1);

        League league = new League("La Liga");
        League league1 = new League("gal");
        FootballSystem.getInstance().addLeagueToDB(league);
        FootballSystem.getInstance().addLeagueToDB(league1);

        league.addSeasonToLeague(season);

        Field field = new Field("Blomfield","teal aviv",1000);
        Field field1 = new Field("tedi","teal aviv",1000);
        FootballSystem.getInstance().addFieldToDB(field);
        FootballSystem.getInstance().addFieldToDB(field1);


        FootballSystem.getInstance().signIn("tair123","1234","tair","cohen");
        FootballSystem.getInstance().creatingTeamOwner("tair123","tair","cohen");

    }

    @Override
    public UserDetails getNewRegisteredUserForTest() {
        UserDetails newRandomUser = new UserDetails("UserName"+randomNumberForUser+"","UserPassword"+randomNumberForUser,"UserFirstName"+randomNumberForUser,"UserLastName"+randomNumberForUser);
        randomNumberForUser++;
        model.signIn(newRandomUser.userName,newRandomUser.password,newRandomUser.firstName,newRandomUser.LastName);
        return newRandomUser;
    }

    @Override
    public boolean login(String username, String pw) {
        try {
            return model.login(username,pw);
        } catch (FailedLoginException e) {
            return false;
        }
    }

    @Override
    public boolean register(String userName, String password, String firstName, String lastName) {
        return model.signIn(userName,password,firstName,lastName);
    }

    @Override
    public TeamDetails getNewRegisteredTeamForTest() {
        if(createNewTeam("RegisteredTeam","La Liga","2020","Blomfield")){
            return new TeamDetails("RegisteredTeam","La Liga","2020","Blomfield");
        }
        return null;
    }

    @Override
    public boolean createNewTeam(String name, String leagueName, String seasonYear, String fieldName) {
        //login first
        if(!login("tair123","1234")){
            return false;
        }
        //TeamOwner create new team
        try {
            return model.createTeam(name,leagueName,seasonYear,fieldName);
        } catch (RecordException e) {
            return false;
        }
    }

    @Override
    public boolean createNewTeamWithotTeamOwner(String name, String leagueName, String seasonYear, String fieldName) {
        //login first
        if(!login("admin","admin")){
            return false;
        }
        //TeamOwner create new team
        try {
            return model.createTeam(name,leagueName,seasonYear,fieldName);
        } catch (RecordException e) {
            return false;
        }
    }
}
