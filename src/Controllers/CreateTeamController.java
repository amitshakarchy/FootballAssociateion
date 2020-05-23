package Controllers;
import AssociationAssets.Field;
import AssociationAssets.League;
import AssociationAssets.Season;
import DB.FieldDB;
import DB.LeagueDB;
import DB.SeasonDB;
import Model.RecordException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import System.FootballSystem;

import java.util.HashMap;
import java.util.Set;


public class CreateTeamController extends Controller{

    public RequiredField requiredField1;
    public RequiredField requiredField2;
    public RequiredField requiredField3;
    public RequiredField requiredField4;
    public RequiredField requiredField5;
    public RequiredField requiredField6;

    public TextField teamID;
    public TextField teamName;
    public TextField teamBudget;
    public ChoiceBox leagueNameCB;
    public ChoiceBox fieldChoiceBox;
    public ChoiceBox seasonChoiceBoxCB;


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
    }
    
    public void init(){
        // init league DB
        LeagueDB leagueDB = FootballSystem.getInstance().getLeagueDB();
        if(leagueDB != null){
            HashMap<String,League> leagueHashMap = leagueDB.getAllLeagues();
            if(leagueHashMap != null && leagueHashMap.size() > 0){
                Set<String> leagueSet = leagueHashMap.keySet();
                for (String leagueName : leagueSet){
                    this.leagueNameCB.getItems().add(leagueName);
                }
            }
            else{
                raiseAlert(new RecordException("There is not leagues at the DB"));
            }
        }
        else{
            raiseAlert(new RecordException("leagues DB is not exits"));
        }

        // init seasons DB
        SeasonDB seasonDB = FootballSystem.getInstance().getSeasonDB();
        if(seasonDB != null){
            HashMap<String, Season> seasonHashMap = seasonDB.getAllSeasons();
            if(seasonHashMap != null && seasonHashMap.size() > 0){
                Set<String> seasonSet = seasonHashMap.keySet();
                for (String seasonName : seasonSet){
                    this.seasonChoiceBoxCB.getItems().add(seasonName);
                }
            }
            else{
                raiseAlert(new RecordException("There is not seasons at the DB"));
            }
        }
        else{
            raiseAlert(new RecordException("seasons DB is not exits"));
        }

        //init home fields
        FieldDB fieldDB = FootballSystem.getInstance().getFieldDB();
        if(fieldDB != null){
            HashMap<String, Field> fieldHashMap = fieldDB.getAllFields();
            if(fieldHashMap != null && fieldHashMap.size() > 0){
                Set<String> fieldSet = fieldHashMap.keySet();
                for (String fieldName : fieldSet){
                    this.fieldChoiceBox.getItems().add(fieldName);
                }
            }
            else{
                raiseAlert(new RecordException("There is not fields at the DB"));
            }
        }
        else{
            raiseAlert(new RecordException("Fields DB is not exits"));
        }
    }
}