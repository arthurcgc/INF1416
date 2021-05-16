package AuthenticationFrames.PasswordFrame;

import Database.*;
import Auth.Validation;

import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(3001, Validation.user.getString("email")));
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(3002, Validation.user.getString("email")));
            Database.log(Registry.RegistryWithTimestamp(1002, Validation.user.getString("email")));
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowIconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeiconified(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowActivated(java.awt.event.WindowEvent e) {

    }

    @Override
    public void windowDeactivated(java.awt.event.WindowEvent e) {

    }
}
