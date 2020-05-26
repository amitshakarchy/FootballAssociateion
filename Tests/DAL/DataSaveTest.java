package DAL;

import AssociationAssets.*;
import PoliciesAndAlgorithms.SimpleGamesAssigningPolicy;
import Users.EReferee;
import Users.Fan;
import Users.Referee;
import Users.RepresentativeFootballAssociation;
import org.junit.Test;
import System.FootballSystem;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DataSaveTest {

    JDBCConnector connector;

    @Test
    public void saveAdditionalInfo() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.databaseManager.startConnection();
        connector.dataSave.saveAdditionalInfo();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveGames() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        Game g= FootballSystem.getInstance().getGameDB().getAllGames().get(1);
        g.setGID(66);
        FootballSystem.getInstance().getGameDB().getAllGames().put(66,g);
        connector.databaseManager.startConnection();
        connector.dataSave.saveGames();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveTeams() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        HashMap<String, Team> allTeams = FootballSystem.getInstance().getTeamDB().getAllTeams();
        Team t= allTeams.get("Beitar Jerusalem");
        t.setName("Amit");
        FootballSystem.getInstance().getTeamDB().getAllTeams().put(t.getName(),t);
        connector.databaseManager.startConnection();
        connector.dataSave.saveTeams();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveSeasons() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getSeasonDB().getAllSeasons().put("2050",new Season("2050"));
        connector.databaseManager.startConnection();
        connector.dataSave.saveSeasons();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveLeagues() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getLeagueDB().getAllLeagues().put("Amit League", new League("Amit League"));
        connector.databaseManager.startConnection();
        connector.dataSave.saveLeagues();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveFields() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getFieldDB().getAllFields().put("Amit", new Field("Amit", "Amit", 3000));
        connector.databaseManager.startConnection();
        connector.dataSave.saveFields();
        connector.databaseManager.closeConnection();

    }

    @Test
    public void savePasswordsUsers() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getFansHashMap().put("Amit", new Fan("Amit","Amit","Amit"));
        FootballSystem.getInstance().getSecuritySystem().getUsersHashMap("iseFab5").put("Amit","Amit");
        connector.databaseManager.startConnection();
        connector.dataSave.saveFans();
        connector.dataSave.savePasswordsUsers();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveFans() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getFansHashMap().put("Amit", new Fan("Amit","Amit","Amit"));
        connector.databaseManager.startConnection();
        connector.dataSave.saveFans();
        connector.databaseManager.closeConnection();

    }

    @Test
    public void saveReferees() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        Referee ref= new Referee("Amit","Amit","Amit", EReferee.VAR);
        FootballSystem.getInstance().getRefereeMap().put("Amit", ref);
        FootballSystem.getInstance().getFansHashMap().put("Amit",ref);
        connector.databaseManager.startConnection();
        connector.dataSave.saveFans();
        connector.dataSave.saveReferees();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveRFAs() { // Checked
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        RepresentativeFootballAssociation rep= new RepresentativeFootballAssociation("Amit","Amit", "Amit",new SimpleGamesAssigningPolicy());
        FootballSystem.getInstance().getRepresentativeFootballAssociationMap().put("Amit",rep);
        FootballSystem.getInstance().getFansHashMap().put("Amit",rep);
        connector.databaseManager.startConnection();
        connector.dataSave.saveFans();
        connector.dataSave.saveRFAs();
        connector.databaseManager.closeConnection();

    }

    @Test
    public void saveSystemManagers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveSystemManagers();
    }

    @Test
    public void saveTeamOwners() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveTeamOwners();
    }

    @Test
    public void saveTeamManagers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveTeamManagers();
    }

    @Test
    public void saveCoaches() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveCoaches();
    }

    @Test
    public void savePlayers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.savePlayers();
    }
}