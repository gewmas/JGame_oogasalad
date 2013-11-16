package gameEngine.view.gameFrame.store;

import java.awt.Color;
import javax.swing.ImageIcon;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.Button;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * A button that represents a tower that a user can purchase.
 * When hovered over, the tower information is displayed on the GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class StoreItemButton extends Button implements TowerInfoFields{

    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;
    private GameFrameMediator mediator;
    private PurchaseInfo towerInfo;
    private Boolean active;
    private Boolean storeOpen;
    private String towerDisplayInfo;

    /**
     * @param tower the tower info data structure of the tower the button represents
     * @param mediator facilitates communication between view components
     * @param view facilitates communication between view and controller
     */
    public StoreItemButton (PurchaseInfo tower, GameFrameMediator mediator, View view) {
        super("");
        storeOpen = false;
        active = false;
        this.setEnabled(false);
        ImageIcon icon = new ImageIcon("resources/img/" + tower.getImage() + ".png");
        this.setIcon(icon);
        this.mediator = mediator;
        this.towerInfo = tower;
        setToolTipText(tower.getItemName());
        this.towerDisplayInfo = formatTowerDisplayInformation();
        setOpaque(true);

    }
    private String formatTowerDisplayInformation(){
        String ret = "<html><ul>";
        ret+= TOWER + towerInfo.getItemName();
        ret+= COST + towerInfo.getCost();
        ret+= DESCRIPTION + towerInfo.getDescription();
        ret+= DAMAGE + towerInfo.getDamage();
        
        ret+= ATTACKSPEED+ towerInfo.getAttackSpeed();
        ret+= ATTACKMODE+ towerInfo.getAttackMode();
        ret+= RANGE+ towerInfo.getRange();
        ret+= RECYCLE + towerInfo.getRecyclePrice();
       
        return ret;
    }
    
 
    @Override
    /**
     * When the button is hovered over, the tower information 
     * is displayed and the button's foreground and background colors are changed.
     */
    protected void mouseEnteteredAction () {
        if (storeOpen) {
            this.setBackground(HOVER_BUTTON_COLOR);
            this.setForeground(HOVER_TEXT_COLOR);

            mediator.displayTowerInfo(towerDisplayInfo);
        }
    }

    /**
     * When the cursor moves away from the button,
     * the button reverts back to its orignal background
     * and foreground
     */
    protected void mouseExitedAction () {
        if (storeOpen) {
            this.setBackground(null);
            this.setForeground(HOVER_EXIT_TEXT_COLOR);
        }

    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. When button is clicked, that allowers users to place a tower.
     * 
     */
    protected void mouseClickAction () {
        if (active) {
            mediator.placeTower(towerInfo);
        }
    }

    public void toggleButtonActivation (int moneySupply) {
        if (storeOpen == false) {
            storeOpen = true;
        }

        active = moneySupply >= towerInfo.getCost();
        setEnabled(active);

    }

}
