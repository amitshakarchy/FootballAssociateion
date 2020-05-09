package Controllers;

import Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class MainPageController extends Controller{

    public Button manageTeamBtn;
    public Button managePolicyBtn;
    public Button manageGameBtn;
    static final String STYLE_SHEET = "../View/style.css";

    public MainPageController() {
    }

    @FXML
    public void manageTeamClick() {
        // TODO: 5/3/2020 need to validate the permission of the user
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageTeam.fxml"));
        setCorrectWindow(loader, manageTeamBtn,"Manage Team");
    }

    @FXML
    public void managePolicyClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManagePolicy.fxml"));
        setCorrectWindow(loader, managePolicyBtn,"Manage Policy");
    }

    @FXML
    public void manageGameClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/ManageGame.fxml"));
        setCorrectWindow(loader,manageGameBtn,"Manage Game");
    }

    private void setCorrectWindow(FXMLLoader loader, Button managePolicyBtn,String title) {
        Stage stage = new Stage();
        stage.initOwner(managePolicyBtn.getScene().getWindow());
        try {
            Scene scene = new Scene(loader.load());
            scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.setTitle(title);
        // showAndWait will block execution until the window closes...
        stage.showAndWait();





    }

}
