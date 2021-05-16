package MenuFrame;

import Database.*;
import Auth.Validation;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
    @Override
    public void windowOpened(WindowEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5002, Validation.user.Email));
            Database.log(Registry.RegistryWithTimestamp(1002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
