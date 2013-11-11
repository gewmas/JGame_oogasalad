package gameEngine.view.initialization;

import gameEngine.view.Frame;
import gameEngine.view.View;
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
     * @param view interface between view components and controller
     */
    public InitializationFrame (View view) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        getContentPane().add(new InitializationPanel(this, view));

        pack();
        setVisible(true);
    }

}
