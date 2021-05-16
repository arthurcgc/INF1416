package MenuFrame.Panel.SignupPanel;

import MenuFrame.Panel.SignupPanel.Buttons.SignupButton;
import MenuFrame.Panel.SignupPanel.Buttons.CaminhoDoCertificadoButton;
import MenuFrame.Panel.SignupPanel.Buttons.LimparCertificadoButton;
import MenuFrame.Panel.SignupPanel.Buttons.VoltarButton;
import MenuFrame.Panel.SignupPanel.ComboBox.GrupoComboBox;
import MenuFrame.Panel.SignupPanel.Labels.*;
import MenuFrame.Panel.SignupPanel.TextFields.ConfirmacaoSenhaTextField;
import MenuFrame.Panel.SignupPanel.TextFields.SenhaTextField;
import Database.Database;
import Security.Validation1;

import javax.swing.*;
import java.awt.*;

public class SignupPanel extends JPanel {

    public CaminhoDoCertificadoLabel caminhoDoCertificadoLabel;
    public SenhaTextField senhaTextField;
    public ConfirmacaoSenhaTextField confirmacaoSenhaTextField;
    public GrupoComboBox grupoComboBox;

    public SignupPanel(){
        try {
            Database.log(6001, Validation1.user.getString("email"));
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        this.setLayout(new GridBagLayout());

        new CabecalhoLabel(this);
        new TotalUsuariosLabel(this);
        new FormularioLabel(this);
        new CaminhoDoCertificadoButton(this);
        new CaminhoDoCertificadoLabel(this);
        new LimparCertificadoButton(this);
        new SenhaLabel(this);
        new SenhaTextField(this);
        new ConfirmacaoSenhaLabel(this);
        new ConfirmacaoSenhaTextField(this);
        new SignupButton(this);
        new GrupoComboBox(this);
        new VoltarButton(this);
    }
}
