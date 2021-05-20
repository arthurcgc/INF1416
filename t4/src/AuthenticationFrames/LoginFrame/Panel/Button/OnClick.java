package AuthenticationFrames.LoginFrame.Panel.Button;

import AuthenticationFrames.PasswordFrame.PasswordFrame;
import Database.*;

import Auth.Validation;
import Main.Main;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String email = Main.loginFrame.panel.textField.getText();

            if((Validation.verifyEmail(email) == false)) {
                Database.log(Registry.RegistryWithTimestamp(2005, email));
                Main.loginFrame.panel.warning.setText("Invalid email");
                return;
            }

            Database db = Database.getInstance();
            Date now = new Date();
            long milliNow = now.getTime();
            long userBlockedMilli= db.getInstance().getBlockedMilli(Validation.user.Email);
            if ( milliNow - userBlockedMilli < 120000 ) {
                Database.log(Registry.RegistryWithTimestamp(2004, email));
                JOptionPane.showMessageDialog(Main.loginFrame.panel, "User temporarily blocked", "Error", JOptionPane.OK_OPTION);
                return;
            }

            Database.log(Registry.RegistryWithTimestamp(2003, email));
            Database.log(Registry.RegistryWithTimestamp(2002));
            Database.getInstance().incAccessCounter(email);
            db.clearBlockedCounter(Validation.user.Email);
            db.insertBlocked(Validation.user.Email, 0);

            Main.loginFrame.dispose();
            Main.passwordFrame = new PasswordFrame();

        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
