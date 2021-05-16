package MenuFrame.Panel.SignupPanel.Labels;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;

public class FormularioLabel extends JLabel {

    public FormularioLabel(SignupPanel signupPanel) {
        String totalDeUsuarios = "Formulario de cadastro: ";

        this.setText(totalDeUsuarios);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }
}
