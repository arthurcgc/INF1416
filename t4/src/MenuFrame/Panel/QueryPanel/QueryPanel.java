package MenuFrame.Panel.QueryPanel;

import Auth.Validation;
import MenuFrame.Panel.QueryPanel.Buttons.QueryFolderPathButton;
import MenuFrame.Panel.QueryPanel.Buttons.ListButton;
import MenuFrame.Panel.QueryPanel.Buttons.BackButton;
import MenuFrame.Panel.QueryPanel.Labels.HeaderLabel;
import MenuFrame.Panel.QueryPanel.Labels.FolderPathLabel;
import MenuFrame.Panel.QueryPanel.Labels.UserPanelLabel;
import MenuFrame.Panel.QueryPanel.Labels.WarningLabel;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class QueryPanel extends JPanel {

    public FolderPathLabel folderPathLabel;
    public WarningLabel warningLabel;

    public QueryPanel(){
        try {
            Database.log(Registry.RegistryWithTimestamp(8001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new HeaderLabel(this);
        new QueryFolderPathButton(this);
        new FolderPathLabel(this);
        new UserPanelLabel(this);
        new WarningLabel(this);
        new ListButton(this);
        new BackButton(this);
    }
}
