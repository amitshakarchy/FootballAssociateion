package Controllers;

import AssociationAssets.AdditionalInfo;
import AssociationAssets.Team;
import Model.RecordException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import System.FootballSystem;

import java.util.Set;


public class EditTeamPropertyController extends Controller {

    @FXML
    Button coach;
    @FXML
    Button player;
    @FXML
    Button teamManager;
    @FXML
    Button field;
    @FXML
    Button saveCoach;
    @FXML
    TextField coachFirstName;
    @FXML
    TextField coachLastName;
    @FXML
    ChoiceBox ETrainingCB;
    @FXML
    ChoiceBox ECoachRoleCB;
    @FXML
    ChoiceBox coachUserNameCB;
    @FXML
    Button saveTM;
    @FXML
    ChoiceBox teamManagerUserNameCB;
    @FXML
    TextField teamManagerFirstName;
    @FXML
    TextField teamManagerLastName;
    @FXML
    Button savePlayer;
    @FXML
    ChoiceBox playerUserNameCB;
    @FXML
    TextField playerFirstName;
    @FXML
    TextField playerLastName;
    @FXML
    ChoiceBox EPlayerRoleCB;
    @FXML
    Button saveField;
    @FXML
    ChoiceBox fieldNameCB;
    @FXML
    TextField fieldCity;
    @FXML
    TextField fieldCapacity;

    @FXML
    RequiredField requiredField1;
    @FXML
    RequiredField requiredField2;
    @FXML
    RequiredField requiredField3;
    @FXML
    RequiredField requiredField4;
    @FXML
    ErrorLabel e1;

