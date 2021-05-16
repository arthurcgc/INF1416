package MenuFrame.Panel.QueryPanel.Buttons;

import Auth.Validation;
import MenuFrame.MenuFrame;
import MenuFrame.Panel.QueryPanel.QueryPanel;
import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BackButton extends JButton implements ActionListener {

    QueryPanel queryPanel;

    public BackButton(QueryPanel queryPanel) {
        this.queryPanel = queryPanel;

        this.setText("Back to main menu");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        queryPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Database.log(Registry.RegistryWithTimestamp(8002, Validation.user.Email));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        try {
            MenuFrame.getInstance().remove(queryPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        MenuPanel menuPanel = new MenuPanel();
        try {
            MenuFrame.getInstance().add(menuPanel);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        menuPanel.updateUI();
    }
}
