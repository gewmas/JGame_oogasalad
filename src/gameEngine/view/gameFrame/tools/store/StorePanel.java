package gameEngine.view.gameFrame.tools.store;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameInitializable;
import gameEngine.view.gameFrame.GameUpdatable;
import gameEngine.view.gameFrame.ItemPurchaser;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase items
 *         Contains the option to select items and panel that displays
 *         item information
 */
public class StorePanel extends JPanel implements GameUpdatable, GameInitializable {

    private JTabbedPane storeTabbedPane;
    private View view;
    private ItemOptionsDisplayer utilities;
    private List<StoreOptionsPanel> storeCategories;
    private ItemPurchaser itemPurchaser;

    /**
     * @param mediator facilitates communication between view components
     * @param view facilitates communication between view and controller
     * @param itemPurchaser
     */

    public StorePanel (View view, ItemOptionsDisplayer utilities, ItemPurchaser itemPurchaser) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.utilities = utilities;
        this.view = view;

        this.itemPurchaser = itemPurchaser;
        storeCategories = new ArrayList();

        storeTabbedPane = new JTabbedPane();

        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

    public void openStore () {
        Map<String, List<PurchaseInfo>> storeInventory = view.getInventory();
        for (String item : storeInventory.keySet()) {
            StoreOptionsPanel storeCategory =
                    new StoreOptionsPanel(view, storeInventory.get(item), utilities, itemPurchaser);
            storeTabbedPane.addTab(item, storeCategory);
            storeCategories.add(storeCategory);
        }

    }

    public void update () {
        for (StoreOptionsPanel options : storeCategories) {
            options.updateStoreStatus();
        }
    }

    public void closeStore () {

        storeTabbedPane.removeAll();
        this.revalidate();
        this.repaint();

    }

    @Override
    public void initialize () {
        openStore();

    }

}
