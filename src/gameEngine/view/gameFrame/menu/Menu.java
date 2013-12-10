package gameEngine.view.gameFrame.menu;

import gameEngine.view.View;
import gameEngine.view.ViewConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * @author Lalita Maraj
 *         Menu used to define the menu items in the GUI
 */
@SuppressWarnings("serial")
public class Menu extends JMenuBar {

    public Menu (MenuActionFactory  factory) {

        JMenu menuitem = new JMenu(ViewConstants.resourceBundle.getString("File"));
        menuitem.add(new SelectNewGameItem(factory.createMenuAction(factory.SELECT_NEW_GAME)));
        menuitem.add(new EndGameMenuItem(factory.createMenuAction(factory.END_GAME)));
        menuitem.add(new ReturnToMainMenuItem(factory.createMenuAction(factory.MAIN_MENU)));
        add(menuitem);

    }

}
