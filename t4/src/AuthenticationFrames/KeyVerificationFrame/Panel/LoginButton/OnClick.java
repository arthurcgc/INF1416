package AuthenticationFrames.KeyVerificationFrame.Panel.LoginButton;

import MenuFrame.MenuFrame;

import Database.*;
import Auth.*;
import Main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {
     private static MenuFrame menuframe = null;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            if (Validation.validatePrivateKey(Main.keyVerificationFrame.panel.passwordField.getText(), Main.keyVerificationFrame.panel.keyPath)) {

                Database.log(Registry.RegistryWithTimestamp(4003, Validation.user.Email));
                Database.log(Registry.RegistryWithTimestamp(4002, Validation.user.Email));

                Main.keyVerificationFrame.dispose();
                menuframe = MenuFrame.getInstance();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
