package MenuFrame.Panel.LogoutPanel.Buttons;

import Auth.Validation;
import MenuFrame.Panel.LogoutPanel.LogoutPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButton extends JButton implements ActionListener {

    LogoutPanel logoutPanel;

    public LogoutButton(LogoutPanel logoutPanel) {
        this.logoutPanel = logoutPanel;

        this.setText("Logout");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        logoutPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(9003, Validation.user.Email));
            Database.log(Registry.RegistryWithTimestamp(1002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        System.exit(0);
    }
}
