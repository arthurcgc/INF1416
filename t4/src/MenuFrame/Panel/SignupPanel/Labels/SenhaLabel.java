package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;

public class SenhaLabel extends JLabel {

    SignupPanel signupPanel;

    public SenhaLabel(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("<html>Senha pessoal:</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }
}
