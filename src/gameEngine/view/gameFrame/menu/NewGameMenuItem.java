package gameEngine.view.gameFrame.menu;

import gameEngine.view.View;
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
class NewGameMenuItem extends AbstractAction {
    private View view;

    public NewGameMenuItem (View engineView) {
        super(StyleConstants.resourceBundle.getString("NewGameFileMenu"));
        this.view = engineView;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        view.selectNewGame();

    }

}
