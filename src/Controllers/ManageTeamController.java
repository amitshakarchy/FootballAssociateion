package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageTeamController extends Controller {

    static final String STYLE_SHEET = "../View/style.css";
    @FXML
    public Button createNewTeamBtn;
    public Button addTP;
    public Button removeTP;
    public Button editTP;
    public ChoiceBox teamName;
    public Button nominateTO;
    public Button nominateTM;
    public Button removeTO;
    public Button removeTM;
    public Button closeTeam;
    public Button editTeamDetails;

    @FXML
    public void createNewTeam() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateTeam.fxml"));
        Stage stage = new Stage();
        stage.initOwner(createNewTeamBtn.getScene().getWindow());
        try {
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle("Create New Team");
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
    @FXML
    public void addTeamProp() {
        stillWorkingOnIt();
    }
    @FXML
    public void removeTeamProp() {
        stillWorkingOnIt();
    }
    @FXML
    public void editTeamProp() {
        stillWorkingOnIt();
    }
    @FXML
    public void nominateTeamOwner() {
        stillWorkingOnIt();
    }
    @FXML
    public void nominateTeamManager() {
        stillWorkingOnIt();
    }
    @FXML
    public void removeTeamOwner() {
        stillWorkingOnIt();
    }
    @FXML
    public void removeTeamManager() {
        stillWorkingOnIt();
    }
    @FXML
    public void closeTeam() {
        stillWorkingOnIt();
    }
    @FXML
    public void editTeamDetails() {
        stillWorkingOnIt();
    }

}
