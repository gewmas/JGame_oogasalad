package gameEngine.view.initialization;

import gameEngine.view.Frame;
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
    public InitializationFrame (Controller controller) {
        super();
        getContentPane().add(new InitializationPanel(controller));
        pack();
        setVisible(true);
    }
}
