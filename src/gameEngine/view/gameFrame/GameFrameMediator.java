package gameEngine.view.gameFrame;

import java.util.Map;
import jgame.impl.JGEngineInterface;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.store.InfoDisplayPanel;
import gameEngine.view.gameFrame.store.StoreOptionsPanel;
import gameEngine.view.gameFrame.store.StorePanel;


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

//    private StoreOptionsPanel storeOptions;
    private GameFrame gameFrame;
    private CanvasPanel canvasPanel;
    private InfoDisplayPanel towerInfoPanel;
    private StorePanel store;

//    public void addTowersOptionPanel (StoreOptionsPanel storeOptions) {
//        this.storeOptions = storeOptions;
//    }

 
    /**
     * Destroys the jgame instance so that it can be reloaded
     */

    public void quitGame () {
        canvasPanel.quitGame();
    }
    
    public void endGame(){
        this.clearDisplay();
        canvasPanel.endGame();
    }

    /**
     * Notifies all colleagues that need to be updated
     * when a user is trying to purchase a tower
     * 
     * @param towername
     */
    public void placeTower (PurchaseInfo towerInfo) {

        canvasPanel.placeTower(towerInfo);
       
    }
    
    public void setCursorImage(PurchaseInfo towerInfo){
        gameFrame.placeTower(towerInfo);
    }

    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * @param towerDisplayInfo TODO
     */
    public void displayTowerInfo (Map<String, String> towerDisplayInfo) {
        towerInfoPanel.displayInformation(towerDisplayInfo);
    }

    /**
     * Executes the actions on the view components
     * that are impacted by the purchase of a tower
     */
    
    public void exitPurchase(){
       
        restoreDefaultCursor();
    }
    
    public void restoreDefaultCursor() {
        gameFrame.restoreDefaultCursor();
    }
    
    /**
     * Updates the enabled status of store items.
     */
    public void updateStoreStatus () {
        store.updateStoreStatus();
    }

    public void addGameFrame (GameFrame gameFrame) {
        this.gameFrame = gameFrame;

    }

    public void addGame (CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

    }

    public void addInfoPanel (InfoDisplayPanel towerInfoPanel) {
        this.towerInfoPanel = towerInfoPanel;
    }
    
    public void clearDisplay(){
        this.towerInfoPanel.clearDisplay();
    }
    
    public void openStore () {
      store.addStoreInventory();
    }

    public void closeStore(){
        store.closeStore();
    }
    public void displayTowerInfo (Map<String, String> information, Map<String, String> display) {
        this.towerInfoPanel.displayInformation(information, display);
        
    }

    public void addStore (StorePanel storePanel) {
        this.store = storePanel;
        
    }
}
