package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import gameEngine.view.Panel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase towers
 *         Contains the option to select towers and panel that displays
 *         tower information
 */
public class StorePanel extends Panel {
    private StoreOptionsPanel towerStoreOptions;
    private StoreOptionsPanel objectsStoreOptions;

    /**
     * @param mediator facilitates communication between view components
     * @param engineView facilitates communication between view and controller
     */

    public StorePanel (GameFrameMediator mediator, View engineView) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        StoreInfoPanel towerInfoPanel = new StoreInfoPanel();
        mediator.addTowerInfoPanel(towerInfoPanel);
        JTabbedPane storeTabbedPane = new JTabbedPane();
        
        towerStoreOptions = new TowerOptionsPanel(mediator, engineView);
        objectsStoreOptions =  new TowerOptionsPanel(mediator, engineView);
        storeTabbedPane.addTab("Towers",towerStoreOptions);
        storeTabbedPane.addTab("Objects", objectsStoreOptions);
        mediator.addTowersOptionPanel(towerStoreOptions);
        add(towerInfoPanel, BorderLayout.CENTER);
        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

}
