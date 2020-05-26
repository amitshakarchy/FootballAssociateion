package DAL;

import AssociationAssets.AdditionalInfo;
import AssociationAssets.Game;
import AssociationAssets.Season;
import org.junit.Test;
import System.FootballSystem;
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
    public void saveGames() {
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
    public void saveTeams() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.databaseManager.startConnection();
        connector.dataSave.saveTeams();
        connector.databaseManager.closeConnection();

    }

    @Test
    public void saveSeasons() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        FootballSystem.getInstance().getSeasonDB().getAllSeasons().put("2050",new Season("2050"));
        connector.databaseManager.startConnection();
        connector.dataSave.saveSeasons();
        connector.databaseManager.closeConnection();
    }

    @Test
    public void saveLeagues() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveLeagues();
    }

    @Test
    public void saveFields() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveFields();
    }

    @Test
    public void savePasswordsUsers() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.savePasswordsUsers();
    }

    @Test
    public void saveFans() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveFans();
    }

    @Test
    public void saveReferees() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveReferees();
    }

    @Test
    public void saveRFAs() {
        connector= new JDBCConnector();
        connector.connectDBUploadData();
        connector.dataSave.saveRFAs();
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