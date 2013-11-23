package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase items
 *         Contains the option to select items and panel that displays
 *         item information
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

        InfoDisplayPanel infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
        mediator.addInfoPanel(infoPanel);
        JTabbedPane storeTabbedPane = new JTabbedPane();

        // TO BE CHANGED
        towerStoreOptions = new TowerOptionsPanel(mediator, engineView);
        objectsStoreOptions = new TowerOptionsPanel(mediator, engineView);
        // TO BE CHANGED

        storeTabbedPane.addTab("Towers", towerStoreOptions);
        storeTabbedPane.addTab("Objects", objectsStoreOptions);
        mediator.addTowersOptionPanel(towerStoreOptions);
        add(infoPanel, BorderLayout.CENTER);
        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

}
