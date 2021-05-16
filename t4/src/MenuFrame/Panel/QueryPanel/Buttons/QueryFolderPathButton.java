package MenuFrame.Panel.QueryPanel.Buttons;

import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryFolderPathButton extends JButton implements ActionListener {

    QueryPanel queryPanel;

    public QueryFolderPathButton(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;

        this.setText("Folder path");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            queryPanel.folderPathLabel.setText(fileChooser.getSelectedFile().getPath());
    }
}