    @FXML
    public void editCoach(){
        divisibleEditField();
        divisibleEditPlayer();
        divisibleEditTM();
        visibleEditCoach();
        coachUserNameCB.getItems().clear();
        if(additionalInfo != null){
            Set<String> coachSet = additionalInfo.getCoaches();
            for (String coach : coachSet) {
                this.coachUserNameCB.getItems().add(coach);
            }
        }
    }
    private void visibleEditCoach() {
        saveCoach.setVisible(true);
        coachUserNameCB.setVisible(true);
        coachFirstName.setVisible(true);
        coachLastName.setVisible(true);
        ETrainingCB.setVisible(true);
        ECoachRoleCB.setVisible(true);
    }
    private void divisibleEditCoach() {
        saveCoach.setVisible(false);
        coachUserNameCB.setVisible(false);
        coachFirstName.setVisible(false);
        coachLastName.setVisible(false);
        ETrainingCB.setVisible(false);
        ECoachRoleCB.setVisible(false);
    }
    @FXML
    public void editTM(){
        divisibleEditField();
        divisibleEditPlayer();
        divisibleEditCoach();
        visibleEditTM();
        this.teamManagerUserNameCB.getItems().clear();
        if(additionalInfo != null){
            Set<String> teamManagerSet = additionalInfo.getTeamManagersHashSet();
            for (String teamManager : teamManagerSet) {
                this.teamManagerUserNameCB.getItems().add(teamManager);
            }
        }

    }
    private void visibleEditTM() {
        saveTM.setVisible(true);
        teamManagerUserNameCB.setVisible(true);
        teamManagerFirstName.setVisible(true);
        teamManagerLastName.setVisible(true);
    }
    private void divisibleEditTM() {
        saveTM.setVisible(false);
        teamManagerUserNameCB.setVisible(false);
        teamManagerFirstName.setVisible(false);
        teamManagerLastName.setVisible(false);
    }
    @FXML
    public void editPlayer(){
        divisibleEditField();
        divisibleEditCoach();
        divisibleEditTM();
        visibleEditPlayer();
        this.playerUserNameCB.getItems().clear();
        if(additionalInfo != null){
            Set<String> playerSet = additionalInfo.getPlayers();
            for (String player : playerSet) {
                this.playerUserNameCB.getItems().add(player);
            }
        }
    }
    private void visibleEditPlayer() {
        savePlayer.setVisible(true);
        playerUserNameCB.setVisible(true);
        playerFirstName.setVisible(true);
        playerLastName.setVisible(true);
        EPlayerRoleCB.setVisible(true);
    }
    private void divisibleEditPlayer() {
        savePlayer.setVisible(false);
        playerUserNameCB.setVisible(false);
        playerFirstName.setVisible(false);
        playerLastName.setVisible(false);
        EPlayerRoleCB.setVisible(false);
    }
    @FXML
    public void editField(){
        divisibleEditPlayer();
        divisibleEditCoach();
        divisibleEditTM();
        visibleEditField();
        this.fieldNameCB.getItems().clear();
        if(team != null){
            Set<String> fieldSet = team.getFields().keySet();
            for (String f : fieldSet) {
                this.fieldNameCB.getItems().add(f);
            }
        }
    }
    private void visibleEditField() {
        saveField.setVisible(true);
        fieldNameCB.setVisible(true);
        fieldCity.setVisible(true);
        fieldCapacity.setVisible(true);
    }
    private void divisibleEditField() {
        saveField.setVisible(false);
        fieldNameCB.setVisible(false);
        fieldCity.setVisible(false);
        fieldCapacity.setVisible(false);
    }
    @FXML
    public void saveCoach(){
        requiredField1.eval();
        if(requiredField1.getHasErrors()){
            return;
        }
        try {
            String training = null;
            Object t = this.ETrainingCB.getValue();
            if(t != null){
                training = t.toString();
            }
            String role = null;
            Object r = this.ECoachRoleCB.getValue();
            if(r != null){
                role = r.toString();
            }
            model.editCoachDetails(teamName,seasonYear,coachUserNameCB.getValue().toString(),coachFirstName.getText(),
                    coachLastName.getText(),training,role);
        } catch (RecordException e) {
            raiseAlert(e);
        }
        this.coachLastName.clear();
        this.coachFirstName.clear();
    }
    @FXML
    public void saveTM(){
        requiredField2.eval();
        if(requiredField2.getHasErrors()){
            return;
        }
        try {
            model.editTeamManagerDetails(teamName,seasonYear,teamManagerUserNameCB.getValue().toString(),
                    teamManagerFirstName.getText(),
                    teamManagerLastName.getText());
        } catch (RecordException e) {
            raiseAlert(e);
        }
        this.teamManagerFirstName.clear();
        this.teamManagerLastName.clear();
    }
    @FXML
    public void savePlayer(){
        // TODO: 5/24/2020 add birthday
        requiredField3.eval();
        if(requiredField3.getHasErrors()){
            return;
        }
        try {
            String role = null;
            Object r = this.EPlayerRoleCB.getValue();
            if(r != null){
                role = r.toString();
            }
            model.editPlayerDetails(teamName,seasonYear,playerUserNameCB.getValue().toString(),playerFirstName.getText(),
                    playerLastName.getText(),role);
        } catch (RecordException e) {
            raiseAlert(e);
        }
        this.playerFirstName.clear();
        this.playerLastName.clear();
    }
    @FXML
    public void saveField(){
        requiredField4.eval();
        if(requiredField4.getHasErrors()){
            return;
        }

        try {
            model.editFieldDetails(teamName,seasonYear,fieldNameCB.getValue().toString(),fieldCity.getText(),
                    fieldCapacity.getText());
        } catch (RecordException e) {
            raiseAlert(e);
        }
        this.fieldCity.clear();
        this.fieldCapacity.clear();
    }

    private String teamName;
    private String seasonYear;
    private Team team;
    private AdditionalInfo additionalInfo;

    public void setTeamName(String teamName) {
        this.teamName = teamName;
        team = FootballSystem.getInstance().getTeamDB().getAllTeams().get(teamName);
        additionalInfo = team.getAdditionalInfoWithSeasons().get(seasonYear);
    }

    public void setSeasonYear(String seasonYear) {
            this.seasonYear = seasonYear;
    }
}
