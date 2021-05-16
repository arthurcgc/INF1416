package MenuFrame.Panel.LogoutPanel;

import Auth.Validation;
import MenuFrame.Panel.LogoutPanel.Buttons.LogoutButton;
import MenuFrame.Panel.LogoutPanel.Buttons.BackButton;
import MenuFrame.Panel.LogoutPanel.Labels.HeaderLabel;
import MenuFrame.Panel.LogoutPanel.Labels.UserQueryLabel;
import MenuFrame.Panel.LogoutPanel.Labels.LogoutLabel;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class LogoutPanel extends JPanel {

    public LogoutPanel(){
        try {
            Database.log(Registry.RegistryWithTimestamp(9001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new HeaderLabel(this);
        new UserQueryLabel(this);
        new LogoutLabel(this);
        new LogoutButton(this);
        new BackButton(this);
    }
}
