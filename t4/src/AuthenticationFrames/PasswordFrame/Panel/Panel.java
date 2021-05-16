package AuthenticationFrames.PasswordFrame.Panel;

import AuthenticationFrames.PasswordFrame.Panel.LoginButton.LoginButton;
import AuthenticationFrames.PasswordFrame.Panel.NumberButton.PhonemeButton;

import javax.swing.*;
import java.awt.*;
import java.security.SecureRandom;
import java.util.ArrayList;

public class Panel extends JPanel {

    public ArrayList<String[]> passwordPhonemes = new ArrayList<>();

    public Panel() {
        this.setLayout(new GridBagLayout());

        createButtons(generateRandomPhonemes());
    }

    public ArrayList<String> generateRandomPhonemes() {
        ArrayList<String> phonemes = new ArrayList<>();
        phonemes.add(0, "BA");phonemes.add(1, "BE");phonemes.add(2, "BO");
        phonemes.add(3, "CA");phonemes.add(4, "CE");phonemes.add(5, "CO");
        phonemes.add(6, "DA");phonemes.add(7, "DE");phonemes.add(8, "DO");
        phonemes.add(9, "FA");phonemes.add(10, "FE");phonemes.add(11, "FO");
        phonemes.add(12, "GA");phonemes.add(13, "GE");phonemes.add(14, "GO");
        phonemes.add(15, "HA");phonemes.add(16, "HE");phonemes.add(17, "HO");

        SecureRandom secureRandom = new SecureRandom();
        ArrayList<String> randomPhonemes = new ArrayList<>();
        for(int i = 0; i < 18; i++) {
            int index = secureRandom.nextInt(phonemes.size());
            randomPhonemes.add(randomPhonemes.size(), phonemes.remove(index));
        }

        return randomPhonemes;
    }

    public void createButtons(ArrayList<String> phonemes) {
        this.removeAll();

        GridBagConstraints gridBagConstraints;

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 10, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(0), phonemes.get(1), phonemes.get(2)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(3), phonemes.get(4), phonemes.get(5)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(6), phonemes.get(7), phonemes.get(8)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(9), phonemes.get(10), phonemes.get(11)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(12), phonemes.get(13), phonemes.get(14)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(45, 0, 45, 10);
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        this.add(new PhonemeButton(phonemes.get(15), phonemes.get(16), phonemes.get(17)), gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);

        this.add(new LoginButton(), gridBagConstraints);
    }

    public void addPasswordPhonemes(String p1, String p2, String p3) {
        passwordPhonemes.add(new String[]{p1, p2, p3});
        createButtons(generateRandomPhonemes());
        this.updateUI();
    }
}
