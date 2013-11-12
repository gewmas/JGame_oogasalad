package gameAuthoring.menuBar;

import gameAuthoring.GameData;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


public class MenuBar extends JMenuBar {
    GameData myGameData;

    public MenuBar (GameData gameData) {
        add(fileMenu());
        myGameData = gameData;
    }

    private JMenu fileMenu () {
        JMenu menu = new JMenu("File");
        menu.add(new AbstractAction("Save") {

            @Override
            public void actionPerformed (ActionEvent e) {
                myGameData.writeToFile();
            }

        });
        return menu;
    }

}
