package MenuFrame.Panel.SignupPanel.TextFields;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;

public class PasswordConfirmTextField extends JPasswordField {

    public PasswordConfirmTextField(SignupPanel signupPanel) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
        signupPanel.passwordConfirmTextField = this;
    }
}
