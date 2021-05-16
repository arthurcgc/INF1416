package MenuFrame.Panel.SignupPanel.Buttons;

import MenuFrame.Panel.SignupPanel.SignupPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LimparCertificadoButton extends JButton implements ActionListener {

    SignupPanel signupPanel;

    public LimparCertificadoButton(SignupPanel signupPanel) {
        this.signupPanel = signupPanel;

        this.setText("Limpar campo");
        this.addActionListener(this);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weightx = 0;
        gridBagConstraints.anchor = GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        signupPanel.add(this, gridBagConstraints);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        signupPanel.caminhoDoCertificadoLabel.setText("");
    }
}
