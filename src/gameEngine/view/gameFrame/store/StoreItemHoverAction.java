package gameEngine.view.gameFrame.store;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.tools.DisplayValue;
import gameEngine.view.gameFrame.towerUpgrader.ItemOptionsDisplayer;
import java.util.ArrayList;
import java.util.Collection;



/**
 * @author Lalita Maraj
 *         A StoreButtonAction that implements the behavior that would happen when
 *         a user hovers over an item over the store
 */
public class StoreItemHoverAction implements StoreButtonAction {

    /**
     * Keys to be displayed from the PurchaseInfo data structure when a user hovers
     * over the storeItem button
     */
    private final String[] DISPLAY_KEYS = { GameEngineConstant.PURCHASE_INFO_NAME,
                                           GameEngineConstant.TOWER_DAMAGE,
                                           GameEngineConstant.TOWER_ATTACK_SPEED,
                                           GameEngineConstant.TOWER_ATTACK_AMOUNT,
                                           GameEngineConstant.TOWER_RANGE,
                                           GameEngineConstant.TOWER_MAGIC,
                                           GameEngineConstant.TOWER_MAGIC_FACTOR,
                                           GameEngineConstant.TOWER_BOOST_FACTOR,
                                           GameEngineConstant.PURCHASE_INFO_COST,
                                           GameEngineConstant.PURCHASE_INFO_DESCRIPTION };
    private final String DISPLAY_COLOR = "black";
    private ItemOptionsDisplayer itemOptionsDisplayer;
    private PurchaseInfo storeItemInformation;

    /** Constructor 
     * @param itemOptionsDisplayer tool used to display options that can be performed on store item
     * @param storeItemInformation information about item the HoverAction is being created for
     */
    public StoreItemHoverAction (ItemOptionsDisplayer itemOptionsDisplayer, PurchaseInfo storeItemInformation) {
        this.itemOptionsDisplayer = itemOptionsDisplayer;
        this.storeItemInformation = storeItemInformation;
    }

    @Override
    /**
     * Packages the information to be displayed on the information Panel when
     * a user hovers over the button 
     */
    public void executeAction () {
        Collection<DisplayValue> display = createDisplayValues(storeItemInformation);
        itemOptionsDisplayer.displayStoreInformation(storeItemInformation.getInfo(), display);

    }

    /** Creates the Collection of DisplayValues to be used by the Information Display panel 
     * to display information about storeItemButton when a user hovers over it
     * @param storeItemInformation information about store item StoreItemHoverAction is being created for
     * @return Collection of DisplayValues that represents the data and formating of information to be displayed
     */
    private Collection<DisplayValue> createDisplayValues (final PurchaseInfo storeItemInformation) {
        Collection<DisplayValue> display = new ArrayList<DisplayValue>();
        for (String key : DISPLAY_KEYS) {
            if (storeItemInformation.getInfo().get(key) != null) {
                String field = key;
                String value = storeItemInformation.getInfo().get(key);
                String color = DISPLAY_COLOR;
                display.add(new DisplayValue(field, value, color));
            }
        }
        return display;
    }
}
