package gameAuthoring.menuBar;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class MenuBar extends JMenuBar {

    public MenuBar () {
        add(fileMenu());
    }

    private JMenu fileMenu () {
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Save") {

            @Override
            public void actionPerformed (ActionEvent e) {
                System.out.println("test");
            }

        });
        return menu;
    }

}
