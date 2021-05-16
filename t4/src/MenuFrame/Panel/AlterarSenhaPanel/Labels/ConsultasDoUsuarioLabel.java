package MenuFrame.Panel.AlterarSenhaPanel.Labels;

import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;
import Auth.Validation;

import javax.swing.*;
import java.awt.*;

public class ConsultasDoUsuarioLabel extends JLabel {

    public ConsultasDoUsuarioLabel(AlterarSenhaPanel alterarSenhaPanel) {
        String totalDeAcessos = "Total de acessos do usuário: ";

        try {
            // totalDeAcessos += Validation.user.getInt("acessos") + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setText(totalDeAcessos);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
    }
}
