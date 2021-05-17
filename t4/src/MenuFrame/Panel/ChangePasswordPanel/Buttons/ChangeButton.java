package MenuFrame.Panel.ChangePasswordPanel.Buttons;

import Auth.CertificateHelper;
import Auth.User;
import MenuFrame.Panel.ChangePasswordPanel.ChangePasswordPanel;
import Database.Database;
import Database.*;
import Auth.Validation;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class ChangeButton extends JButton implements ActionListener {

    ChangePasswordPanel changePasswordPanel;

    public ChangeButton(ChangePasswordPanel changePasswordPanel) {
        this.changePasswordPanel = changePasswordPanel;

        this.setText("Confirm");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        changePasswordPanel.add(this, gridBagConstraints);
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

//        // check if password has two identical consecutive phonemes
        for(int i = 0; i < password.length() - 4; i+=4){
            String first = password.substring(i, i+2);
            String second = password.substring(i+2, i+4);
            if ( first.equals(second)){
                return false;
            }
        }

        return true;
    }

    public X509Certificate getCertificate() {
        try {
            Path path = Paths.get(changePasswordPanel.certificatePathLabel.getText());
            return CertificateHelper.getCertificate(path.toString());
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(8002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String password = new String(changePasswordPanel.passwordTextField.getPassword());
        String confirmedPassword = new String(changePasswordPanel.confirmPasswordField.getPassword());

        boolean shouldChangePass = false;
        X509Certificate newCert = null;
        if(password.length() != 0 || confirmedPassword.length() != 0)
            shouldChangePass = validatePassword(password, confirmedPassword);

        if(changePasswordPanel.certificatePathLabel.getText().equals("") == false) {
            try {
                newCert = CertificateHelper.getCertificate(changePasswordPanel.certificatePathLabel.getText());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        User newUser = Validation.user;
        if (shouldChangePass) {
            try {
                newUser.Hash = newUser.generatePasswordHash(password);
            } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
                noSuchAlgorithmException.printStackTrace();
            }
        }

        if (newCert != null ){
            newUser.Certificate = newCert;
        }

        if ( shouldChangePass == true || newCert != null){
            try {
                Database db = Database.getInstance();
                db.deleteUser(Validation.user);
                db.insertUser(newUser);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }
}
