package gameEngine.view;

import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import jgame.impl.JGEngineInterface;
import gameEngine.controller.Controller;
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
    private Controller controller;


    public View (Controller controller) {
        this.controller = controller;
        this.gameFrame = new GameFrame(controller, this);
        this.initializationFrame = new InitializationFrame(this);

    }

    /**
     * Prompts user for file to be read to start game
     */
    public void promptForFile () {
        initializationFrame.showFrame();

    }

    public void selectNewGame () {
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);
        initializationFrame.setVisible(true);
    }

    /**
     * Used to start the game 
     */
    public void startJGame () {
        gameFrame.showGame();
    }

    public void startModel () {
        controller.startGame();
    }

    public void newGame (File file) {
        try {
            controller.newGame(file);
            initializationFrame.setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                                          StyleConstants.resourceBundle.getString("FileReadError"));
        }
    }

    public void sendEngine (JGEngineInterface engine) {
        controller.setJGEngine(engine);
    }

    /**
     * Tells the controller to send tower purchase instructions to the model
     * and then reset the cursor
     */
    public boolean buyTower (int x, int y, PurchaseInfo itemInformation) {
        return (controller.purchaseObject(x, y, itemInformation));

    }

    /**
     * Requests tower information for the tower at the given location
     */
    public PurchaseInfo getTowerInfo (int x, int y) {
        return controller.getTowerInfo(x, y);
    }
    
    public GameInfo getGameInfo(){
        return controller.getGameInfo();
    }


    public List<Tile> getPath () {
        return controller.getPath();
    }

    public Map<String, List<PurchaseInfo>> getInventory () {
        return controller.getInventory();
    }



    public void endGame () {
        gameFrame.endGame();

    }

    public boolean activateCheat (String cheat) {
        return controller.activateCheat(cheat);
    }



    public boolean upgradeTower (int x, int y) {
        return controller.upgradeTower(x, y);
    }

    public boolean sellTower (int towerX, int towerY) {
        return controller.sellTower(towerX, towerY);
    }

}
