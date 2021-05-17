package MenuFrame.Panel.SignupPanel.Buttons;

import Auth.CertificateHelper;
import Auth.User;
import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.SignupPanel.SignupPanel;
import Database.*;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.cert.X509Certificate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupButton extends JButton implements ActionListener {

    SignupPanel signupPanel;

    public SignupButton(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("Signup");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(6002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }



        String email = "";
        String issuer = "";
        String subject = "";
        X509Certificate certificate = null;
        try {
            certificate = CertificateHelper.getCertificate(signupPanel.certificatePathLabel.getText());
            email = CertificateHelper.getCertificateEmail(certificate);
            subject = CertificateHelper.getCertificateCN(certificate);

            String regex = ".*CN=([^,]*).*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(certificate.getIssuerX500Principal().toString());
            matcher.matches();
            issuer = matcher.group(1);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String message = "<html>";
        message += "Version: " + certificate.getVersion();
        message += "<br>Series: " + certificate.getSerialNumber().toString();
        message += "<br>Expire: " + certificate.getNotAfter().toString();
        message += "<br>Issuer: " + issuer;
        message += "<br>Subject: " + subject;
        message += "<br>Email: " + email;
        message += "<br>Signature Type: " + certificate.getSigAlgName();
        message += "</html>";


        int answer = JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION) {
            User newUser = null;
            try {
                newUser = User.NewUser(StringUtils.substringBefore(email, "@"),
                        (String)signupPanel.comboboxGroup.getSelectedItem(),
                        certificate,
                        new String(signupPanel.passwordTextField.getPassword())
                );
                signUp(newUser, new String(signupPanel.passwordTextField.getPassword()), new String(signupPanel.passwordConfirmTextField.getPassword()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }

            try {
                MenuFrame.getInstance().remove(this.signupPanel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            SignupPanel signupPanel = new SignupPanel();
            try {
                MenuFrame.getInstance().add(signupPanel);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            signupPanel.updateUI();
        }
    }

    public boolean validatePassword(String password, String confirmedPassword) {
        if(!password.equals(confirmedPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords must be the same");
            return false;
        }
        // passwords are the same

        if(password.length() < 4*2 || password.length() > 6*2) {
            JOptionPane.showMessageDialog(this, "Password must have ter 4 or 5 or 6 phonemes");
            return false;
        }

        // check if password has two identical consecutive phonemes
        for(int i = 0; i < password.length() - 4; i+=4){
            String first = password.substring(i, i+2);
            String second = password.substring(i+2, i+4);
            if ( first.equals(second)){
                return false;
            }
        }

        return true;
    }

    public void signUp(User user, String password, String confirmedPassword) throws Exception {
        if (!validatePassword(password, confirmedPassword)) {
            Database.log(Registry.RegistryWithTimestamp(6003, Validation.user.Email));
            return;
        }

        if (Database.getInstance().getEmailCount(user.Email) != 0) {
            JOptionPane.showMessageDialog(this, "Email already in use");
            return;
        }
        Database db = Database.getInstance();
        db.insertUser(user);
        db.insertGroup(user.Group, user.GetGroupID());
        Database.log(Registry.RegistryWithTimestamp(6005, Validation.user.Email));
        JOptionPane.showMessageDialog(this, "User Successfully created!", "Success", JOptionPane.OK_OPTION);
    }

}
