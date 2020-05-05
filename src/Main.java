

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static final String RESOURCE = "View/CreateTeam.fxml";
    static final String STYLE_SHEET = "View/style.css";

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(RESOURCE));
        root.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        Scene scene = new Scene(root,400,600);
        primaryStage.setTitle("Football Association System");
        primaryStage.setScene(scene);
        primaryStage.show();
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


