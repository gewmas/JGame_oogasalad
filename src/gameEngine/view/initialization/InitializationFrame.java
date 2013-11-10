package gameEngine.view.initialization;

import gameEngine.view.Button;
import gameEngine.view.EngineView;
import gameEngine.view.Frame;
import gameEngine.view.StyleConstants;
import gameEngine.controller.*;


/**
 * Frame that prompts user to select a file to load to
 * begin a game
 * 
 * @author Lalita Maraj
 * 
 */
public class InitializationFrame extends Frame {

    /**
     * @param controller facilitates communication between view and model
     */

    public InitializationFrame (Controller controller, EngineView engineView) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(new InitializationPanel(controller, this, engineView));
        pack();
        setVisible(true);
    }

}
