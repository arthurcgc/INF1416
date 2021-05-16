package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;

public class CaminhoDoCertificadoLabel extends JLabel {

    SignupPanel signupPanel;

    public CaminhoDoCertificadoLabel(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("");
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
        signupPanel.caminhoDoCertificadoLabel = this;
    }
}
