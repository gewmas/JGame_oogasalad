package gameEngine.view;

import java.awt.Dimension;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import gameEngine.controller.Controller;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.Tile;
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
    private InitializationFrame initializationFrame;
    private Controller controller;
    private GameFrameMediator mediator;

    public View (Controller controller) {
        this.controller = controller;
        mediator = new GameFrameMediator();
        gameFrame = new GameFrame(controller, this, mediator);
        initializationFrame = new InitializationFrame(this);
        initializationFrame.showFrame();
    }

    public void selectNewGame () {
        mediator.endGame();
        gameFrame.dispose();
        gameFrame = new GameFrame(controller, this, mediator);
        initializationFrame.setVisible(true);
    }

    public void loadNewGame () {
        gameFrame.showGame();
        initializationFrame.setVisible(false);
    }

    public void startJGame () {
        gameFrame.showGame();
    }
    
    public void startModel(){
        controller.startGame();
    }

    public void newGame (File file) {
        try {
            controller.newGame(file);
            initializationFrame.setVisible(false);
        }
        catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                                          StyleConstants.resourceBundle.getString("FileReadError"));
        }
    }

    /**
     * Tells the controller to send tower purchase instructions to the model
     * and then reset the cursor
     */
    public boolean buyTower (int x, int y, String tower) {
        return (controller.purchaseTower(x, y, tower));
    }

    /**
     * Requests tower information for the tower at the given location
     */
    public PurchaseInfo getTowerInfo (int x, int y) {
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

    public List<PurchaseInfo> getTowers () {
        return controller.getTowerFactory();
    }

    public boolean activateCheat (String cheat) {
       return controller.activateCheat(cheat);
        
    }
}
