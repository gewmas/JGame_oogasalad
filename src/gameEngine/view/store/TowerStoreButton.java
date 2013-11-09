package gameEngine.view.store;

import java.awt.Color;
import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Button;
import gameEngine.view.Mediator;


public class TowerStoreButton extends Button {

    private static final int MAX_BUTTON_TEXT_LENGTH = 5;
    private static final Color HOVER_BUTTON_COLOR = Color.BLUE;
    private static final Color HOVER_TEXT_COLOR = Color.RED;
    private static final Color HOVER_EXIT_TEXT_COLOR = Color.BLACK;
    private Mediator mediator;
    private TowerInfo towerInfo;

    public TowerStoreButton (TowerInfo tower, Mediator mediator, Controller controller) {
        super(trimName(tower.getName()));

        this.mediator = mediator;
        this.towerInfo = tower;
        setToolTipText(tower.getName());
        
        setOpaque(true);

    }

    @Override
    /**
     * Defines the behavior that will occur when the button
     * is entered. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseEnteteredAction () {
        this.setBackground(HOVER_BUTTON_COLOR);
        this.setForeground(HOVER_TEXT_COLOR);
        mediator.displayTowerInfo(towerInfo);

    }

    private static String trimName (String name) {
        if (name.length() > MAX_BUTTON_TEXT_LENGTH) { return name.substring(0,
                                                                            MAX_BUTTON_TEXT_LENGTH) +
                                                             "..."; }
        return name;
    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseExitedAction () {
        this.setBackground(null);
        this.setForeground(HOVER_EXIT_TEXT_COLOR);

    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseClickAction () {
        mediator.placeTower(towerInfo);

    }

}
