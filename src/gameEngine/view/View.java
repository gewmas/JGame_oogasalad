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
        initializationFrame = new InitializationFrame(gameFrame);

    }



    public void selectNewGame () {
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);
        initializationFrame = new InitializationFrame(gameFrame);
    }
    
    public void loadNewGame(){
        gameFrame.showGame();
        initializationFrame.dispose();
    }

}
