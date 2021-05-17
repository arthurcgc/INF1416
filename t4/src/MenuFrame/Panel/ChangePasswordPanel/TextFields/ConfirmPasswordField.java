package MenuFrame.Panel.ChangePasswordPanel.TextFields;

import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;

import javax.swing.*;
import java.awt.*;

public class ConfirmPasswordField extends JPasswordField {

    public ConfirmPasswordField(ChangePasswordPanel changePasswordPanel) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
        changePasswordPanel.confirmPasswordField = this;
    }
}
