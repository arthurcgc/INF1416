package MenuFrame.Panel.SignupPanel.Buttons;

import Auth.User;
import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.SignupPanel.SignupPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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

         X509Certificate certificate = null;

        String email = "";
        String issuer = "";
        String subject = "";

        try {
//            email = Validation.getCertificateEMAILADDRESS(certificate);
//            subject = CertificateUtility.getCertificateCamp(certificate, "CN");

//            String regex = ".*CN=([^,]*).*";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(certificate.getIssuerX500Principal().toString());
//            matcher.matches();
//            issuer = matcher.group(1);
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

        User newUser = null;
        int answer = JOptionPane.showConfirmDialog(this, message, "Confirm", JOptionPane.YES_NO_OPTION);
        if(answer == JOptionPane.YES_OPTION) {
            signUp(newUser);

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

    public void signUp(User user) {
//        try {
//            if(!isPasswordValid()) {
//                Database.log(6003, Validation1.user.getString("email"));
//                return;
//            }
//
//            if(DAO.getInstance().getEmailCount(email) != 0) {
//                JOptionPane.showMessageDialog(this, "Esse email está em uso");
//                return;
//            }
//
//            byte[] salt = getSalt();
//            DAO.getInstance().insertUsuario(email, getDigest(salt), salt, signupPanel.caminhoDoCertificadoLabel.getText(), getGrupo());
//
//            Database.log(6005, Validation1.user.getString("email"));
//        } catch (Exception e) {
//            try {
//                Database.log(6006, Validation1.user.getString("email"));
//                e.printStackTrace();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
    }


}
