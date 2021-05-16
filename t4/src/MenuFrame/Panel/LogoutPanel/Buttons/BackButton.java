package MenuFrame.Panel.LogoutPanel.Buttons;

import Auth.Validation;
import MenuFrame.Panel.LogoutPanel.LogoutPanel;
import MenuFrame.MenuFrame;
import Database.*;
import MenuFrame.Panel.MenuPanel.MenuPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton implements ActionListener {

    LogoutPanel logoutPanel;

    public BackButton(LogoutPanel logoutPanel) {
        this.logoutPanel = logoutPanel;

        this.setText("Back to main menu");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        logoutPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(9004, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(logoutPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        MenuPanel menuPanel = new MenuPanel();
        try {
            MenuFrame.getInstance().add(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        menuPanel.updateUI();
    }
}
