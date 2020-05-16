package DAL;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JDBCConnectorTest {

    @Test
    void connectDB() {
        JDBCConnector connector= new JDBCConnector();
        connector.connectDB();
    }
}