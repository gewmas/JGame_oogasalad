package gameEngine.view.gameFrame.menu;


import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * @author lalitamaraj
 *         Menu used to define the menus in the GUI
 */
public class Menu extends JMenuBar {

    public Menu (MenuActions menuOptions) {
       
        JMenu menuitem = new JMenu("File");
        menuitem.add(new NewGameMenuItem( menuOptions));
        menuitem.add(new EndGameMenuItem( menuOptions));
       
        add(menuitem);

    }

}
