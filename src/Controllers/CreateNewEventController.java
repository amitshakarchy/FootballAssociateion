package Controllers;
import Model.RecordException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class CreateNewEventController extends Controller {


    public ChoiceBox gameID;
    public ChoiceBox eventChoiceBox;
    public TextArea description;
    public Button addEvent;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;

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
}
