package Controllers;

import Model.RecordException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class ManageGameController extends Controller {

    @FXML
    public Button createReport;
    public Button removeEvent;
    public Button editEvent;
    public Button addEvent;
    public ChoiceBox eventChoiceBox;
    public ChoiceBox gameID;
    public TextArea description;

    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;

    @FXML
    public void createReport() {

    }

    @FXML
    public void addEvent() {
        requiredField1.eval();
        requiredField2.eval();
        requiredField3.eval();
        if (!requiredField1.getHasErrors() && !requiredField2.getHasErrors() && !requiredField3.getHasErrors()) {
            int gameID = Integer.parseInt(this.gameID.getValue().toString());
            try {
                model.addEvent(gameID, eventChoiceBox.getValue().toString(), description.getText());
            } catch (RecordException e) {
                raiseAlert(e);
            }
        }
    }

    @FXML
    public void removeEvent() {
        stillWorkingOnIt();
    }

    @FXML
    public void editEvent() {
        stillWorkingOnIt();
    }
}

