package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;
import Auth.Validation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public ChangePasswordButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("2 - Change password and user certificate");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5003, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        ChangePasswordPanel changePasswordPanel = new ChangePasswordPanel();
        try {
            MenuFrame.getInstance().add(changePasswordPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        changePasswordPanel.updateUI();
    }
}
