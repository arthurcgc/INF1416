package AuthenticationFrames.PasswordFrame;

import Auth.Blocked;
import Auth.Validation;
import AuthenticationFrames.LoginFrame.LoginFrame;
import AuthenticationFrames.PasswordFrame.Panel.Panel;
import Database.*;
import Main.Main;

import javax.swing.*;
import java.util.ArrayList;

public class PasswordFrame extends JFrame {

    public Panel panel;

    private final String NAME = "AuthenticationFrames/PasswordFrame";
    private final int WIDTH = 800;
    private final int HEIGHT = 600;

    public PasswordFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);

        this.setTitle(NAME);
        this.setSize(WIDTH, HEIGHT);
        this.setLocation(this.getLocation().x - WIDTH/2, this.getLocation().y - HEIGHT/2);
        this.addWindowListener(new OnChange());

        panel = new Panel();
        this.setContentPane(panel);

        this.setVisible(true);
    }

    public void warning(String problemMessage) {
        panel.passwordPhonemes = new ArrayList<>();

        JOptionPane.showMessageDialog(this, problemMessage);
    }

    public void warningIncrease(String error) throws Exception {
        int fails = 0;

        try {
            fails = Blocked.getFails() + 1;
            Blocked.setFails(fails);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String errorString = error;
        switch (fails){
            case 1:
                errorString = String.format("%s: You have %d attempts left", error, 3-fails);
                Database.log(Registry.RegistryWithTimestamp(3004, Validation.user.getString("email")));
            case 2:
                errorString = String.format("%s: You have %d attempts left", error, 3-fails);
                Database.log(Registry.RegistryWithTimestamp(3005, Validation.user.getString("email")));
            case 3:
                errorString = String.format("%s: You have %d attempts left", error, 3-fails);
                Database.log(Registry.RegistryWithTimestamp(3006, Validation.user.getString("email")));
            default:
                errorString = String.format("%s: User blocked for 2 minutes");
                Database.log(Registry.RegistryWithTimestamp(3007, Validation.user.getString("email")));

                this.dispose();
                Main.loginFrame = new LoginFrame();
        }

        warning(errorString);
    }

}
