package MenuFrame.Panel.QueryPanel.Labels;

import Auth.Validation;
import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;

public class UserPanelLabel extends JLabel {

    public UserPanelLabel(QueryPanel queryPanel) {
        String totalAccess = "Total access count: ";

        try {
            totalAccess += Validation.user.AccessCounter + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalAccess);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
    }
}
