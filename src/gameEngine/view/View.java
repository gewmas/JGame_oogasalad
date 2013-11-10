package gameEngine.view;

import gameEngine.controller.Controller;
import gameEngine.view.initialization.InitializationFrame;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
 * 
 * @author Lalita Maraj, Alex Zhu
 * 
 */
public class View {
    private GameFrame gameFrame;
    private Frame initializationFrame;
    private Controller controller;

    public View (Controller controller) {
        this.controller = controller;
        gameFrame = new GameFrame(controller, this);
        initializationFrame = new InitializationFrame(controller, this);

    }

    // May need to actually initialize the game when this is called
    public void showGame () {
        gameFrame.showGame();
    }

    public void selectNewGame () {
        gameFrame.dispose();
        initializationFrame = new InitializationFrame(controller, this);
    }

}
