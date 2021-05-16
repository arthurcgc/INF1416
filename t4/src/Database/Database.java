
package Database;

import Auth.CertificateHelper;
import Auth.User;

import javax.xml.bind.DatatypeConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Database {

    public Connection conn = null;

    private static Database database = null;
    private final int loginCode = 1;


    // singleton
    private Database() {}

    public static Database getInstance() throws Exception {
        if(database != null)
            return database;

        database = new Database();

        String url = "jdbc:sqlite:database/vault.db";

        database.conn = DriverManager.getConnection(url);

        return database;
    }

    public static void log(Registry registry) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO registry(timestamp, user_id, event_code) VALUES (?, ?, ?)");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, registry.Timestamp);
        if (registry.UserEmail != null){
            ps.setInt(2, User.GetUserID(registry.UserEmail));
        }

        ps.setInt(3, registry.Code);

        ps.executeUpdate();
    }

    public void incAccessCounter(String email) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET access_counter = access_counter + 1 WHERE email=?;");
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ps.executeUpdate();
        ps.close();
    }

    public String getGroupName(int id) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT name FROM groups WHERE id = ?;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet resultSet = ps.executeQuery();

        String name = resultSet.getString("name");
        resultSet.close();
        ps.close();

        return name;
    }

    public ResultSet getUser(String email) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("SELECT * FROM users WHERE email = ?;");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);

        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public ResultSet getUsersCount() throws Exception {
        String query = String.format("SELECT COUNT(*) FROM users;");
        PreparedStatement ps = Database.getInstance().conn.prepareStatement(query);
        ResultSet resultSet = ps.executeQuery();

        return resultSet;
    }

    public int getEmailCount(String email) throws Exception {
        Database db = Database.getInstance();
        String query = "SELECT COUNT(email) FROM users WHERE email=?;";
        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, email);
        ResultSet resultSet = ps.executeQuery();
        int count = resultSet.getInt("COUNT(email)");

        resultSet.close();
        ps.close();
        return count;
    }

    public void insertUser(Auth.User user) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO users(email,hash,salt,certificate,group_id,uid) VALUES (?,?,?,?,?,?);");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setString(1, user.Email);
        ps.setString(2, DatatypeConverter.printHexBinary(user.Hash));
        ps.setString(3, new String(user.Salt));
        ps.setString(4, CertificateHelper.getCertificateBase64(user.Certificate));
        ps.setInt(5, user.GetGroupID());
        ps.setInt(6, User.GetUserID(user.Email));

        ps.executeUpdate();
        ps.close();
    }

    public void insertGroup(String groupString, int groupID) throws Exception{
        Database db = Database.getInstance();
        String query = String.format("INSERT INTO groups(id,name) VALUES (?,?);");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setInt(1, groupID);
        ps.setString(2, groupString);

        ps.executeUpdate();
        ps.close();
    }

    public void setPassword(Auth.User user) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET hash = ?, salt = ? WHERE email = ?");

        PreparedStatement ps = db.conn.prepareStatement(query);
        ps.setString(1, DatatypeConverter.printHexBinary(user.Hash));
        ps.setString(2, new String(user.Salt));
        ps.setString(3, user.Email);

        ps.executeUpdate();
        ps.close();
    }

    public void setCertificate(Auth.User user) throws Exception {
        Database db = Database.getInstance();
        String query = String.format("UPDATE users SET certificate = ? WHERE email = ?");
        PreparedStatement ps = db.conn.prepareStatement(query);

        ps.setString(1, Auth.CertificateHelper.getCertificateBase64(user.Certificate));
        ps.setString(2, user.Email);

        ps.executeUpdate();
        ps.close();
    }
}