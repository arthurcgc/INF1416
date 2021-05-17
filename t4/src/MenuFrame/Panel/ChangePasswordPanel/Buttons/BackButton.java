package MenuFrame.Panel.ChangePasswordPanel.Buttons;

import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton implements ActionListener {

    ChangePasswordPanel changePasswordPanel;

    public BackButton(ChangePasswordPanel changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;

        this.setText("Back");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(7006, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(changePasswordPanel);
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
