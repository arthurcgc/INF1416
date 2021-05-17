package AuthenticationFrames.PasswordFrame.Panel.PhonemeButton;

import Main.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClick implements ActionListener {

    public PhonemeButton phonemeButton;

    public OnClick(PhonemeButton phonemeButton) {
        this.phonemeButton = phonemeButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Main.passwordFrame.panel.addPasswordPhonemes(phonemeButton.p1, phonemeButton.p2, phonemeButton.p3);
    }
}
