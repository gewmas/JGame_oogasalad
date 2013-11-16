package gameEngine.view.gameFrame;

import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.gameFrame.store.TowerInfoPanel;
import gameEngine.view.gameFrame.store.TowerStorePanel;
import gameEngine.view.gameFrame.store.TowersOptionPanel;


/**
 * Used to implement the Mediator design pattern. Facilitates
 * communication between view components.
 * The components that interact with the mediator implement
 * the Colleage interface.
 * For each method defined in the mediator, the colleague
 * interface has a corresponding method and those colleagues that
 * are impacted by the actions described in the methods should implement
 * their own behavior for said methods.
 * 
 * When a method is called on the mediator, the mediator calls
 * the corresponding method on the colleagues that are impacted.
 * 
 * 
 * @author Lalita Maraj
 * 
 */
public class GameFrameMediator {


    private GameFrame gameFrame;
    private CanvasPanel canvasPanel;
    private TowerStorePanel storePanel;

  

    /**
     * Destroys the jgame instance so that it can be reloaded
     */

    public void endGame () {
        canvasPanel.endGame();
    }

    /**
     * Notifies all colleagues that need to be updated
     * when a user is trying to purchase a tower
     * 
     * @param towername
     */
    public void placeTower (TowerInfo towerInfo) {

        canvasPanel.placeTower(towerInfo);
        gameFrame.placeTower(towerInfo);
    }

    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * 
     * @param tower
     */
    public void displayTowerInfo (TowerInfo tower) {
        storePanel.displayTowerInfo(tower);
    }

    /**
     * Executes the actions on the view components
     * that are impacted by the purchase of a tower
     */
    public void purchaseTower () {
        gameFrame.purchaseTower();
    }

    /**
     * Updates the enabled status of store items.
     */
    public void updateStoreStatus () {
        storePanel.updateStoreStatus();
    }

    public void addGameFrame (GameFrame gameFrame) {
        this.gameFrame = gameFrame;

    }

    public void addGamePanel (CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

    }

    public void addStorePanel (TowerStorePanel storePanel) {
        this.storePanel = storePanel;
        
    }

}
