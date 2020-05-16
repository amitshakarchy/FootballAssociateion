package DAL;

public class databaseManagerServerMySQL extends DatabaseManager {

    public databaseManagerServerMySQL() {

        super();
    }

    @Override
    public void startConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            super.startConnection();
        } catch (Exception e) {
            System.out.println(String.format("Error starting connection to database '%s'", databaseName));
            System.out.println(e.getMessage());
        }
    }

}
