package AuthenticationFrames.LoginFrame.Panel.Button;

import AuthenticationFrames.PasswordFrame.PasswordFrame;
import Database.*;

import Auth.Validation;
import Main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            String email = Main.loginFrame.panel.textField.getText();
//            email = "user01@inf1416.puc-rio.br";
//            email = "admin@inf1416.puc-rio.br";

            if((Validation.verifyEmail(email) == false)) {
                Database.log(Registry.RegistryWithTimestamp(2005, email));
                Main.loginFrame.panel.warning.setText("Invalid email");

                return;
            }

//            if(Blocked.isBlocked()) {
//                Database.log(Registry.RegistryWithTimestamp(2004, email));
//                Main.loginFrame.panel.warning.setText("User is temporarily blocked");
//
//                return;
//            }

            Database.log(Registry.RegistryWithTimestamp(2003, email));
            Database.log(Registry.RegistryWithTimestamp(2002));

            Main.loginFrame.dispose();
            Main.passwordFrame = new PasswordFrame();

        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }
}
