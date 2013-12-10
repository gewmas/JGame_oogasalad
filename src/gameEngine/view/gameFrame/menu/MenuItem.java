package gameEngine.view.gameFrame.menu;


import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;

/**
 * @author Lalita Maraj
 *Super class used to define items on the menu. 
 *Requires a menuAction item to be passed as a paramter 
 *to  define the behavior tied to the menu item
 */
@SuppressWarnings("serial")
public abstract class MenuItem extends AbstractAction {
    protected MenuActions menuActions;

    public MenuItem (MenuActions menuActions,String menuName) {

        super(menuName);

        this.menuActions = menuActions;
    }
    @Override
    public abstract void actionPerformed (ActionEvent e);
}
