package AuthenticationFrames.PasswordFrame.Panel.LoginButton;

import Auth.Validation;
import AuthenticationFrames.KeyVerificationFrame.KeyVerificationFrame;
import Database.*;
import Auth.Blocked;
import Auth.Validation;
import Main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<String[]> passwordPhonemes = Main.passwordFrame.panel.passwordPhonemes;


            if(Blocked.isBlocked()) {
                Main.passwordFrame.warning("User email is blocked");
                return;
            }

            if(passwordPhonemes.size() != 4 && passwordPhonemes.size() != 5 && passwordPhonemes.size() != 6) {
                // TODO: change
                Main.passwordFrame.warningIncrease("Phoneme pairs must be between 4 and 6 (inclusive)");
                return;
            }

            if(Validation.guessPassword(passwordPhonemes) == false) {
                Main.passwordFrame.warningIncrease("Wrong password");
                return;
            }

            Database.log(Registry.RegistryWithTimestamp(3003, Validation.user.Email));
            Database.log(Registry.RegistryWithTimestamp(3002, Validation.user.Email));

            Main.passwordFrame.dispose();
            Main.keyVerificationFrame = new KeyVerificationFrame();

            Blocked.setFails(0);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
