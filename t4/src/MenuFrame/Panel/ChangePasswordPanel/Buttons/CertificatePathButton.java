package MenuFrame.Panel.ChangePasswordPanel.Buttons;

import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CertificatePathButton extends JButton implements ActionListener {

    ChangePasswordPanel changePasswordPanel;

    public CertificatePathButton(ChangePasswordPanel changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;

        this.setText("Certificate Path");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            changePasswordPanel.certificatePathLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
