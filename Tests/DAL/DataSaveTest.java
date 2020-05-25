package DAL;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataSaveTest {

    JDBCConnector connector;
    DataSave dataSave;

    @Test
    public void saveAdditionalInfo() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveAdditionalInfo();
    }

    @Test
    public void saveGames() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveGames();
    }

    @Test
    public void saveTeams() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveTeams();
    }

    @Test
    public void saveSeasons() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveSeasons();
    }

    @Test
    public void saveLeagues() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveLeagues();
    }

    @Test
    public void saveFields() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveFields();
    }

    @Test
    public void savePasswordsUsers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.savePasswordsUsers();
    }

    @Test
    public void saveFans() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveFans();
    }

    @Test
    public void saveReferees() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveReferees();
    }

    @Test
    public void saveRFAs() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveRFAs();
    }

    @Test
    public void saveSystemManagers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveSystemManagers();
    }

    @Test
    public void saveTeamOwners() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveTeamOwners();
    }

    @Test
    public void saveTeamManagers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveTeamManagers();
    }

    @Test
    public void saveCoaches() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.saveCoaches();
    }

    @Test
    public void savePlayers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        dataSave.savePlayers();
    }
}