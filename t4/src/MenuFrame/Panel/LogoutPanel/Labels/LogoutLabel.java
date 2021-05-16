package MenuFrame.Panel.LogoutPanel.Labels;

import MenuFrame.Panel.LogoutPanel.LogoutPanel;

import javax.swing.*;
import java.awt.*;

public class LogoutLabel extends JLabel {

    public LogoutLabel(LogoutPanel logoutPanel) {
        String header = "<html>Logout options: </html>";

        this.setText(header);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        logoutPanel.add(this, gridBagConstraints);
    }
}
