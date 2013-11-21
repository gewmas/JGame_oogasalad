package gameEngine.view.gameFrame;

import java.util.Map;
import gameEngine.model.purchase.PurchaseInfo;


public interface ItemPurchaser {

    public void setCursorImage (PurchaseInfo towerInfo);

    public void displayTowerInfo (Map<String, String> towerDisplayInfo);

    public void exitPurchase ();


    public void updateStoreStatus ();

    public void clearDisplay ();

    public void openStore ();
}
