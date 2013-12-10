package gameEngine.view.gameFrame.menu;

import gameEngine.view.ViewConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


/**
 * Menu specific to selecting a new game
 * 
 * @author Lalita Maraj
 * 
 */
@SuppressWarnings("serial")
class SelectNewGameItem extends AbstractAction {
    private MenuAction menuActions;

    public SelectNewGameItem (MenuAction menuActions) {
        super(ViewConstants.resourceBundle.getString("NewGameFileMenu"));
        this.menuActions = menuActions;
    }

    @Override
    public void actionPerformed (ActionEvent e) {

        menuActions.executeAction();

    }

}
