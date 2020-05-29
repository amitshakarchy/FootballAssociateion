package DAL;

import AssociationAssets.Event;
import AssociationAssets.Game;
import org.junit.Test;
import System.*;
import static org.junit.Assert.*;

public class DeleteDataTest {
    JDBCConnector connector;

    @Test
    public void deleteEvent() {

        connector = new JDBCConnector();
        connector.connectDBUploadData();
        Game g= FootballSystem.getInstance().getGameDB().getAllGames().get(1);
        Event event= g.getEvents().get(1);
        connector.deleteEvent(event, 1);

    }




}