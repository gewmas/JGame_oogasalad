package gameEngine.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.io.File;
import java.util.List;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.Tile;
import gameEngine.model.tower.Tower;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.GameFrameMediator;
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
    private GameFrameMediator mediator;

    public View (Controller controller) {
        this.controller = controller;
        mediator=new GameFrameMediator();
        gameFrame = new GameFrame(controller, this,mediator);
        initializationFrame = new InitializationFrame(this);
    }

    public void selectNewGame () {
        mediator.endGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this,mediator);
        initializationFrame = new InitializationFrame(this);
    }

    public void loadNewGame () {
        gameFrame.showGame();
        initializationFrame.dispose();
    }

    public void startGame () {
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

    /**
     * Tells the controller to send tower purchase instructions to the model
     * and then reset the cursor
     */
    public void buyTower (int x, int y, String tower) {
        if (controller.purchaseTower(x, y, tower)){
            gameFrame.purchaseTower();
        }     
    }

    /**
     * Requests tower information for the tower at the given location
     */
    public Tower getTowerInfo (int x, int y) {
        return controller.getTowerInfo(x, y);
    }

    /**
     * Gets the dimensions of the game on initialization
     */
    public Dimension getGameSize () {
        return controller.getGameSize();
    }

    public List<Tile> getPath () {
        return controller.getPath();
    }

    /**
     * Gets the background image of the game upon initialization
     */
    public String getBGImage () {
        return controller.getBGImage();
    }

    public int getMoney () {
        return controller.getMoney();
    }

    public int getLives () {
        return controller.getLives();
    }

    public List<TowerFactory> getTowers () {
        return controller.getTowerFactory();
    }
}
