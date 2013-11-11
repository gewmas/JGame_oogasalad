package gameEngine.view;

import java.io.File;
import gameEngine.controller.Controller;
import gameEngine.view.gameFrame.GameFrame;
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
        initializationFrame = new InitializationFrame(this);

    }

    public void selectNewGame () {
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);
        initializationFrame = new InitializationFrame(this);
    }

    public void loadNewGame () {
        gameFrame.showGame();
        initializationFrame.dispose();
    }
    
    public void startGame(){
        gameFrame.showGame();
    }

    public void newGame (File file) {
        try {
            controller.newGame(file);
            initializationFrame.dispose();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}
