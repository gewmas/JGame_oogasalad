package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import gameEngine.model.tower.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase towers
 *         Contains the option to select towers and panel that displays
 *         tower information
 */
public class TowerStorePanel extends Panel {
    private TowersOptionPanel storeOptions;
    private TowerInfoPanel towerInfoPanel;

    /**
     * @param mediator facilitates communication between view components
     * @param engineView facilitates communication between view and controller
     */

    public TowerStorePanel (GameFrameMediator mediator, View engineView) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        towerInfoPanel = new TowerInfoPanel();
        storeOptions = new TowersOptionPanel(mediator, engineView);

        add(towerInfoPanel, BorderLayout.CENTER);
        add(storeOptions, BorderLayout.PAGE_START);

    }

    public void displayTowerInfo (TowerInfo tower) {
        towerInfoPanel.displayTowerInfo(tower);

    }

    public void updateStoreStatus () {
        storeOptions.updateStoreStatus();

    }

}
