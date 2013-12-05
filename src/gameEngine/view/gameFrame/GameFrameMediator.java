package gameEngine.view.gameFrame;


import gameEngine.view.gameFrame.tools.InfoDisplayPanel;
import gameEngine.view.gameFrame.tools.store.StorePanel;


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
@Deprecated
public class GameFrameMediator {

    private GameFrame gameFrame;
    private CanvasPanel canvasPanel;
    private InfoDisplayPanel towerInfoPanel;
    private StorePanel storePanel;

    /**
     * Destroys the jgame instance so that it can be reloaded
     */

    public void quitGame () {
        canvasPanel.quitGame();
    }

    public void endGame () {
        towerInfoPanel.clearDisplay();
        canvasPanel.endGame();
        storePanel.closeStore();
    }




    public void addGameFrame (GameFrame gameFrame) {
        this.gameFrame = gameFrame;

    }

    public void addGame (CanvasPanel canvasPanel) {
        this.canvasPanel = canvasPanel;

    }
    @Deprecated
    public void addInfoPanel (InfoDisplayPanel towerInfoPanel) {
        this.towerInfoPanel = towerInfoPanel;
    }
    @Deprecated
    private void clearDisplay () {
        this.towerInfoPanel.clearDisplay();
    }
    @Deprecated
    public void openStore () {
        storePanel.openStore();
    }
    @Deprecated
    private void closeStore () {
        storePanel.closeStore();
    }
    @Deprecated
    /**
     * Updates the enabled status of store items.
     */
    public void updateStoreStatus () {
        storePanel.update();
    }
    public void addStore (StorePanel storePanel) {
        this.storePanel = storePanel;

    }

    @Deprecated
    public void openInfoPanel () {
        this.towerInfoPanel.setVisible(true);
        gameFrame.pack();
    }
}
