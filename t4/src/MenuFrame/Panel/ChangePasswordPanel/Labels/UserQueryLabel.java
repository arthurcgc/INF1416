package MenuFrame.Panel.ChangePasswordPanel.Labels;

import Auth.Validation;
import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;

import javax.swing.*;
import java.awt.*;

public class UserQueryLabel extends JLabel {

    public UserQueryLabel(ChangePasswordPanel changePasswordPanel) {
        String accessCounter = "Access counter: ";

        try {
            accessCounter += Validation.user.AccessCounter+1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(accessCounter);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
    }
}
