package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    public TextField userName;
    public TextField password;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public Button loginBtn;
    @FXML
    public void submitPressed(ActionEvent actionEvent) {
        requiredField1.eval();
        requiredField2.eval();
        if(!requiredField1.getHasErrors() && !requiredField2.getHasErrors()){
            Parent newRoot = null;
            try {
                newRoot = FXMLLoader.load(getClass().getResource("../View/MainPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
            primaryStage.getScene().setRoot(newRoot);
        }
    }
}
