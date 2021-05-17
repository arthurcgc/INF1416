package MenuFrame.Panel.SignupPanel.Buttons;

import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.SignupPanel.SignupPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton implements ActionListener {

    SignupPanel signupPanel;

    public BackButton(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("Back");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(6007, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(signupPanel);
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
