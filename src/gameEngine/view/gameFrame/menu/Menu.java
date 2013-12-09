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
        menuitem.add(new SelectNewGameItem(menuOptions));
        menuitem.add(new EndGameMenuItem(menuOptions));
        menuitem.add(new ReturnToMainMenuItem(menuOptions));
        add(menuitem);

    }

}
