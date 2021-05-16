package MenuFrame.Panel.QueryPanel.Labels;

import Auth.CertificateHelper;
import Auth.Validation;
import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;

public class HeaderLabel extends JLabel {

    public HeaderLabel(QueryPanel queryPanel) {
        String header = "<html>";

        try{
            String email = Validation.user.Email;
            String groupName = Validation.user.Group;
            String name = CertificateHelper.getCertificateCN(Validation.user.Certificate);

            header = header + "Login: " + email + "<br>";
            header = header + "Group: " + groupName + "<br>";
            header = header + "Name: " + name + "<br>";
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
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
    }
}
