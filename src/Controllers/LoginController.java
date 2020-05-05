package Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    public TextField userName;
    public TextField password;
    public RequiredField requiredField1;
    public RequiredField requiredField2;

//    @FXML
//    public void submitPressed(ActionEvent e) {
//        System.out.println("Login success");
//    }

    @FXML
    public void submitPressed(ActionEvent actionEvent) {
        requiredField1.eval();
        requiredField2.eval();
    }
}
