package AuthenticationFrames.KeyVerificationFrame.Panel.LoginButton;

import MenuFrame.MenuFrame;

import Database.*;
import Auth.*;
import Main.Main;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class OnClick implements ActionListener {
     private static MenuFrame menuframe = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (Validation.validatePrivateKey(Main.keyVerificationFrame.panel.passwordField.getText(), Main.keyVerificationFrame.panel.keyPath)) {

                Database.log(Registry.RegistryWithTimestamp(4003, Validation.user.Email));
                Database.log(Registry.RegistryWithTimestamp(4002, Validation.user.Email));

                Database db = Database.getInstance();

                db.clearBlockedCounter(Validation.user.Email);
                db.insertBlocked(Validation.user.Email, 0);


                Main.keyVerificationFrame.dispose();
                menuframe = MenuFrame.getInstance();
            }
            else {
                Database db = Database.getInstance();
                db.incBlockCounter(Validation.user.Email);
                JOptionPane.showMessageDialog(Main.keyVerificationFrame.panel, "Invalid key + passphrase", "Error", JOptionPane.OK_OPTION);
                Database.log(Registry.RegistryWithTimestamp(4005, Validation.user.Email));
                if( db.getBlockedCount(Validation.user.Email) >= 3 ){
                    Database.log(Registry.RegistryWithTimestamp(4007, Validation.user.Email));
                    Main.keyVerificationFrame.dispose();
                    Main.loginFrame.setVisible(true);

                    Date date = new Date();
                    //This method returns the time in millis
                    long timeMilli = date.getTime();
                    Database.getInstance().insertBlocked(Validation.user.Email, timeMilli);
                    return;
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
