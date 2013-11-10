package gameEngine.view.menu;

import gameEngine.view.EngineView;
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
    private EngineView engineView;

    public NewGameMenuItem (EngineView engineView) {
        super(StyleConstants.myResources.getString("NewGameFileMenu"));
        this.engineView = engineView;
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        engineView.selectNewGame();

    }

}
