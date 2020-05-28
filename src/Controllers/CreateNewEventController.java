package Controllers;
import Model.RecordException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CreateNewEventController extends Controller {


    public ComboBox cmbGameIDType;
    public ComboBox cmbEventType1;
    public TextArea txtDescription;
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
            int gameID = Integer.parseInt(this.cmbGameIDType.getValue().toString());
            try {
                if(model.addEvent(gameID, cmbEventType1.getValue().toString(), txtDescription.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("The event was adding successfully!");
                    alert.showAndWait();
                }
            } catch (RecordException e) {
                raiseAlert(e);
            }
        }
    }

    public void init() {
        initGameIdCB(this.cmbGameIDType);
    }
}
