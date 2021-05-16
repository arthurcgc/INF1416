package AuthenticationFrames.PasswordFrame;

import AuthenticationFrames.PasswordFrame.Panel.Panel;

import javax.swing.*;

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

}
