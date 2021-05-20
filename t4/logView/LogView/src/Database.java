
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    public Connection conn = null;

    private static Database database = null;


    // singleton
    private Database() {}


    public static Database getInstance() throws Exception {
        if(database != null)
            return database;

        database = new Database();

        String url = "jdbc:sqlite:/Users/arthur.coelho/Documents/inf1416/t4/database/vault.db";

        database.conn = DriverManager.getConnection(url);
        return database;
    }

    public static ResultSet getLogs() throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT messages.content,registry.user_id,registry.event_code,registry.timestamp FROM messages INNER JOIN registry ON messages.time_nano = registry.time_nano AND messages.user_id = registry.user_id ORDER BY registry.time_nano;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();
        // ps.close();

        return resultSet;
    }

    public static String getUserEmail(int uid) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT email FROM users WHERE uid = ?;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setInt(1, uid);
        ResultSet resultSet = ps.executeQuery();

        String email = resultSet.getString("email");
        resultSet.close();
        return email;
    }
}