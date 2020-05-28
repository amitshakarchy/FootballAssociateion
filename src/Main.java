
import AssociationAssets.*;
import Controllers.*;
import DAL.JDBCConnector;
import Model.Model;
import Users.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import System.FootballSystem;
import sun.misc.Contended;
import sun.misc.FpUtils;

import java.awt.*;
import java.sql.Time;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Main extends Application {
    static final String RESOURCE = "View/Login2.fxml";
    static final String STYLE_SHEET = "View/common-styles.css";
    static Stage stg;

    @Override
    public void start(Stage stage) throws Exception {

        this.stg = stage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource(RESOURCE).openStream());
        root.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        stage.setTitle("Football Association System");
        stage.setScene(new Scene(root, 900, 900));
        stage.show();
        //-------------
        initDB();
        Model myModel = new Model();
        //***************
        // TODO: 5/26/2020 add all rep to observers
        FootballSystem.getInstance().signIn("r","r","r","r");
        RepresentativeFootballAssociation r =
                (RepresentativeFootballAssociation) FootballSystem.getInstance().creatingRepresentativeFootballAssociation("r","r","r",null);
        myModel.addObserver(r);
        myModel.addObserver(fxmlLoader.getController());
        Controller controller = new Controller();
        controller.setModel(myModel);
        RepresentativeFootballAssociation.notificationTeams.add("bnlablalbal");
        // ***********

    }

    private void initDB() {
        JDBCConnector dbConnector= new JDBCConnector();
        dbConnector.connectDBUploadData();
        //region Tair's initialization
/*        Season season = new Season("2020");
        Season season1 = new Season("2021");
        FootballSystem.getInstance().addSeasonToDB(season);
        FootballSystem.getInstance().addSeasonToDB(season1);
        League league = new League("La Liga");
        League league1 = new League("liga");
        FootballSystem.getInstance().addLeagueToDB(league);
        FootballSystem.getInstance().addLeagueToDB(league1);
        league.addSeasonToLeague(season);
        Field field = new Field("Blomfield","teal aviv",1000);
        Field field1 = new Field("Tedi","teal aviv",1000);
        FootballSystem.getInstance().addFieldToDB(field);
        FootballSystem.getInstance().addFieldToDB(field1);
        FootballSystem.getInstance().signIn("4","4","tair","cohen");
        TeamOwner tairTO = (TeamOwner) FootballSystem.getInstance().creatingTeamOwner("4","tair","cohen");
        FootballSystem.getInstance().signIn("1","1","lala","la");
        FootballSystem.getInstance().creatingReferee("1","la","laala", EReferee.MAIN);
        FootballSystem.getInstance().signIn("2","2","lala","la");
        FootballSystem.getInstance().creatingReferee("2","la","laala", EReferee.ASSISTANT);
        FootballSystem.getInstance().signIn("3","3","lala","la");
        FootballSystem.getInstance().creatingReferee("3","la","laala", EReferee.ASSISTANT);

        Team team1 = new Team(45,"team1",season,field,null,(TeamOwner)FootballSystem.getInstance().getFanByUserName("4"));
        Team team2 = new Team(55,"team2",season,field,null,(TeamOwner)FootballSystem.getInstance().getFanByUserName("4"));
        team1.addSeasonToTeam(season);
        team1.addSeasonToTeam(season1);
        team2.addSeasonToTeam(season);
        tairTO.addCoach(team1,season,"coach-2020","123","c","c", ETraining.CDiploma,ECoachRole.AssistantCoach);
        tairTO.addField(team1,season,"Camp-No1","tel-aviv",12222);
        tairTO.addField(team1,season,"Camp-No2","tel-aviv",12222);
        tairTO.addTeamManager(team1,season,"TM-2020","123","la","la");
        tairTO.addPlayer(team1,season,"player-2020","123","la","la",new Date(17/10/1995),EPlayerRole.GoalKeeper);

        try {
            FootballSystem.getInstance().addTeamToDB(team1);
            FootballSystem.getInstance().addTeamToDB(team2);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Date date = new Date(2020-1900,4,12);
        Time time = new Time(14,40,0);
        Referee main = FootballSystem.getInstance().getRefereeByUseName("1");
        Referee side1 = FootballSystem.getInstance().getRefereeByUseName("2");
        Referee side2 = FootballSystem.getInstance().getRefereeByUseName("3");
        Game game = null;

        try {
            game = new Game(date,time,field,team1,team2,main,side1,side2,season,league);
        } catch (Exception e) {
            e.printStackTrace();
        }
        main.addGame(game);
        FootballSystem.getInstance().addGameToDB(game);*/
        //endregion
    }


    public static void main(String[] args) {
        launch(args);
    }

}
