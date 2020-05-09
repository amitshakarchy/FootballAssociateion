package Controllers;

import Model.Model;
import javafx.scene.control.Alert;
import Model.RecordException;
public class Controller {
    public static Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    public void stillWorkingOnIt() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText("Ops.. Our system is still working on it \n" +
                " please try soon!");
        alert.showAndWait();
    }

    public void raiseAlert(RecordException e1){
        Alert alert;
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(e1.getErrorMessage());
        alert.showAndWait();
    }
}
