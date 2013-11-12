package gameEngine.view;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.File;
import java.util.List;
import gameEngine.controller.Controller;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.Tile;
import gameEngine.model.tower.Tower;
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
    
    /**
     * Changes the default cursor to the image of the tower to be placed
     */
    public void placeTower (TowerFactory towerInfo) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage(towerInfo.getImage());
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        gameFrame.setCursor(c);
    }

    /**
     * Tells the controller to send tower purchase instructions to the model
     * and then reset the cursor
     */
    public void buyTower (int x, int y, String tower) {
        controller.purchaseTower(x, y, tower);
        purchaseTower();
    }

    /**
     * Requests tower information for the tower at the given location
     */
    public Tower getTowerInfo (int x, int y) {
        return controller.getTowerInfo(x, y);
    }

    /**
     * After tower is purchased, the cursor is set to the default cursor.
     */
    public void purchaseTower () {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    /**
     * Gets the dimensions of the game on initialization
     */
    public Dimension getGameSize () {
        return controller.getGameSize();
    }
    
    public List<Tile> getPath(){
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
