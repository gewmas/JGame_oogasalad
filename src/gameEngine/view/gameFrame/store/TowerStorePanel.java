package gameEngine.view.gameFrame.store;

import java.awt.BorderLayout;
import gameEngine.view.Panel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.ColleagueKeys;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.GameFrameMediator;


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
     * @param engineView facilitates communication between view and controller
     */

    public TowerStorePanel (GameFrameMediator mediator, View engineView) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);

        Panel TowerInfoPanel = new TowerInfoPanel();
        mediator.addColleague(ColleagueKeys.INFOPANEL.toString(), TowerInfoPanel);

        storeOptions = new TowersOptionPanel(mediator, engineView);
        mediator.addTowersOptionPanel(storeOptions);
        add(TowerInfoPanel, BorderLayout.CENTER);
        add(storeOptions, BorderLayout.PAGE_START);

    }

}
