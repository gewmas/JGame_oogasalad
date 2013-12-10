package gameEngine.view.gameFrame.store;

import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.ViewConstants;
import gameEngine.view.gameFrame.ItemPurchaser;
import gameEngine.view.gameFrame.towerUpgrader.ItemOptionsDisplayer;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import net.miginfocom.swing.MigLayout;


/**
 * Panel that contains the inventory of
 * different items a user can purchase for a specific Category
 * of items. Example: Towers or Temporary Barriers
 * 
 * @author Lalita Maraj
 */
@SuppressWarnings("serial")
public class StoreCategoryPanel extends JPanel {

    private final String LAYOUT_WRAP = "wrap 4";
    private final int PANEL_WIDTH = 250;
    private final int PANEL_HEIGHT = 200;

    private Collection<StoreItemButton> storeItems;
    private ControllerToViewInterface controller;
    private StoreItemCreator storeItemCreator;

    /**
     * Constructor
     * 
     * @param controller the View that serves as interface for controller
     * @param itemImagesMap map that contains the image paths for all store item
     * @param itemInformation Collection of the different items to be sold in this category
     * @param itemOptionsDisplayer Tool used to display information about store items
     * @param itemPurchaser Tool used to facilitate purchasing an item
     */
    protected StoreCategoryPanel (ControllerToViewInterface controller,
                                  Map<String, String> itemImagesMap,
                                  Collection<PurchaseInfo> itemInformation,
                                  ItemOptionsDisplayer itemOptionsDisplayer,
                                  ItemPurchaser itemPurchaser) {

        super();
        this.controller = controller;
        this.storeItems = new ArrayList<StoreItemButton>();
        this.storeItemCreator = new StoreItemCreator(itemOptionsDisplayer, itemPurchaser);
        setUIStyle();
        JPanel options = createOptionsScrollPanel();
        addStoreInventory(itemImagesMap, options, itemInformation);

    }

    /**
     * Creates the scroll panel that will hold the
     * inventory of items a user can purchase
     * returns the Panel containing all of the items
     */
    private JPanel createOptionsScrollPanel () {

        JPanel options = new JPanel(new MigLayout(LAYOUT_WRAP));
        options.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        JScrollPane scrollPane = new JScrollPane(options);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        add(scrollPane);
        return options;
    }

    /**
     * Set look and feel of the display of available towers
     */
    private void setUIStyle () {

        Border listPanelBorder =
                BorderFactory.createTitledBorder(ViewConstants.resourceBundle
                        .getString("StoreName"));
        setBorder(listPanelBorder);
    }

    /**
     * @param itemImagesMap map that contains the image paths for all store item
     * @param optionsPanel Swing panel that holds all of the store items that are creatd
     * @param storeItemsInformation contains all of the information of each storeItem to be created
     */
    private void addStoreInventory (Map<String, String> itemImagesMap,
                                    JPanel optionsPanel,
                                    Collection<PurchaseInfo> storeItemsInformation) {

        for (final PurchaseInfo storeItem : storeItemsInformation) {
            String imagePath = ViewConstants.IMAGE_PATH +
                               itemImagesMap.get(storeItem.getInfo()
                                       .get(GameEngineConstant.PURCHASE_INFO_IMAGE).trim());
            StoreItemButton storeItemButton =
                    storeItemCreator.createStoreItem(storeItem, imagePath);

            optionsPanel.add(storeItemButton);
            storeItems.add(storeItemButton);
        }
        this.revalidate();
    }

    /**
     * Used to update the status of each Item in store.
     * Toggles their enabled/disabled status based on the user's
     * money supply
     * 
     */
    public void updateStoreStatus () {
        for (StoreItemButton button : storeItems) {
            int money = controller.getGameInfo().getGold();
            button.toggleButtonActivation(money);

        }
    }

}
