package gameEngine.view.gameFrame.tools.store;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.Panel;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * @author Lalita Maraj
 *         Panel to hold the store used to purchase items
 *         Contains the option to select items and panel that displays
 *         item information
 */
public class StorePanel extends Panel {

    private JTabbedPane storeTabbedPane;
    private View view;
    private GameFrameMediator mediator;
    private List<StoreOptionsPanel> storeCategories;

    /**
     * @param mediator facilitates communication between view components
     * @param view facilitates communication between view and controller
     */

    public StorePanel (GameFrameMediator mediator, View view) {
        super();
        BorderLayout borderLayout = new BorderLayout();
        setLayout(borderLayout);
        this.view = view;
        this.mediator = mediator;
        storeCategories = new ArrayList();

        storeTabbedPane = new JTabbedPane();

        add(storeTabbedPane, BorderLayout.PAGE_START);

    }

    public void addStoreInventory () {
        Map<String, List<PurchaseInfo>> storeInventory = view.getInventory();
        for (String item : storeInventory.keySet()) {
            StoreOptionsPanel storeCategory =
                    new StoreOptionsPanel(mediator, view, storeInventory.get(item));
            storeTabbedPane.addTab(item, storeCategory);
            storeCategories.add(storeCategory);
        }

    }

    public void updateStoreStatus () {
        for (StoreOptionsPanel options : storeCategories) {
            options.updateStoreStatus();
        }
    }

    public void closeStore () {

        storeTabbedPane.removeAll();
        this.revalidate();
        this.repaint();

    }

}
