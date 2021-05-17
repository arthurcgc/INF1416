package MenuFrame.Panel.ChangePasswordPanel.Buttons;

import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearCertificateButton extends JButton implements ActionListener {

    ChangePasswordPanel changePasswordPanel;

    public ClearCertificateButton(ChangePasswordPanel changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;

        this.setText("Clear");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        changePasswordPanel.certificatePathLabel.setText("");
    }
}
