package MenuFrame.Panel.SignupPanel.ComboBox;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ComboboxGroup extends JComboBox<String> implements ActionListener {

    SignupPanel signupPanel;

    public ComboboxGroup(SignupPanel signupPanel) {
        this.addItem("Usuario");
        this.addItem("Administrador");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
        signupPanel.comboboxGroup = this;
    }
}
