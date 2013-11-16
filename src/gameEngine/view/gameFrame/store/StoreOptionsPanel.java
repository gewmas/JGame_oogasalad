package gameEngine.view.gameFrame.store;


import gameEngine.model.tower.TowerInfo;
import gameEngine.view.Panel;
import gameEngine.view.StyleConstants;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * Panel that contains the inventory of
 * different options a
 * user can purchase
 * 
 * @author Lalita Maraj
 */
@SuppressWarnings("serial")
public abstract class StoreOptionsPanel extends Panel {

    private static final String LAYOUT_WRAP = "wrap 4";
    private static final int PANEL_WIDTH = 250;
    private static final int PANEL_HEIGHT = 300;
    protected List<StoreItemButton> storeItems;
    protected View view;

    /**
     * @param mediator facilitates communication between view components
     * @param engineView facilitates communication between view and controller
     */
    protected StoreOptionsPanel (GameFrameMediator mediator, View engineView) {

        super();
        this.view = engineView;
        this.storeItems = new ArrayList<StoreItemButton>();

        setUIStyle();
        createOptionsScrollPanel(mediator);

    }

    /**
     * Creates the scroll panel that will hold the
     * inventory of towers a user can purchase
     * 
     * @param mediator facilitates communication between view components
     */
    private void createOptionsScrollPanel (GameFrameMediator mediator) {

        JPanel options = new JPanel(new MigLayout(LAYOUT_WRAP));
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
     * @param view facilitates communication between view and model
     */
    private  void addStoreInventory (JPanel optionsPanel, GameFrameMediator mediator) {
        List<TowerInfo> towerInformation = getItems();
        for (TowerInfo tower : towerInformation ) {
            StoreItemButton towerButton = new StoreItemButton(tower, mediator, view);
            optionsPanel.add(towerButton);
            storeItems.add(towerButton);

        }
    }

    protected abstract List<TowerInfo> getItems();

    /**
     * Used to update the status of each TowerStoreButton.
     * Toggles their enabled/disabled status based on the user's
     * money supply
     * 
     */
    public void updateStoreStatus () {
        for (StoreItemButton button : storeItems) {
            button.toggleButtonActivation(view.getMoney());
        }
    }

}
