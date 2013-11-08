package gameEngine.view.store;

import java.awt.Color;
import javax.swing.Icon;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Button;
import gameEngine.view.Mediator;


public class TowerStoreButton extends Button {

    private String name;
    private String description;
    private int cost;
    private Mediator mediator;

    public TowerStoreButton (String name, Icon towerGraphic,
                             int cost,
                             String description,
                             String power,
                             Mediator mediator, Controller controller) {
        super(trimName(name));

        this.setSize(10, 100);
        this.name = name;
        this.cost = cost;
        this.description = description;

        this.mediator = mediator;
        setToolTipText(name);
        setOpaque(true);
       
    }

    public TowerStoreButton (TowerInfo tower, Mediator mediator,Controller controller) {
        super(trimName(tower.getName()));

        this.setSize(10, 100);
        this.name = tower.getName();
        this.cost = tower.getCost();
        this.description = tower.getDescription();

        this.mediator = mediator;
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
        mediator.displayTowerInfo(getDisplayInfo());

    }

    private static String trimName (String name) {
        if (name.length() > 5) { return name.substring(0, 5) + "..."; }
        return name;
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
        mediator.purchaseTower(name);

    }

    private String getDisplayInfo () {
        String initialText = "<html>" +
                             name + "<ul>" +

                             "<li><font color=red>Cost: </font>" + cost + "</li>" +
                             "<li><font color=blue>Description: </font>" + description + "</li></html>";
                            

        return initialText;
    }

}
