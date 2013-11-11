package gameEngine.view.gameFrame.store;

import java.awt.Color;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.Button;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.GameFrameMediator;


/**
 * A button that represents a tower that a user can purchase.
 * When hovered over, the tower information is displayed on the GUI
 * 
 * @author Lalita Maraj
 * 
 */
public class TowerStoreButton extends Button {

    private static final int MAX_BUTTON_TEXT_LENGTH = 6;
    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;
    private GameFrameMediator mediator;
    private TowerFactory towerInfo;
    private Boolean active;

    /**
     * @param towerInfo the tower info data structure of the tower the button represents
     * @param mediator facilitates communication between view components
     * @param gameFrame facilitates communication between view and controller
     */
    public TowerStoreButton (TowerFactory towerInfo, GameFrameMediator mediator, GameFrame gameFrame) {
        super(trimName(towerInfo.getName()));
        active = true; // to be changed!
        this.setEnabled(true);
        this.mediator = mediator;
        this.towerInfo = towerInfo;
        setToolTipText(towerInfo.getName());

        setOpaque(true);

    }

    @Override
    /**
     * When the button is hovered over, the tower information 
     * is displayed and the button's foreground and background colors are changed.
     */
    protected void mouseEnteteredAction () {

        this.setBackground(HOVER_BUTTON_COLOR);
        this.setForeground(HOVER_TEXT_COLOR);

        mediator.displayTowerInfo(towerInfo);

    }

    /**
     * Trims the name of the button to meet size requirements if necessary
     * 
     * @param name name of tower
     * @return trimmed name
     */
    private static String trimName (String name) {
        if (name.length() > MAX_BUTTON_TEXT_LENGTH) { return name.substring(0,
                                                                            MAX_BUTTON_TEXT_LENGTH) +
                                                             "..."; }
        return name;
    }

    /**
     * When the cursor moves away from the button,
     * the button reverts back to its orignal background
     * and foreground
     */
    protected void mouseExitedAction () {

        this.setBackground(null);
        this.setForeground(HOVER_EXIT_TEXT_COLOR);

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
        active = moneySupply >= towerInfo.getCost();
        setEnabled(active);

    }

}
