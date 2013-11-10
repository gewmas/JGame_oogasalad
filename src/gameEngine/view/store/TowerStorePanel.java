package gameEngine.view.store;

import java.awt.BorderLayout;
import gameEngine.view.GameFrame;
import gameEngine.view.Mediator;
import gameEngine.view.ColleagueKeys;
import gameEngine.view.Panel;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase towers
 *         Contains the option to select towers and panel that displays
 *         tower information
 */
public class TowerStorePanel extends Panel {
    private TowersOptionPanel storeOptions;

    /**
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and controller
     */

    public TowerStorePanel (Mediator mediator, GameFrame gameFrame) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        Panel TowerInfoPanel = new TowerInfoPanel();
        mediator.addColleague(ColleagueKeys.INFOPANEL.toString(), TowerInfoPanel);

        storeOptions = new TowersOptionPanel(mediator, gameFrame);
        mediator.addColleague(ColleagueKeys.STOREOPTIONS.toString(), storeOptions);

        add(TowerInfoPanel, BorderLayout.CENTER);
        add(storeOptions, BorderLayout.PAGE_START);

    }

}
