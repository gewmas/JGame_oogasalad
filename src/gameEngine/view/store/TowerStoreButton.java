package gameEngine.view.store;

import java.awt.Color;
import javax.swing.Icon;
import gameEngine.view.Button;


public class TowerStoreButton extends Button {
    private InfoPanel infoPanel;

    private String name;
    private String description;
    private String power;
    private String cost;

    public TowerStoreButton (String name, InfoPanel infoPanel, Icon towerGraphic,
                             String cost,
                             String description,
                             String power) {
        super(name);
        this.infoPanel = infoPanel;
        this.setSize(10, 100);
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.power = power;
        setToolTipText(name);
        setOpaque(true);
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
        infoPanel.update(getDisplayInfo());

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

    /**
     * Defines the behavior that will occur when the button
     * is exited. If Button class is extened,
     * this method should be overridden to define this behavior.
     */
    protected void mouseClickAction () {
        System.out.println("READY TO BUY" + name);

    }

    private String getDisplayInfo () {
        String initialText = "<html>" +
                             name + "<ul>" +

                             "<li><font color=red>Cost: </font>" + cost + "</li>" +
                             "<li><font color=blue>Description: </font>" + description + "</li>" +
                             "<li><font color=green>Power: </font>" + power + "</li>";

        return initialText;
    }

}
