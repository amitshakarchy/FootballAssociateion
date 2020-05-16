package DAL;

import AssociationAssets.*;
import DB.*;
import PoliciesAndAlgorithms.*;
import System.*;
import Users.*;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class JDBCConnector {

    FootballSystem system;
    DatabaseManager databaseManager;
    HashMap<String, Season> allSeasons;
    HashMap<String, League> allLeagues;
    HashMap<String,Team> allTeams;
    HashMap<String, Field> allFields;
    Map<String, Coach> allCoaches;

    // Users:
    Map<String, Fan> allFans;
    Map<String, TeamOwner> allTeamOwners;
    Map<String, Player> allPlayers;
    private Map<String, TeamManager> allTeamManagers;
    private Map<String, SystemManager> allSystemManagers;
    private Map<String, RepresentativeFootballAssociation> allRFAs;
    private Map<String, Referee> allReferees;

    public void connectDBUploadData() {

        databaseManager = new databaseManagerServerMySQL();
        databaseManager.startConnection();

        DataUploader uploader = new DataUploader(databaseManager);
        uploader.uploadData();

        databaseManager.closeConnection();
    }

    public void connectSaveOnDB() {

    }




}
