package gameEngine.view;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import jgame.impl.JGEngineInterface;
import gameAuthoring.view.MainPanel;
import gameEngine.controller.Controller;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.menu.MenuActions;
import gameEngine.view.initialization.InitializationFrame;


/**
 * The main view class that orchestrates the sequence of events
 * for selecting a game, starting a game, selecting a new game, and ending a game.
 * This class serves as the interface to the controller as the front end.
 * No other front end elements are exposed to the rest of the game Engine.
 * 
 * @author Lalita Maraj, Alex Zhu
 * 
 */
public class View implements MenuActions {
    private GameFrame gameFrame;
    private InitializationFrame initializationFrame;
    private ControllerToViewInterface controller;

    public View (ControllerToViewInterface controller) {
        this.controller = controller;
        this.gameFrame = new GameFrame(controller,this);
        this.initializationFrame = new InitializationFrame(this);

    }

    /**
     * Prompts user for file to be read to start game
     */
    public void promptForFile () {
        initializationFrame.showFrame();
    }

    /**
     * Used to start the game
     */
    public void startJGame () {
        gameFrame.showGame();
    }

    public void newGame (File file) {
        try {
            controller.newGame(file);
            initializationFrame.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                                          ViewConstants.resourceBundle.getString("FileReadError"));
        }
    }

    @Override
    public void endGame () {
        gameFrame.endGame();
    }

    @Override
    public void selectNewGame () {
        controller.startGame();
        gameFrame.endGame();
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller,this);
        controller = new Controller();
        initializationFrame.showFrame();
    }

    @Override
    public void goToMainMenu () {
        controller.startGame();
        gameFrame.endGame();
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller,this);
        
        MainPanel panel = new MainPanel();
        panel.show();
    }

}
