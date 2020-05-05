package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class CreateTeamController {

    @FXML
    public void clickOnCreateTeam(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("The request has been sent to the \n" +
                " Representative Football Association!");
        alert.showAndWait();
    }
}