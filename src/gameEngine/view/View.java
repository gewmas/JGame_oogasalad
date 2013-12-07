package gameEngine.view;

import java.awt.Dimension;
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
import gameEngine.view.gameFrame.GameFrameMediator;
import gameEngine.view.gameFrame.menu.MenuActions;
import gameEngine.view.initialization.InitializationFrame;


/**
 * The main view class that holds all the panels and frames included in the
 * Game Engine GUI
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
       
        gameFrame = new GameFrame(controller, this);
        initializationFrame = new InitializationFrame(this);

    }

    public void promptForFile () {
        initializationFrame.showFrame();

    }

    public void selectNewGame () {
        gameFrame.quitGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this);
        initializationFrame.setVisible(true);
    }

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

    public Map<String, List<PurchaseInfo>> getInventory () {
        return controller.getInventory();
    }

    @Deprecated
    public void quitGame () {
        gameFrame.quitGame();
    }

    public void endGame () {
        gameFrame.endGame();

    }

    public boolean activateCheat (String cheat) {
        return controller.activateCheat(cheat);
    }

    public String getGameTitle () {
        return controller.getGameTitle();
    }

    public boolean upgradeTower (int x, int y) {
        return controller.upgradeTower(x, y);
    }

    public boolean sellTower (int towerX, int towerY) {
        return controller.sellTower(towerX, towerY);
    }

}
