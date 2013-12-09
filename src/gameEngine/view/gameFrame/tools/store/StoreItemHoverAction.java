package gameEngine.view.gameFrame.tools.store;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.tools.DisplayValue;
import gameEngine.view.gameFrame.towerUpdrader.ItemOptionsDisplayer;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Lalita Maraj
 *         A StoreButtonAction that implements the behavior that would happen when
 *         a user hovers over an item over the store
 */
public class StoreItemHoverAction implements StoreButtonAction {

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
    private PurchaseInfo storeItem;

    public StoreItemHoverAction (ItemOptionsDisplayer itemOptionsDisplayer, PurchaseInfo storeItem) {
        this.itemOptionsDisplayer = itemOptionsDisplayer;
        this.storeItem = storeItem;
    }

    @Override
    /**
     * Packages the information to be displayed on the information Panel when
     * a user hovers over the button 
     */
    public void executeAction () {
        List<DisplayValue> display = createDisplayValues(storeItem);
        itemOptionsDisplayer.displayStoreInformation(storeItem.getInfo(), display);

    }

    private List<DisplayValue> createDisplayValues (final PurchaseInfo storeItem) {
        List<DisplayValue> display = new ArrayList<DisplayValue>();
        for (String key : DISPLAY_KEYS) {
            if (storeItem.getInfo().get(key) != null) {
                String field = key;
                String value = storeItem.getInfo().get(key);
                String color = DISPLAY_COLOR;
                display.add(new DisplayValue(field, value, color));
            }
        }
        return display;
    }
}
