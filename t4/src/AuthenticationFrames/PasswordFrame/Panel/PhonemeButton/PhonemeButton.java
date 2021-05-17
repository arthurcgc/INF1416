package AuthenticationFrames.PasswordFrame.Panel.PhonemeButton;

import javax.swing.*;

public class PhonemeButton extends JButton {

    public String p1;
    public String p2;
    public String p3;

    public PhonemeButton(String phoneme1, String phoneme2, String phoneme3) {
        this.p1 = phoneme1;
        this.p2 = phoneme2;
        this.p3 = phoneme3;

        this.setText(phoneme1 + " - " + phoneme2 + " - " + phoneme3);
        this.addActionListener(new OnClick(this));
    }
}
