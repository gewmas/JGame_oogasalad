package gameEngine.view.gameFrame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import jgame.JGPoint;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;


public class ItemPurchaser {

    private String towerToPurchaseName;
    private GameFrame gameFrame;
    private PurchaseInfo towerToPurchase;
    private boolean purchasing;
    private View view;

    public ItemPurchaser (View view, GameFrame gameFrame) {
        this.view = view;
        this.gameFrame = gameFrame;
        purchasing = false;
        towerToPurchaseName = "";

    }

    /**
     * Indicates that the user wants to buy a tower
     */
    public void placeTower (PurchaseInfo purchaseInfo) {
        // if (purchasing) {
        // setBGColor(JGColor.red);
        String towerName = purchaseInfo.getInfo().get("Name");

        
        if (towerName.equals(towerToPurchaseName)) {
            restoreDefaultCursor();
            
            towerToPurchase = null;
            towerToPurchaseName = null;
            purchasing = false;
            return;
        }
        ;
        setCursorImage("src/resources/img/"+view.getStoreImages().get(purchaseInfo.getInfo().get("Image")));
        purchasing = true;
        towerToPurchase = purchaseInfo;
        towerToPurchaseName = purchaseInfo.getInfo().get("Name");
        // }
    }

    public boolean checkAndPlaceTower (JGPoint mousePosition) {
        if (purchasing) {
            if (view.buyTower(mousePosition.x, mousePosition.y, towerToPurchase)) {
                towerToPurchase = null;
                towerToPurchaseName = null;
                purchasing = false;
                restoreDefaultCursor();
            }
            System.out.println(towerToPurchase);
        }

        return purchasing;
    }

    public void setCursorImage (String imagePath) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image =
                toolkit.getImage(imagePath);
        Cursor c =
                toolkit.createCustomCursor(image,
                                           new Point(image.getWidth(null) / 2, image
                                                   .getHeight(null) / 2), "tower");
        gameFrame.setCursor(c);
    }

    public void restoreDefaultCursor () {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

}
