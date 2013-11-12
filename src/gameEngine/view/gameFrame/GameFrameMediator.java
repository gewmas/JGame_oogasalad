package gameEngine.view.gameFrame;

import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.Panel;
import gameEngine.view.gameFrame.store.TowerInfoPanel;
import gameEngine.view.gameFrame.store.TowersOptionPanel;
import java.util.HashMap;
import java.util.Map;


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

    private Map<String, GameFrameColleague> colleagues;
    private TowersOptionPanel storeOptions;
    private GameFrame gameFrame;
    private CanvasPanel canvasPanel;
    private TowerInfoPanel towerInfoPanel;
    public GameFrameMediator () {
        colleagues = new HashMap<String, GameFrameColleague>();

    }
    public void addTowersOptionPanel(TowersOptionPanel storeOptions){
        this.storeOptions = storeOptions;
    }

    /**
     * a map is used to store all the colleagues the mediator liasons
     * with. Key strings are stored in the MediatorConstants class and
     * shuld be used to both add and get colleagues.
     * 
     * @param name key of colleague
     * @param colleague Colleague to be added
     */
    public void addColleague (String name, GameFrameColleague colleague) {
        colleagues.put(name, colleague);
    }

    /**
     * Notifies all colleagues that need to be updated
     * when a user is trying to purchase a tower
     * 
     * @param towername
     */
    public void placeTower (TowerFactory towerInfo) {
      
        canvasPanel.placeTower(towerInfo);
        gameFrame.placeTower(towerInfo);
    }

    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * 
     * @param tower
     */
    public void displayTowerInfo (TowerFactory tower) {
        towerInfoPanel.displayTowerInfo(tower);
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
        storeOptions.updateStoreStatus();
    }
    public void addGameFrame (GameFrame gameFrame) {
       this.gameFrame = gameFrame;
        
    }
    public void addGame (CanvasPanel canvasPanel) {
       this.canvasPanel = canvasPanel;
        
    }
    public void addTowerInfoPanel (TowerInfoPanel towerInfoPanel) {
       this.towerInfoPanel  = towerInfoPanel;
        
    }

}
