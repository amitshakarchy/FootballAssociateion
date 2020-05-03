package Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ManageTeamController {

    @FXML
    public Button createNewTeamBtn;

    @FXML
    public void createNewTeam(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateTeam.fxml"));
        Stage stage = new Stage();
        stage.initOwner(createNewTeamBtn.getScene().getWindow());
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }
}
