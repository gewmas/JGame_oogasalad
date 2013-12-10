package gameEngine.view.gameFrame.store;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;


/**
 * A button that represents an item that a user can purchase.
 * The user can click a button to purchase a store item
 * 
 * @author Lalita Maraj
 * 
 */
@SuppressWarnings("serial")
public class StoreItemButton extends JButton {

    /**
     * Constants for the UI features of button
     */
    private static final int ICON_SIZE = 32;
    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;

    /**
     * Boolean that indicates if a player can purchase this item.
     * Can be false for different reasons. Example: User does not have enough money.
     */
    private boolean ableToPurchase;
    private int cost;

    /**
     * Constructor
     * 
     * @param imagePath The path of the image to use as the icon of the store Button
     * @param itemPurchaseInfo information about item button represents
     * @param hoverExitAction defines behavior when button is exited
     * @param hoverAction defines behavior when button is hovered over
     * @param clickAction defines behavior when button is clicked
     */

    public StoreItemButton (String imagePath,
                            PurchaseInfo itemPurchaseInfo,
                            StoreButtonAction hoverExitAction,
                            StoreButtonAction hoverAction,
                            StoreButtonAction clickAction) {
        super("");
        setAbleToPurchase(false);
        setIconImage(imagePath);
        applyItemInformation(itemPurchaseInfo);
        setOpaque(true);
        this.addMouseAdapter(hoverAction, hoverExitAction, clickAction);
    }

    /**
     * Extracts the information givena about the item as a PurchaseInfo data
     * structure and applies it to the button attributes.
     * 
     * @param itemPurchaseInfo the datastructure that represents the item
     */
    private void applyItemInformation (PurchaseInfo itemPurchaseInfo) {
        String tempInt = itemPurchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_COST);
        this.cost = (int) Double.parseDouble(tempInt);
        setToolTipText(itemPurchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_NAME));
    }

    /**
     * Sets the user's ability to purchase this item.
     * If false, button is grayed out and not able to be purchased.
     * User can still view information about item in the information Panel
     * 
     * @param status
     */
    private void setAbleToPurchase (boolean status) {
        ableToPurchase = status;
        setEnabled(ableToPurchase);
    }

    /**
     * Assigns an icon to button based on the image path that
     * is provided.
     * 
     * @param urlImagePath URL of image path
     */
    private void setIconImage (String urlImagePath) {
        File file = new File(urlImagePath);
        Image myImage = null;
        try {
            myImage = ImageIO.read(file);
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        myImage = myImage.getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_FAST);
        this.setIcon(new ImageIcon(myImage));
    }

    /**
     * UI effects added to button to highlight the button from the other
     * items in the store
     * 
     */
    private void highlightButton () {
        this.setBackground(HOVER_BUTTON_COLOR);

    }

    /**
     * Removes highlight effects
     */
    private void unHighlightButton () {

        this.setBackground(null);

    }

    /**
     * Sets button to enabled/disabled depending on
     * whether user has enough money to purchase item
     * Public method because game Engine knows when to update and
     * has access to user's moneySupply
     * 
     * @param moneySupply user's money supply
     */
    public boolean toggleButtonActivation (int moneySupply) {
         setAbleToPurchase(moneySupply >= cost);
         return moneySupply >= cost;
    }

    /**
     * Adds a mouse adapter to button
     * 
     * @param hoverExitAction defines behavior when button is exited
     * @param hoverAction defines behavior when button is hovered over
     * @param clickAction defines behavior when button is clicked
     */
    private void addMouseAdapter (final StoreButtonAction hoverAction,
                                  final StoreButtonAction hoverExitAction,
                                  final StoreButtonAction clickAction) {
        addMouseListener(new MouseAdapter() {

            /**
             * A user can only purchase the item if the ableToPurchase
             * boolean is ture
             */
            public void mouseClicked (MouseEvent me) {
                if (ableToPurchase) {
                    clickAction.executeAction();
                }

            }

            public void mouseExited (MouseEvent me) {
                unHighlightButton();
                hoverExitAction.executeAction();
            }

            public void mouseEntered (MouseEvent me) {
                highlightButton();
                hoverAction.executeAction();
            }
        });

    }
}
