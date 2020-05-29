package Controllers;

import Model.RecordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class ManagePolicyController extends Controller{

    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;
    public RequiredField requiredField4;

    public ChoiceBox policyChoiceBox;

    public TextField leagueName;
    public TextField season;
    public TextField leagueName2;
    public TextField season2;

    @FXML
    public void clickOnAssignGamePolicy(ActionEvent e) {

        requiredField1.eval();
        requiredField2.eval();
        if (!requiredField1.getHasErrors() && !requiredField2.getHasErrors()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            try {
                String policy = policyChoiceBox.getValue().toString();
                if(policy.equalsIgnoreCase("Regular Schedule Policy") || policy.equalsIgnoreCase("One Round Schedule Policy")){
                    model.defineGameSchedulingPolicy(policy,leagueName.getText(),season.getText());
                    alert.setTitle("Information Dialog");
                    alert.setContentText("The game schedule policy set successfully");
                }
                else{
                    model.defineScoreTablePolicy(policy,leagueName.getText(),season.getText());
                    alert.setTitle("Information Dialog");
                    alert.setContentText("The score policy set successfully");
                }

                alert.showAndWait();
            } catch (RecordException e1) {
                raiseAlert(e1);
            }
            catch (Exception e2){
                raiseAlert((RecordException) e2);
            }
        }

     /*
        requiredField1.eval();
        requiredField2.eval();
        requiredField3.eval();
        requiredField4.eval();
        requiredField5.eval();
        requiredField6.eval();
        if(!requiredField1.getHasErrors() && !requiredField2.getHasErrors() && !requiredField3.getHasErrors() &&
                !requiredField4.getHasErrors() && !requiredField5.getHasErrors()  && !requiredField6.getHasErrors()  ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            try {
                model.createTeam(teamName.getText(), leagueNameCB.getValue().toString(),
                        seasonChoiceBoxCB.getValue().toString(),fieldChoiceBox.getValue().toString());
                alert.setTitle("Information Dialog");
                alert.setContentText("The request has been sent to the \n" +
                        " Representative Football Association!");
                alert.showAndWait();
            } catch (RecordException e1) {
                raiseAlert(e1);
            }
        }

      */
        }


        public void init() {
        }

    }

