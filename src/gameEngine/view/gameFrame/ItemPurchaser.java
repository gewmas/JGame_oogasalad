package gameEngine.view.gameFrame;

import jgame.JGPoint;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;


public class ItemPurchaser {

    private String towerToPurchaseName;
    private Utilities utilities;
    private PurchaseInfo towerToPurchase;
    private boolean purchasing;
    private View view;

    public ItemPurchaser (View view, Utilities utilities) {
        this.view = view;
        this.utilities = utilities;
        purchasing = false;
        towerToPurchaseName = "";

    }

    /**
     * Indicates that the user wants to buy a tower
     */
    public void placeTower (PurchaseInfo purchaseInfo) {
        //if (purchasing) {
            // setBGColor(JGColor.red);
            String towerName = purchaseInfo.getInfo().get("Name");

            System.out.println(towerName);
            if (towerName.equals(towerToPurchaseName)) {
                utilities.restoreDefaultCursor();
                System.out.println("Tower cancelled");
                towerToPurchase = null;
                towerToPurchaseName=null;
                purchasing = false;
                return;
            }
            utilities.setCursorImage(purchaseInfo);
            purchasing = true;
            towerToPurchase = purchaseInfo;
            towerToPurchaseName = purchaseInfo.getInfo().get("Name");
        //}
    }

    public boolean checkAndPlaceTower (JGPoint mousePosition) {
        if (purchasing) {
            if (view.buyTower(mousePosition.x, mousePosition.y, towerToPurchase)) {
                towerToPurchase = null;
                towerToPurchaseName=null;
                purchasing = false;
                utilities.restoreDefaultCursor();
            }
            System.out.println(towerToPurchase);
        }
        
        return purchasing;
    }
    
}
