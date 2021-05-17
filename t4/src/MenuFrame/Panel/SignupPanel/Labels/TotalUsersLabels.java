package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class TotalUsersLabels extends JLabel {

    public TotalUsersLabels(SignupPanel signupPanel) {
        String totalUsers = "Total users: ";

        try {
            totalUsers += Database.getInstance().getUsersCount();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalUsers);
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
