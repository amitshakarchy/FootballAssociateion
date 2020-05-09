package Controllers;
import Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController extends Controller{


    @FXML
    public TextField userName;
    public TextField password;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public Button loginBtn;
    static final String STYLE_SHEET = "../View/style.css";


    public LoginController() {
    }

    @FXML
    public void submitPressed(ActionEvent actionEvent) {
        requiredField1.eval();
        requiredField2.eval();
        if(!requiredField1.getHasErrors() && !requiredField2.getHasErrors()){
            if(model.login(userName.getText(),password.getText())){
                showMainPage();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username or Password incorrect. \n" +
                        "Please try again.");
                alert.showAndWait();
            }
        }
    }

    private void showMainPage() {
        Parent newRoot = null;
        try {
            newRoot = FXMLLoader.load(getClass().getResource("../View/MainPage.fxml"));
            newRoot.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage primaryStage = (Stage) loginBtn.getScene().getWindow();
        primaryStage.getScene().setRoot(newRoot);
        primaryStage.setResizable(true);
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(450);
        primaryStage.setTitle("Football Association System");
    }
}
