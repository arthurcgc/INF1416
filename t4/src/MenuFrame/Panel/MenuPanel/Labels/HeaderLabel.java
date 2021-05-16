package MenuFrame.Panel.MenuPanel.Labels;

import Auth.CertificateHelper;
import Auth.Validation;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

public class HeaderLabel extends JLabel {

    public HeaderLabel(MenuPanel menuPanel) {
        String header = "<html>";

        try{
            Database db = Database.getInstance();
            String email = Validation.user.Email;
            String groupName = Validation.user.Group;
            String nome = CertificateHelper.getCertificateCN(Validation.user.Certificate);

            header = header + "Login: " + email + "<br>";
            header = header + "Group: " + groupName + "<br>";
            header = header + "Name: " + nome + "<br>";
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
        menuPanel.add(this, gridBagConstraints);
    }
}
