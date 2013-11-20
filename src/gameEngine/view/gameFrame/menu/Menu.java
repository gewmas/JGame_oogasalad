package gameEngine.view.gameFrame.menu;


import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * @author lalitamaraj
 *         Menu used to define the menus in the GUI
 */
public class Menu extends JMenuBar {

    public Menu (View view) {
       
        JMenu menuitem = new JMenu("File");
        menuitem.add(new NewGameMenuItem(view));
        menuitem.add(new RestartGameMenuItem(view));
       
        add(menuitem);

    }

}
