package gameEngine.view.gameFrame.menu;

import gameEngine.view.ViewConstants;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


/**
 * @author Lalita Maraj
 *         Menu used to define the menu items in the GUI
 */
@SuppressWarnings("serial")
public class Menu extends JMenuBar {

    public Menu (MenuActions menuOptions) {

        JMenu menuitem = new JMenu(ViewConstants.resourceBundle.getString("File"));
        menuitem.add(new SelectNewGameItem(menuOptions,ViewConstants.resourceBundle.getString("NewGameFileMenu")));
        menuitem.add(new EndGameMenuItem(menuOptions,ViewConstants.resourceBundle.getString("EndGameFileMenu")));
        menuitem.add(new ReturnToMainMenuItem(menuOptions,ViewConstants.resourceBundle.getString("ReturnToMainMenu")));
        add(menuitem);

    }

}
