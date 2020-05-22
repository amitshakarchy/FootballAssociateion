package AcceptanceTests.SystemOperations;

import AcceptanceTests.DataObjects.TeamDetails;
import AcceptanceTests.DataObjects.UserDetails;
import AssociationAssets.*;
import System.FootballSystem;
import Model.*;
import Users.EReferee;
import Users.Referee;
import Users.TeamOwner;

import javax.security.auth.login.FailedLoginException;
import java.nio.file.Path;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

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

        FootballSystem.getInstance().signIn("1","1","lala","la");
        FootballSystem.getInstance().creatingReferee("1","la","laala", EReferee.MAIN);
        FootballSystem.getInstance().signIn("2","2","lala","la");
        FootballSystem.getInstance().creatingReferee("2","la","laala", EReferee.ASSISTANT);
        FootballSystem.getInstance().signIn("3","3","lala","la");
        FootballSystem.getInstance().creatingReferee("3","la","laala", EReferee.ASSISTANT);

        Team team1 = new Team(45,"team1",season,field,null,(TeamOwner)FootballSystem.getInstance().getFanByUserName("tair123"));
        Team team2 = new Team(55,"team2",season,field,null,(TeamOwner)FootballSystem.getInstance().getFanByUserName("tair123"));
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate = LocalDate.now();

        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

        Time time =Time.valueOf(LocalTime.of(LocalTime.now().getHour()-1,LocalTime.now().getMinute()));
        Referee main = FootballSystem.getInstance().getRefereeByUseName("1");
        Referee side1 = FootballSystem.getInstance().getRefereeByUseName("2");
        Referee side2 = FootballSystem.getInstance().getRefereeByUseName("3");
        Game game = null;
        try {
            game = new Game(date,time,field,team1,team2,main,side1,side2,season,league);
            game.addEvent(EEventType.GOALHOST,"descriprion");
        } catch (Exception e) {
            e.printStackTrace();
        }
        main.addGame(game);
        side1.addGame(game);
        side2.addGame(game);
        FootballSystem.getInstance().addGameToDB(game);
        FootballSystem.getInstance().signIn("r","r","r","r");
        FootballSystem.getInstance().creatingRepresentativeFootballAssociation("r","r","r",null);
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

    @Override
    public boolean addEvent(String userName,String password, int gameID, EEventType eventType, int eventIndex, String description) {
      if(!login(userName,password)){
          return false;
      }

        try {
            return model.addEvent(gameID,eventType.toString(),description);
        } catch (RecordException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean updateEvent(String userName,String password, int gameID, EEventType eventType, int eventIndex, String description) {
        if(!login(userName,password)){
            return false;
        }
        try {
            return model.updateEvent(gameID,eventIndex,eventType.toString(),description);
        } catch (RecordException e) {
            return false;
        }
    }

    @Override
    public boolean deleteEvent(String userName,String password, int gameID, EEventType eventType, int eventIndex, String description) {
        if(!login(userName,password)){
            return false;
        }
        try {
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).addEvent(EEventType.GOALHOST,"descriprion");
            return model.removeEvent(gameID,eventIndex);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteEventAfterGameOver(String userName, String password, int gameID, EEventType eventType, int eventIndex, String description,int hoursBefore) {
        if(!login(userName,password)){
            return false;
        }
        try {
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).addEvent(EEventType.GOALHOST,"descriprion");
            Time time =Time.valueOf(LocalTime.of(LocalTime.now().getHour()-hoursBefore,LocalTime.now().getMinute()));
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).setTime(time);
            return model.removeEventAfterGameOver(gameID,eventIndex);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean editEventAfterGameOver(String userName, String password, int gameID, EEventType eventType, int eventIndex, String description,int hoursBefore) {
        if(!login(userName,password)){
            return false;
        }
        boolean bool = false;
        try {
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).addEvent(EEventType.GOALHOST,"descriprion");
            Time time =Time.valueOf(LocalTime.of(LocalTime.now().getHour()-hoursBefore,LocalTime.now().getMinute()));
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).setTime(time);
              bool = model.updateEventAfterGameOver(gameID,eventIndex,eventType.toString(),description);
        } catch (RecordException e) {
            return false;
        }
        return bool;
    }

    @Override
    public boolean exportReport(String userName, String password, int gameID, int hoursBefore, String path) {
        if(!login(userName,password)){
            return false;
        }
        try {
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).addEvent(EEventType.GOALHOST,"descriprion");
            Time time =Time.valueOf(LocalTime.of(LocalTime.now().getHour()-hoursBefore,LocalTime.now().getMinute()));
            FootballSystem.getInstance().getGameDB().getAllGames().get(gameID).setTime(time);
            return model.exportGameReport(gameID,path,""+gameID);
        } catch (Exception e) {
            return false;
        }
    }
}
