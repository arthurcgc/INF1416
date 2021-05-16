package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;
import Database.DAO;

import javax.swing.*;
import java.awt.*;

public class TotalUsuariosLabel extends JLabel {

    public TotalUsuariosLabel(SignupPanel signupPanel) {
        String totalDeUsuarios = "Total de usuários no sistema: ";

        try {
            totalDeUsuarios += DAO.getInstance().getUsersCount().getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalDeUsuarios);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }
}
