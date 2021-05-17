package MenuFrame.Panel.SignupPanel.Labels;

import Auth.CertificateHelper;
import Auth.Validation;
import MenuFrame.Panel.SignupPanel.SignupPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class HeaderLabel extends JLabel {

    public HeaderLabel(SignupPanel signupPanel) {
        String header = "<html>";

        try{
            String email = Validation.user.Email;
            String groupName = Database.getInstance().getGroupName(Validation.user.GroupID);
            String name = CertificateHelper.getCertificateCN(Validation.user.Certificate);

            header = header + "Login: " + email + "<br>";
            header = header + "Grupo: " + groupName + "<br>";
            header = header + "Nome: " + name + "<br>";
        } catch (Exception e){
            e.printStackTrace();
        }

        header = header + "</html>";
        this.setText(header);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }
}
