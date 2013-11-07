package gameEngine.view.store;

import java.awt.Color;
import javax.swing.Icon;
import gameEngine.view.Button;


public class TowerStoreButton extends Button {
    InfoPanel infoPanel;
    String label;

    public TowerStoreButton (String label, InfoPanel infoPanel, Icon towerGraphic) {
        super(label, towerGraphic);
        this.infoPanel = infoPanel;
        this.setSize(100, 100);
        this.label = label;
        // setOpaque(true);

    }

    @Override
    /**
     * Defines the behavior that will occur when the button
     * is entered. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseEnteteredAction () {
        this.setBackground(Color.CYAN);
        this.setForeground(Color.GREEN);
        infoPanel.update(label);

    }

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseExitedAction () {
        this.setBackground(Color.RED);
        this.setForeground(Color.BLUE);

    }

}
