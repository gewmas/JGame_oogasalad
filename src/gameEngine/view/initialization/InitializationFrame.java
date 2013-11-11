package gameEngine.view.initialization;

import gameEngine.view.Frame;
import gameEngine.view.gameFrame.GameFrame;


/**
 * Frame that prompts user to select a file to load to
 * begin a game
 * 
 * @author Lalita Maraj
 * 
 */
public class InitializationFrame extends Frame {

    /**
     * @param gameFrame interface between view components and controller
     */
    public InitializationFrame (GameFrame gameFrame) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(new InitializationPanel(this, gameFrame));

        pack();
        setVisible(true);
    }

}
