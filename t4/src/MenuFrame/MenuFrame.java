package MenuFrame;

import MenuFrame.Panel.MenuPanel.MenuPanel;
import Database.*;
import Auth.Validation;

import javax.swing.*;
import java.sql.SQLException;

public class MenuFrame extends JFrame {

    private final MenuPanel menuPanel;
    private final String NAME = "Tela Principal";
    private final int WIDTH = 600;
    private final int HEIGHT = 600;
    private static MenuFrame instance = null;

    public MenuFrame(){
        this.addWindowListener(new OnChange());

        menuPanel = new MenuPanel();
        this.add(menuPanel);

        this.setSize(WIDTH,HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setTitle(NAME);
        this.setVisible(true);
    }

    private static void count() throws Exception {
        String email = Validation.user.getString("email");

        Database.getInstance().incAccessCounter(email);
    }

    public static MenuFrame getInstance() throws Exception {
        if(instance == null){
            count();
            instance = new MenuFrame();
        }
        return instance;
    }
}
