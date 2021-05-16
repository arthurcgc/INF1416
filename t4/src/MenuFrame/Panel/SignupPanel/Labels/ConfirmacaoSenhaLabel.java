package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmacaoSenhaLabel extends JLabel {

    SignupPanel signupPanel;

    public ConfirmacaoSenhaLabel(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("<html>Confirmação senha pessoal:</html>");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }
}
