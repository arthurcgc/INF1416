package MenuFrame.Panel.MenuPanel.Buttons;

import MenuFrame.MenuFrame;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;
import Auth.Validation;
import MenuFrame.Panel.QueryPanel.QueryPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QueryButton extends JButton implements ActionListener {

    MenuPanel menuPanel;

    public QueryButton(MenuPanel menuPanel) {
        this.menuPanel = menuPanel;

        this.setText("3 - Access safe-folder");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.weighty = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        menuPanel.add(this, gridBagConstraints);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(5004, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        QueryPanel queryPanel = new QueryPanel();
        try {
            MenuFrame.getInstance().add(queryPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        queryPanel.updateUI();
    }
}
