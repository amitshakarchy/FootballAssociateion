package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class CreateTeamController {


    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;
    public RequiredField requiredField4;
    public RequiredField requiredField5;
    public RequiredField requiredField6;

    @FXML
    public void clickOnCreateTeam(ActionEvent e) {
        requiredField1.eval();
        requiredField2.eval();
        requiredField3.eval();
        requiredField4.eval();
        requiredField5.eval();
        requiredField6.eval();
        if(!requiredField1.getHasErrors() && !requiredField2.getHasErrors() && !requiredField3.getHasErrors() &&
                !requiredField4.getHasErrors() && !requiredField5.getHasErrors()  && !requiredField6.getHasErrors()  ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("The request has been sent to the \n" +
                    " Representative Football Association!");
            alert.showAndWait();
        }
    }
}