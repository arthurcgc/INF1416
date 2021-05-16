package MenuFrame.Panel.QueryPanel.Buttons;

import Auth.*;
import MenuFrame.Panel.QueryPanel.QueryPanel;
import Database.*;
import MenuFrame.Panel.QueryPanel.Table.Table;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListButton extends JButton implements ActionListener {

    QueryPanel queryPanel;

    public ListButton(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;

        this.setText("List");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(8003, Validation.user.Email));

            // decrypt index to discover filer list with real names and owner specs
            byte[] indexBytes = Auth.decryptFile(Validation.user, queryPanel.folderPathLabel.getText(), "index");
            String indexString = new String(indexBytes, "UTF8");
            ArrayList<SafeFile> filesList = Parser.ParseIndex(indexString);

            String[][] fileTable = new String[5][4];
            for (int i = 0; i < filesList.size(); i++){
                SafeFile sf = filesList.get(i);
                fileTable[i][0] = sf.Code;
                fileTable[i][1] = sf.Name;
                fileTable[i][2] = sf.OwnerEmail;
                fileTable[i][3] = sf.Group;
            }
            String[] fileColumns = new String[]{"Codename", "Real name", "Owner", "Group"};

            Table table = new Table(queryPanel, fileTable, fileColumns, indexString);

            Database.log(Registry.RegistryWithTimestamp(8009, Validation.user.Email));
        } catch(Exception e1) {
            try {
                Database.log(Registry.RegistryWithTimestamp(8004, Validation.user.Email));
                JOptionPane.showMessageDialog(this, "Unable to display directory");
                e1.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
