package gameEngine.view.gameFrame.tools.store;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.ViewConstants;


/**
 * A button that represents an item that a user can purchase.
 * When hovered over, the item information is displayed on the GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class StoreItemButton extends JButton {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;
    private Boolean active;
    private int cost;

    /**
     * @param itemPurchaseInfo information about item buttom represents
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
        active = false;
        this.setEnabled(false);

        setIconImage(imagePath);

        String tempInt = itemPurchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_COST);
        this.cost = (int) Double.parseDouble(tempInt);
        setToolTipText(itemPurchaseInfo.getInfo().get(GameEngineConstant.PURCHASE_INFO_NAME));
        setOpaque(true);
        this.addMouseAdapter(hoverAction, hoverExitAction, clickAction);
    }

    private void setIconImage (String imagePath) {
        File file = new File(imagePath);
        Image myImage = null;
        try {
            myImage = ImageIO.read(file);
        }
        catch (IOException e) {

            e.printStackTrace();
        }
        myImage = myImage.getScaledInstance(32,32, Image.SCALE_FAST);
        this.setIcon(new ImageIcon(myImage));
    }

    /**
     * When the button is hovered over, the item information
     * is displayed and the button's foreground and background colors are changed.
     */
    private void highlightButton () {
        this.setBackground(HOVER_BUTTON_COLOR);
        this.setForeground(HOVER_TEXT_COLOR);
    }

    /**
     * When the cursor moves away from the button,
     * the button reverts back to its orignal background
     * and foreground
     */
    private void unHighlightButton () {

        this.setBackground(null);
        this.setForeground(HOVER_EXIT_TEXT_COLOR);

    }

    /**
     * Sets button to enables/disabled depending on
     * whether user has enough money to purchase item
     * 
     * @param moneySupply user's money supply
     */
    public void toggleButtonActivation (int moneySupply) {
        active = moneySupply >= cost;
        setEnabled(active);
    }

    /**
     * Adds a mouse adapter to button
     * 
     * @param hoverExitAction defines behavior when button is exited
     * @param hoverAction defines behavior when button is hovered over
     * @param clickAction defines behavior when button is clicked
     */
    void addMouseAdapter (final StoreButtonAction hoverAction,
                          final StoreButtonAction hoverExitAction,
                          final StoreButtonAction clickAction) {
        addMouseListener(new MouseAdapter() {
            public void mouseClicked (MouseEvent me) {
                if (active) {
                    clickAction.executeAction();
                }

            }

            public void mouseExited (MouseEvent me) {
                unHighlightButton();

            }

            public void mouseEntered (MouseEvent me) {
                highlightButton();
                hoverAction.executeAction();
            }
        });

    }
}
