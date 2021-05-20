package Main;

import AuthenticationFrames.KeyVerificationFrame.KeyVerificationFrame;
import Auth.User;
import AuthenticationFrames.LoginFrame.LoginFrame;
import AuthenticationFrames.PasswordFrame.PasswordFrame;
import Database.*;

public class Main {
    public static LoginFrame loginFrame;
    public static PasswordFrame passwordFrame;
    public static KeyVerificationFrame keyVerificationFrame;

    private static void insertFirstUsers() throws Exception {
        // User user01 = User.NewUser("user01", "usuario", "user01-pkcs8-des.key", "user01-x509.crt", "FACAFEHO");
        User admin = User.NewUser("admin", "admin", "admin-pkcs8-des.key", "admin-x509.crt", "FACAFEHO");
        try {
            Database db = Database.getInstance();
            // db.insertUser(user01);
            // db.insertGroup(user01.Group, user01.GetGroupID());
            db.insertUser(admin);
            db.insertGroup(admin.Group, admin.GetGroupID());
        } catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        try{
            insertFirstUsers();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        Database.log(Registry.RegistryWithTimestamp(1001));
        loginFrame = new LoginFrame();
    }
}
