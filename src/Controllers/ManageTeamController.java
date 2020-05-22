package Controllers;

import AssociationAssets.Field;
import AssociationAssets.Team;
import DB.FieldDB;
import DB.TeamDB;
import Model.RecordException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import java.util.HashMap;
import java.util.Set;
import System.FootballSystem;

public class ManageTeamController extends Controller {

    @FXML
    public Button createNewTeamBtn;
    public Button addTP;
    public Button removeTP;
    public Button editTP;
    public ChoiceBox teamNameCB;
    public Button nominateTO;
    public Button nominateTM;
    public Button removeTO;
    public Button removeTM;
    public Button closeTeam;

    @FXML
    public void createNewTeam(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateTeam.fxml"));
        Stage stage = getStage(loader,createNewTeamBtn);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/CreateTeam.fxml"));
        Stage stage = getStage(loader,createNewTeamBtn);
        stage.setTitle("Edit Team Property");
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
        if(teamDB != null){
            HashMap<String, Team> teamHashMap = teamDB.getAllTeams();
            if(teamHashMap != null && teamHashMap.size() > 0){
                Set<String> teamSet = teamHashMap.keySet();
                for (String teamName : teamSet){
                    this.teamNameCB.getItems().add(teamName);
                }
            }
            else{
                raiseAlert(new RecordException("There is not teams at the DB"));
            }
        }
        else{
            raiseAlert(new RecordException("Teams DB is not exits"));
        }
    }
}
