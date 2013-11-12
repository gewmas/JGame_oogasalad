package gameEngine.view.gameFrame.menu;

import gameEngine.controller.Controller;
import gameEngine.view.View;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * @author lalitamaraj
 *         Menu used to define the menus in the GUI
 */
public class Menu extends JMenuBar {
    View engineView;

    public Menu (View engineView, Controller controller) {
        this.engineView = engineView;
        JMenu menuitem = new JMenu("File");
        menuitem.add(new NewGameMenuItem(engineView));
        menuitem.add(new RestartGameMenuItem(controller));
        add(menuitem);

    }

}
