
import AssociationAssets.Field;
import AssociationAssets.League;
import AssociationAssets.Season;
import AssociationAssets.SeasonLeagueBinder;
import Controllers.*;
import Model.Model;
import Users.Coach;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import System.FootballSystem;
import sun.misc.Contended;
import sun.misc.FpUtils;

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
//        MainPageController mainPageController = fxmlLoader.getController();
////        mainPageController.setModel(myModel);
////        ManageGameController manageGameController = fxmlLoader.getController();
////        manageGameController.setModel(myModel);
////        eamController createTeamController = fxmlLoader.getController();
////        createTeamController.setModel(myModel);
        //-------------

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


    public static void main(String[] args) {
        launch(args);
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//    Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
//    primaryStage.setTitle("Football Association System");
//    primaryStage.setScene(new Scene(root, 400, 600));
//    primaryStage.show();
//}
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
}


