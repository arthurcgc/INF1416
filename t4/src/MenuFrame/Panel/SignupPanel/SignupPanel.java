package MenuFrame.Panel.SignupPanel;

import Auth.Validation;
import MenuFrame.Panel.SignupPanel.Buttons.SignupButton;
import MenuFrame.Panel.SignupPanel.Buttons.CertificatePathButton;
import MenuFrame.Panel.SignupPanel.Buttons.ClearCertificateButton;
import MenuFrame.Panel.SignupPanel.Buttons.BackButton;
import MenuFrame.Panel.SignupPanel.ComboBox.ComboboxGroup;
import MenuFrame.Panel.SignupPanel.Labels.*;
import MenuFrame.Panel.SignupPanel.TextFields.ConfirmacaoSenhaTextField;
import MenuFrame.Panel.SignupPanel.TextFields.SenhaTextField;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {

    public CertificatePathLabel certificatePathLabel;
    public SenhaTextField senhaTextField;
    public ConfirmacaoSenhaTextField confirmacaoSenhaTextField;
    public ComboboxGroup comboboxGroup;

    public SignupPanel(){
        try {
            Database.log(Registry.RegistryWithTimestamp(6001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new HeaderLabel(this);
        new TotalUsersLabels(this);
        new FormLabel(this);
        new CertificatePathButton(this);
        new CertificatePathLabel(this);
        new ClearCertificateButton(this);
        new PasswordLabel(this);
        new SenhaTextField(this);
        new ConfirmPasswordLabel(this);
        new ConfirmacaoSenhaTextField(this);
        new SignupButton(this);
        new ComboboxGroup(this);
        new BackButton(this);
    }
}
