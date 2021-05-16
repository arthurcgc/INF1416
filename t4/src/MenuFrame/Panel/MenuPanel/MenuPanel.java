package MenuFrame.Panel.MenuPanel;

import MenuFrame.Panel.MenuPanel.Buttons.ChangePasswordButton;
import MenuFrame.Panel.MenuPanel.Buttons.QueryButton;
import MenuFrame.Panel.MenuPanel.Buttons.LogoutButton;
//import MenuFrame.Panel.MenuPanel.Labels.CabecalhoLabel;
import MenuFrame.Panel.MenuPanel.Buttons.SignupButton;
import MenuFrame.Panel.MenuPanel.Labels.UserControlPanelLabel;
import Database.*;
import Auth.Validation;
import MenuFrame.Panel.MenuPanel.Labels.HeaderLabel;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    public MenuPanel(){
        try {
            Database.log(Registry.RegistryWithTimestamp(5001, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new HeaderLabel(this);
        new UserControlPanelLabel(this);
        new SignupButton(this);
        new ChangePasswordButton(this);
        new QueryButton(this);
        new LogoutButton(this);
    }
}
