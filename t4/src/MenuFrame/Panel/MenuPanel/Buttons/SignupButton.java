package MenuFrame.Panel.MenuPanel.Buttons;

import Auth.Validation;
import MenuFrame.MenuFrame;
// import MenuFrame.Panel.CadastrarPanel.CadastrarPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignupButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public SignupButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        try {
            String group = Validation.user.Group;
            if (!group.equals("admin"))
                return;
        } catch (Exception e) {
            e.printStackTrace();

            return;
        }

        this.setText("1 - Create New User");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        // CadastrarPanel consultarPanel = new CadastrarPanel();
//        try {
//            MenuFrame.getInstance().add(consultarPanel);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        consultarPanel.updateUI();
    }
}
