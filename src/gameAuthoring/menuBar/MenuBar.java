package gameAuthoring.menuBar;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar{

    public MenuBar () {
        add(fileMenu());
    }
    
    private JMenu fileMenu () {
        JMenu menu = new JMenu("");
        return menu;
    }

}
