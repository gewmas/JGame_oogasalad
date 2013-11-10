package gameEngine.view.store;

import gameEngine.model.TowerInfo;
import gameEngine.view.GameFrame;
import gameEngine.view.Mediator;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;


/**
 * Panel that contains the inventory of
 * different tower options a
 * user can purchase
 * 
 * @author Lalita Maraj
 */
@SuppressWarnings("serial")
public class TowersOptionPanel extends Panel {

    private static final int PANEL_WIDTH = 300;
    private static final int PANEL_HEIGHT = 400;
    private List<TowerStoreButton> storeItems;
    private GameFrame gameFrame;

    /**
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and controller
     */
    protected TowersOptionPanel (Mediator mediator, GameFrame gameFrame) {

        super();
        this.gameFrame = gameFrame;
        this.storeItems = new ArrayList<TowerStoreButton>();

        setUIStyle();
        createOptionsScrollPanel(mediator);

    }

    /**
     * Creates the scroll panel that will hold the
     * inventory of towers a user can purchase
     * 
     * @param mediator facilitates communication between view components
     */
    private void createOptionsScrollPanel (Mediator mediator) {
        JPanel options = new JPanel();
        options.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        addStoreInventory(options, mediator);

        JScrollPane scrollPane = new JScrollPane(options);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(scrollPane);
    }

    /**
     * Set look and feel of the display of available towers
     */
    private void setUIStyle () {

        Border listPanelBorder =
                BorderFactory.createTitledBorder(StyleConstants.resourceBundle
                        .getString("StoreName"));
        setBorder(listPanelBorder);
    }

    /**
     * Adds buttons based on the defined towers specified by Model
     * 
     * @param optionsPanel panel buttons are added to
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and model
     */
    private void addStoreInventory (JPanel optionsPanel, Mediator mediator) {

        for (TowerInfo tower : gameFrame.getTowers()) {
            TowerStoreButton towerButton = new TowerStoreButton(tower, mediator, gameFrame);
            optionsPanel.add(towerButton);
            storeItems.add(towerButton);
        }

    }

    @Override
    /**
     * Used to update the status of each TowerStoreButton.
     * Toggles their enabled/disabled status based on the user's 
     * money supply
     * 
     */
    public void updateInventoryStatus () {
        for (TowerStoreButton button : storeItems) {
            button.toggleButtonActivation(gameFrame.getMoney());
        }
    }

}
