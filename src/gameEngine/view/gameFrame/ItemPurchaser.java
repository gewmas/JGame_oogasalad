package gameEngine.view.gameFrame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import jgame.JGPoint;
import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.ViewConstants;


public class ItemPurchaser {

    private String towerToPurchaseName;
    private GameFrame gameFrame;
    private PurchaseInfo towerToPurchase;
    private boolean purchasing;
    private ControllerToViewInterface controller;

    public ItemPurchaser (ControllerToViewInterface controller, GameFrame gameFrame) {
        this.controller = controller;
        this.gameFrame = gameFrame;
        purchasing = false;
        towerToPurchaseName = "";

    }

    /**
     * Indicates that the user wants to buy a tower
     */
    public void placeTower (PurchaseInfo purchaseInfo) {

        String towerName = purchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_NAME);

        if (towerName.equals(towerToPurchaseName)) {
            restoreDefaultCursor();

            towerToPurchase = null;
            towerToPurchaseName = null;
            purchasing = false;
            return;
        }

        setCursorImage(ViewConstants.IMAGE_PATH +
                       controller.getImageURL()
                               .get(purchaseInfo.getInfo()
                                       .get(GameEngineConstant.PURCHASE_INFO_IMAGE)));
        purchasing = true;
        towerToPurchase = purchaseInfo;
        towerToPurchaseName = purchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_NAME);
        // }
    }

    public boolean checkAndPlaceTower (JGPoint mousePosition) {
        boolean bought = false;
        if (purchasing) {
            bought = controller.purchaseObject(mousePosition.x, mousePosition.y, towerToPurchase);
            if (bought) {
                towerToPurchase = null;
                towerToPurchaseName = null;
                purchasing = false;
                restoreDefaultCursor();
            }
        }

        return bought;
    }

    public void setCursorImage (String imagePath) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image =
                toolkit.getImage(imagePath);
        Cursor c =
                toolkit.createCustomCursor(image,
                                           new Point(image.getWidth(null) / 2, image
                                                   .getHeight(null) / 2), "");
        gameFrame.setCursor(c);
    }

    public void restoreDefaultCursor () {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public boolean isPurchasing () {
        return purchasing;
    }

}
