package gameEngine.view.gameFrame.menu;

import gameEngine.view.StyleConstants;
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
    private MenuActions menuActions;

    public SelectNewGameItem (MenuActions menuActions) {
        super(StyleConstants.resourceBundle.getString("NewGameFileMenu"));
        this.menuActions = menuActions;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        
        menuActions.selectNewGame();

    }

}
