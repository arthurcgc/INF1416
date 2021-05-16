package MenuFrame.Panel.MenuPanel.Labels;

import Auth.Validation;
import MenuFrame.Panel.MenuPanel.MenuPanel;

import javax.swing.*;
import java.awt.*;

public class UserControlPanelLabel extends JLabel {

    public UserControlPanelLabel(MenuPanel menuPanel) {
        String accessCountString = "Access count: ";

        try {
            accessCountString += Validation.user.AccessCounter + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(accessCountString);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }
}
