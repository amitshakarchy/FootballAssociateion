package Controllers;

import AssociationAssets.Team;
import DB.TeamDB;
import Model.RecordException;
import System.FootballSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Set;

public class ManageTeamController extends Controller {

    @FXML
    public Button createNewTeamBtn;
    public Button addTP;
    public Button removeTP;
    public Button editTP;
    public ComboBox cmbTeamNameType;
    public Button nominateTO;
    public Button nominateTM;
    public Button removeTO;
    public Button removeTM;
    public Button closeTeam;
    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public ComboBox cmbSeasonType;

    @FXML
    public void teamNameCBChoose() {
        //init season DB
        cmbSeasonType.getItems().clear();
        cmbSeasonType.setVisible(true);
        Team team = FootballSystem.getInstance().getTeamDB().getAllTeams().get(cmbTeamNameType.getValue().toString());
        Set<String> seasonSet = team.getAdditionalInfoWithSeasons().keySet();
        for (String seasonName : seasonSet) {
            this.cmbSeasonType.getItems().add(seasonName);
        }
    }

    @FXML
    public void createNewTeam() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateNewTeamUI.fxml"));
        Stage stage = getStage(loader, createNewTeamBtn);
        stage.setTitle("Create New Team");
        CreateTeamController createTeamController = loader.getController();
        createTeamController.init();
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    @FXML
    public void addTeamProp() {
        stillWorkingOnIt();
    }

    @FXML
    public void removeTeamProp() {
        stillWorkingOnIt();
    }

    @FXML
    public void editTeamProp() {
        requiredField1.eval();
        requiredField2.eval();
        if (requiredField1.getHasErrors() || requiredField2.getHasErrors()) {
            return;
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/EditTeamProperty.fxml"));
        Stage stage = getStage(loader, editTP);
        stage.setTitle("Edit Team Property");
        EditTeamPropertyController controller = loader.getController();
        controller.setSeasonYear(cmbSeasonType.getValue().toString());
        controller.setTeamName(cmbTeamNameType.getValue().toString());
        // showAndWait will block execution until the window closes...
        stage.showAndWait();
    }

    @FXML
    public void nominateTeamOwner() {
        stillWorkingOnIt();
    }

    @FXML
    public void nominateTeamManager() {
        stillWorkingOnIt();
    }

    @FXML
    public void removeTeamOwner() {
        stillWorkingOnIt();
    }

    @FXML
    public void removeTeamManager() {
        stillWorkingOnIt();
    }

    @FXML
    public void closeTeam() {
        stillWorkingOnIt();
    }

    public void init() {
        //init team DB
        TeamDB teamDB = FootballSystem.getInstance().getTeamDB();
        if (teamDB != null) {
            HashMap<String, Team> teamHashMap = teamDB.getAllTeams();
            if (teamHashMap != null && teamHashMap.size() > 0) {
                Set<String> teamSet = teamHashMap.keySet();
                for (String teamName : teamSet) {
                    this.cmbTeamNameType.getItems().add(teamName);
                }
            } else {
                raiseAlert(new RecordException("There is not teams at the DB"));
            }
        } else {
            raiseAlert(new RecordException("Teams DB is not exits"));
        }
    }

    public void seasonCBChoose() {

    }
}
