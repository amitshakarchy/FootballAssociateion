package Controllers;

import Model.Model;

import Model.RecordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class CreateTeamController {

    Model model;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;
    public RequiredField requiredField4;
    public RequiredField requiredField5;
    public RequiredField requiredField6;

    public TextField teamID;
    public TextField teamName;
    public ChoiceBox leagueName;
    public TextField teamBudget;
    public ChoiceBox fieldChoiceBox;
    public ChoiceBox seasonChoiceBox;

    public CreateTeamController() {
        this.model = new Model();
    }

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
            try {
                System.out.println((model.createTeam(teamName.getText(),leagueName.getSelectionModel().getSelectedItem().toString(),
                        seasonChoiceBox.getValue().toString(),teamName.getText(),null)));
            } catch (RecordException e1) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e1.getErrorMessage());
            }
        }
    }
}