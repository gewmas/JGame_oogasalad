package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import java.util.Map;
import javax.swing.JTabbedPane;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.PuchaseSimulator;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase items
 *         Contains the option to select items and panel that displays
 *         item information
 */
public class StorePanel extends Panel {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private StoreOptionsPanel towerStoreOptions;
    private StoreOptionsPanel objectsStoreOptions;
    private InfoDisplayPanel infoPanel;
    /**
     * @param mediator facilitates communication between view components
     * @param engineView facilitates communication between view and controller
     */

    public StorePanel (PuchaseSimulator mediator, View engineView) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

       infoPanel = new InfoDisplayPanel(StyleConstants.resourceBundle
                .getString("ItemInfo"));
      
        JTabbedPane storeTabbedPane = new JTabbedPane();

        // TO BE CHANGED
        towerStoreOptions = new StoreOptionsPanel(mediator, engineView,infoPanel);
        objectsStoreOptions = new StoreOptionsPanel(mediator, engineView,infoPanel);
        // TO BE CHANGED

        storeTabbedPane.addTab("Towers", towerStoreOptions);
        storeTabbedPane.addTab("Objects", objectsStoreOptions);
       
        add(infoPanel, BorderLayout.CENTER);
        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

    public void updateStoreStatus () {
        towerStoreOptions.updateStoreStatus();
        
    }

    public void clearDisplay () {
        infoPanel.clearDisplay();
        
    }

    public void addStoreInventory () {
        towerStoreOptions.addStoreInventory();
        
    }

    public void displayInformation (Map<String, String> information) {
       infoPanel.displayInformation(information);
        
    }

}
