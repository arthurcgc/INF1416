package AuthenticationFrames.LoginFrame;

import Database.*;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class OnChange implements WindowListener {
    @Override
    public void windowOpened(java.awt.event.WindowEvent e) {
        try {
            Registry r = Registry.RegistryWithTimestamp(2001);
            Database.log(r);
        } catch(Exception e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosing(java.awt.event.WindowEvent e) {
        Registry r1 = Registry.RegistryWithTimestamp();
        r1.Code = 2002;
        Registry r2 = Registry.RegistryWithTimestamp();
        r2.Code = 1002;
        try {
            Database.log(r1);
            Database.log(r2);
        } catch (Exception exception) {
            exception.printStackTrace();
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
