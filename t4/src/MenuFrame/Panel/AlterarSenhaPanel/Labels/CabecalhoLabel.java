package MenuFrame.Panel.AlterarSenhaPanel.Labels;

import Auth.Validation;
import MenuFrame.Panel.AlterarSenhaPanel.AlterarSenhaPanel;
import Database.*;

import javax.swing.*;
import java.awt.*;

public class CabecalhoLabel extends JLabel {

    public CabecalhoLabel(AlterarSenhaPanel alterarSenhaPanel) {
        String cabecalho = "<html>";

        try{
            String email = Validation.user.Email;
            // String grupo_nome = Database.getInstance().getGrupoNome(Validation1.user.getInt("grupo"));
//            String nome = CertificateUtility.getCertificateCN(Validation1.user.getBytes("certificate"));
//
//            cabecalho = cabecalho + "Login: " + email + "<br>";
//            cabecalho = cabecalho + "Grupo: " + grupo_nome + "<br>";
//            cabecalho = cabecalho + "Nome: " + nome + "<br>";
        } catch (Exception e){
            e.printStackTrace();
        }

        cabecalho = cabecalho + "</html>";
        this.setText(cabecalho);
        this.setVerticalAlignment(JLabel.NORTH);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(10, 10, 0 , 10);
        alterarSenhaPanel.add(this, gridBagConstraints);
    }
}
