package AuthenticationFrames.KeyVerificationFrame;

import AuthenticationFrames.KeyVerificationFrame.Panel.Panel;
import AuthenticationFrames.LoginFrame.LoginFrame;
import Database.*;
import Auth.Blocked;
import Auth.Validation;
import Main.Main;

import javax.swing.*;
import java.util.ArrayList;

public class KeyVerificationFrame extends JFrame {

    public Panel panel;

    private final String NAME = "AuthenticationFrames/KeyVerificationFrame";
    private final int WIDTH = 500;
    private final int HEIGHT = 100;

    public KeyVerificationFrame() throws Exception {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        this.setTitle(NAME);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
        this.addWindowListener(new OnChange());

        panel = new Panel();
        this.setContentPane(panel);

        this.setVisible(true);
    }

    public void warningIncrease(String errorString) throws Exception {
        int fails = 0;

        try {
            fails = Blocked.getFails() + 1;
            Blocked.setFails(fails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(fails < 3)
            errorString = errorString + "\nVocê tem " + (3 - fails) + " tentativas";
        else {
            errorString = errorString + "\nEmail foi bloqueado por 2 minutos";
            Database.log(Registry.RegistryWithTimestamp(4007, Validation.user.Email));

            this.dispose();
            Main.loginFrame = new LoginFrame();
        }

        JOptionPane.showMessageDialog(this, errorString);
    }

}
