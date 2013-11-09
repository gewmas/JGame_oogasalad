package gameEngine.view.store;

import java.awt.BorderLayout;
import gameEngine.controller.Controller;
import gameEngine.view.Mediator;
import gameEngine.view.MediatorConstants;
import gameEngine.view.Panel;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase towers
 *         Contains the option to select towers and panel that displays
 *         tower information
 */
public class TowerStorePanel extends Panel {


    /**
     * @param mediator  facilitates communication between view components
     * @param controller facilitates communication between view and model
     */
    public TowerStorePanel (Mediator mediator, Controller controller) {
        super();

        Panel TowerInfoPanel = new TowerInfoPanel();
        Panel storeOptions = new TowersOptionPanel(mediator, controller);
        mediator.addColleague(MediatorConstants.INFO_PANEL_KEY, TowerInfoPanel);
        
        add(TowerInfoPanel, BorderLayout.CENTER);
        add(storeOptions, BorderLayout.PAGE_START);
        
 
    }


}
