package MenuFrame.Panel.SignupPanel.Buttons;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CertificatePathButton extends JButton implements ActionListener {

    SignupPanel signupPanel;

    public CertificatePathButton(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("Certificate Path");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            signupPanel.certificatePathLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
