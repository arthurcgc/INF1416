package AuthenticationFrames.KeyVerificationFrame;

import AuthenticationFrames.KeyVerificationFrame.Panel.Panel;

import javax.swing.*;

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

}
