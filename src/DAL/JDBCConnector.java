package DAL;

import AssociationAssets.*;
import System.*;
import Users.*;

import java.util.*;

/**
 * JDBC Connector in in charge of connecting with the DB.
 * Offers uploading and downloading all data do/from the system's datasets.
 */
public class JDBCConnector {

    DatabaseManager databaseManager;

    public void connectDBUploadData() {
        databaseManager = new DatabaseManagerServerMySQL();
        databaseManager.startConnection();
        DataUploader uploader = new DataUploader(databaseManager);
        uploader.uploadData();
        databaseManager.closeConnection();
    }

    public void connectDBSaveData() {
        databaseManager = new DatabaseManagerServerMySQL();
        databaseManager.startConnection();
        DataSave dataSave = new DataSave(databaseManager);
        dataSave.saveAllData();
        databaseManager.closeConnection();
    }


}
