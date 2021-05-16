package MenuFrame.Panel.QueryPanel.Labels;

import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;

public class WarningLabel extends JLabel {

    public WarningLabel(QueryPanel queryPanel) {
        this.setText("<html><font color='red'>Access denied</font></html>");
        this.setVerticalAlignment(JLabel.NORTH);
        this.setVisible(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
        queryPanel.warningLabel = this;
    }
}
