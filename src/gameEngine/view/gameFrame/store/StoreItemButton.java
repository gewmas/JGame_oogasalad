package gameEngine.view.gameFrame.store;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
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
public class StoreItemButton extends Button {

    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;
    private GameFrameMediator mediator;
    private PurchaseInfo towerInfo;
    private Boolean active;
    private Boolean storeOpen;
    private Map<String, String> towerDisplayInfo;

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

        setOpaque(true);
        towerDisplayInfo = new HashMap();
        formatTowerDisplayInformation();
        this.addMouseAdapter();
    }

    private void formatTowerDisplayInformation () {
        towerDisplayInfo.put("Tower", towerInfo.getItemName());
        towerDisplayInfo.put("Cost", Double.toString(towerInfo.getCost()));
        towerDisplayInfo.put("Description", towerInfo.getDescription());
        towerDisplayInfo.put("Damage", Double.toString(towerInfo.getDamage()));
        towerDisplayInfo.put("Attack Speed", Double.toString(towerInfo.getAttackSpeed()));
        towerDisplayInfo.put("Attack Mode", Double.toString(towerInfo.getAttackMode()));
        towerDisplayInfo.put("Range", Double.toString(towerInfo.getRange()));
        towerDisplayInfo.put("Recycle Price", Double.toString(towerInfo.getRecyclePrice()));

    }

 
    /**
     * When the button is hovered over, the tower information 
     * is displayed and the button's foreground and background colors are changed.
     */
    private  void highlightButton () {
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
    private void unHighlightButton () {

        this.setBackground(null);
        this.setForeground(HOVER_EXIT_TEXT_COLOR);

        mediator.clearDisplay();

    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. When button is clicked, that allowers users to place a tower.
     * 
     */
    private void placeTower() {
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
    private void addMouseAdapter(){
        addMouseListener(new MouseAdapter() { 
            public void mouseClicked(MouseEvent me) { 
                placeTower();
            } 
            public void mouseExited(MouseEvent me){
                unHighlightButton();
            }
            public void mouseEntered(MouseEvent me){
                highlightButton();
            }
          }); 

    }
}
