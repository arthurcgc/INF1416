package AuthenticationFrames.PasswordFrame.Panel.LoginButton;

import Auth.Validation;
import AuthenticationFrames.KeyVerificationFrame.KeyVerificationFrame;
import Database.*;
import Main.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<String[]> passwordPhonemes = Main.passwordFrame.panel.passwordPhonemes;

            if(passwordPhonemes.size() != 4 && passwordPhonemes.size() != 5 && passwordPhonemes.size() != 6) {
                Database db = Database.getInstance();
                db.incBlockCounter(Validation.user.Email);
                JOptionPane.showMessageDialog(Main.keyVerificationFrame.panel, "Invalid password", "Error", JOptionPane.OK_OPTION);
                if( db.getBlockedCount(Validation.user.Email) >= 3 ){
                    Main.passwordFrame.dispose();
                    Main.loginFrame.setVisible(true);
                    Date date = new Date();
                    // This method returns the time in millis
                    long timeMilli = date.getTime();
                    Database.getInstance().insertBlocked(Validation.user.Email, timeMilli);
                }
                return;
            }

            if(Validation.guessPassword(passwordPhonemes) == false) {
                Database db = Database.getInstance();
                db.incBlockCounter(Validation.user.Email);
                JOptionPane.showMessageDialog(Main.passwordFrame.panel, "Invalid password", "Error", JOptionPane.OK_OPTION);
                int blockedCount = db.getBlockedCount(Validation.user.Email);
                if (blockedCount == 1) {
                    Database.log(Registry.RegistryWithTimestamp(3004, Validation.user.Email));
                }
                if (blockedCount == 2) {
                    Database.log(Registry.RegistryWithTimestamp(3005, Validation.user.Email));
                }
                if( blockedCount >= 3 ){
                    Database.log(Registry.RegistryWithTimestamp(3006, Validation.user.Email));
                    Main.passwordFrame.dispose();
                    Main.loginFrame.setVisible(true);

                    Date date = new Date();
                    //This method returns the time in millis
                    long timeMilli = date.getTime();
                    Database.getInstance().insertBlocked(Validation.user.Email, timeMilli);
                }
                return;
            }

            Database db = Database.getInstance();
            db.clearBlockedCounter(Validation.user.Email);
            db.insertBlocked(Validation.user.Email, 0);

            Database.log(Registry.RegistryWithTimestamp(3003, Validation.user.Email));
            Database.log(Registry.RegistryWithTimestamp(3002, Validation.user.Email));

            Main.passwordFrame.dispose();
            Main.keyVerificationFrame = new KeyVerificationFrame();
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
