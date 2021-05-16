package MenuFrame.Panel.QueryPanel.Labels;

import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;

public class FolderPathLabel extends JLabel {

    QueryPanel queryPanel;

    public FolderPathLabel(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;

        this.setText("");
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
        queryPanel.folderPathLabel = this;
    }
}
