package MenuFrame.Panel.ChangePasswordPanel.Labels;

import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;

import javax.swing.*;
import java.awt.*;

public class CertificatePathLabel extends JLabel {

    ChangePasswordPanel changePasswordPanel;

    public CertificatePathLabel(ChangePasswordPanel changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;

        this.setText("");
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
        changePasswordPanel.certificatePathLabel = this;
    }
}
