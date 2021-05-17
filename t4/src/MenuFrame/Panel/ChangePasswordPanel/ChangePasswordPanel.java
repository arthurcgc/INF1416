package MenuFrame.Panel.ChangePasswordPanel;

import Auth.Validation;
import MenuFrame.Panel.ChangePasswordPanel.Buttons.ChangeButton;
import MenuFrame.Panel.ChangePasswordPanel.Buttons.CertificatePathButton;
import MenuFrame.Panel.ChangePasswordPanel.Buttons.ClearCertificateButton;
import MenuFrame.Panel.ChangePasswordPanel.Buttons.BackButton;
import MenuFrame.Panel.ChangePasswordPanel.Labels.*;
import MenuFrame.Panel.ChangePasswordPanel.TextFields.ConfirmPasswordField;
import MenuFrame.Panel.ChangePasswordPanel.TextFields.PasswordTextField;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordPanel extends JPanel {

    public CertificatePathLabel certificatePathLabel;
    public PasswordTextField passwordTextField;
    public ConfirmPasswordField confirmPasswordField;

    public ChangePasswordPanel(){
        try {
            Database.log(Registry.RegistryWithTimestamp(7001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new HeaderLabel(this);
        new UserQueryLabel(this);
        new CertificatePathButton(this);
        new CertificatePathLabel(this);
        new ClearCertificateButton(this);
        new PasswordLabel(this);
        new PasswordTextField(this);
        new ConfirmPasswordLabel(this);
        new ConfirmPasswordField(this);
        new ChangeButton(this);
        new BackButton(this);
    }
}
