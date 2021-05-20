package MenuFrame.Panel.QueryPanel.Table;

import Auth.*;
import MenuFrame.Panel.QueryPanel.QueryPanel;
import Database.*;
import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.io.File;

public class Table extends JTable {

    QueryPanel queryPanel;
    public int selectedRow = 0;
    public int doubleClick = 0;
    public String indexString;

    public Table(QueryPanel queryPanel, String[][] data, String[] column, String indexString) {
        super(data, column);

        this.indexString = indexString;
        this.queryPanel = queryPanel;
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);

        queryPanel.add(this, gridBagConstraints);
        queryPanel.updateUI();
    }

    public boolean isCellEditable(int row, int column) {
        if(getSelectedRow() == selectedRow && getSelectedRow() != -1 && doubleClick == 0) {
            tryDecipherRow();
            selectedRow = getSelectedRow();
            doubleClick = 1;
            return false;
        }

        selectedRow = getSelectedRow();
        doubleClick = 0;

        return false;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(getSelectedRow() != -1)
            queryPanel.warningLabel.setVisible(!isAccessible());

        doubleClick = 0;

        super.valueChanged(e);
    }

    public void tryDecipherRow() {
        try {
            decipherRow();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, String.format("Can't decipher file: %s", e.getMessage()));
            e.printStackTrace();
        }
    }

    public void decipherRow() throws Exception {
        String currentName = (String) this.getValueAt(getSelectedRow(), 0);
        String secretName = (String) this.getValueAt(getSelectedRow(), 1);

        String fullPath = queryPanel.folderPathLabel.getText() + File.separator + currentName;
        String folderPath = StringUtils.substringBeforeLast(fullPath, "/");
        String fileName = StringUtils.substringAfterLast(fullPath, "/");

        Database.log(Registry.RegistryWithTimestamp(8010, Validation.user.Email));

        if(Auth.checkPermission(Validation.user, this.indexString, fileName, folderPath)) {
            Database.log(Registry.RegistryWithTimestamp(8011, Validation.user.Email));
            JOptionPane.showMessageDialog(this, "Access granted\nFile successfully written to disk");
            return;
        }
        JOptionPane.showMessageDialog(this, "Access denied");
        Database.log(Registry.RegistryWithTimestamp(8012, Validation.user.Email));
    }

    public boolean isAccessible() {
        String fileOwner = (String) this.getValueAt(getSelectedRow(), 2);
        String fileGroup = (String) this.getValueAt(getSelectedRow(), 3);

        try {
            if(isFromGroup(fileGroup) || isFromOwner(fileOwner))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean isFromGroup(String fileGroup) throws Exception {
        String userGroup = Validation.user.Group;

        if(userGroup.equalsIgnoreCase(fileGroup))
            return true;

        return false;
    }

    public boolean isFromOwner(String fileOwner) throws Exception {
        String ownerEmail = Validation.user.Email;

        if(ownerEmail.equalsIgnoreCase(fileOwner))
            return true;

        return false;
    }
}
