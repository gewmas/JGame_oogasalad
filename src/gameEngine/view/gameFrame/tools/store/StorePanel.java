package gameEngine.view.gameFrame.tools.store;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameInitializable;
import gameEngine.view.gameFrame.GameUpdatable;
import gameEngine.view.gameFrame.ItemPurchaser;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase items
 *         Supports different categories of items by visually separating them
 *         into tabs. Each category is represented as a StoreCategoryPanel
 *         Implements GameUpdatable and GameInitializable because the storePanel relies
 *         on the game to receive a signal to initialize and update itself throghout the course
 *         of the game
 */
@SuppressWarnings("serial")
public class StorePanel extends JPanel implements GameUpdatable, GameInitializable {

    private JTabbedPane storeTabbedPane;
    private ControllerToViewInterface controller;
    private ItemOptionsDisplayer itemOptionsDisplayer;
    private List<StoreCategoryPanel> storeCategories;
    private ItemPurchaser itemPurchaser;

    /**
     * @param controller interface between controller and GUI
     * @param itemOptionsDisplayer tool to display different options that can be performed on a
     * @param itemPurchaser tool to facilitate purchasing a store item
     */
    public StorePanel (ControllerToViewInterface controller,
                       ItemOptionsDisplayer itemOptionsDisplayer,
                       ItemPurchaser itemPurchaser) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.itemOptionsDisplayer = itemOptionsDisplayer;
        this.controller = controller;
        this.itemPurchaser = itemPurchaser;
        storeCategories = new ArrayList<StoreCategoryPanel>();

        storeTabbedPane = new JTabbedPane();
        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

    /**
     * When game is ready to be started, the store
     * retrieves the items to be added to inventory and dynamically
     * adds the items to its display
     */
    public void openAndStockStore () {
        Map<String, List<PurchaseInfo>> storeInventory = controller.getInventory();

        for (String item : storeInventory.keySet()) {
            Map<String, String> images = controller.getImageURL();
            StoreCategoryPanel storeCategory =
                    new StoreCategoryPanel(controller, images, storeInventory.get(item),
                                           itemOptionsDisplayer, itemPurchaser);
            storeTabbedPane.addTab(item, storeCategory);
            storeCategories.add(storeCategory);
        }

    }

    @Override
    public void update () {
        for (StoreCategoryPanel options : storeCategories) {
            options.updateStoreStatus();
        }
    }

    @Override
    public void endGame () {

        storeTabbedPane.removeAll();
        this.revalidate();
        this.repaint();

    }

    @Override
    public void initialize () {
        openAndStockStore();

    }

}
