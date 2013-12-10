package gameEngine.view.gameFrame.tools.store;

import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.ItemPurchaser;
import gameEngine.view.gameFrame.towerUpgrader.ItemOptionsDisplayer;


/**
 * @author Lalita Maraj
 *         Tool used to create the the StoreItemButtons
 */
public class StoreItemCreator {
    private ItemOptionsDisplayer itemOptionsDisplayer;
    private StoreButtonAction hoverExitAction;
    private ItemPurchaser itemPurchaser;

    /**
     * Constructor
     * 
     * @param itemOptionsDisplayer tool used to display the different options that can be performed
     *        on an item
     * @param itemPurchaser tool used to facilitate purchasing an item in the game
     */
    public StoreItemCreator (final ItemOptionsDisplayer itemOptionsDisplayer,
                             ItemPurchaser itemPurchaser) {
        this.itemOptionsDisplayer = itemOptionsDisplayer;
        this.itemPurchaser = itemPurchaser;
        this.hoverExitAction = new StoreButtonAction() {

            @Override
            public void executeAction () {
                itemOptionsDisplayer.clearDisplay();

            }

        };
    }

    /**
     * @param storeItemInformation information about store item to be represented as button
     * @param imagePath path of image to be used as button icon
     * @return a StoreItemButton that represents the store item in the storeItemInformation
     */
    public StoreItemButton createStoreItem (final PurchaseInfo storeItemInformation,
                                            String imagePath) {
        StoreButtonAction clickAction = new StoreButtonAction() {

            @Override
            public void executeAction () {
                itemPurchaser.selectPurchaseTower(storeItemInformation);

            }

        };
        StoreButtonAction hoverAction =
                new StoreItemHoverAction(itemOptionsDisplayer, storeItemInformation);

        StoreItemButton towerButton =
                new StoreItemButton(imagePath, storeItemInformation, hoverExitAction, hoverAction,
                                    clickAction);
        return towerButton;

    }
}
