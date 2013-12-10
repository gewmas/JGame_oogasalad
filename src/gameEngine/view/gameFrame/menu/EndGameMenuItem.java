package gameEngine.view.gameFrame.menu;

import gameEngine.view.ViewConstants;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


/**
 * @author Lalita Maraj
 * Menu item to end game
 */
@SuppressWarnings("serial")
public class EndGameMenuItem extends AbstractAction {
    private MenuAction menuActions;

    public EndGameMenuItem (MenuAction menuActions) {

        super(ViewConstants.resourceBundle.getString("EndGameFileMenu"));

        this.menuActions = menuActions;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        menuActions.executeAction();

    }

}
