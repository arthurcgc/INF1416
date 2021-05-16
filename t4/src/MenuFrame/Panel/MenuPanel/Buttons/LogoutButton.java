package MenuFrame.Panel.MenuPanel.Buttons;

import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.LogoutPanel.LogoutPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
// import MenuFrame.Panel.SairPanel.SairPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public LogoutButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("4 - Logout");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5005, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
         LogoutPanel logoutPanel = new LogoutPanel();
        try {
            MenuFrame.getInstance().add(logoutPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        logoutPanel.updateUI();
    }
}
