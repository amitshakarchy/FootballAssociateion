
import AssociationAssets.*;
import Controllers.*;
import Model.Model;
import Users.Coach;
import Users.EReferee;
import Users.Referee;
import Users.TeamOwner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import System.FootballSystem;
import sun.misc.Contended;
import sun.misc.FpUtils;

import java.sql.Time;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Main extends Application {
    static final String RESOURCE = "View/Login.fxml";
    static final String STYLE_SHEET = "View/style.css";
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
        Controller controller = new Controller();
        controller.setModel(myModel);
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
        Date date = new Date(2020-1900,4,17,13,0);
        Time time = new Time(9,0,0);
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
        FootballSystem.getInstance().addGameToDB(game);
        FootballSystem.getInstance().signIn("r","r","r","r");
        FootballSystem.getInstance().creatingRepresentativeFootballAssociation("r","r","r",null);
    }


    public static void main(String[] args) {
        launch(args);
    }

}


