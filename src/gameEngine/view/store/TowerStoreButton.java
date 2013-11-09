package gameEngine.view.store;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.Icon;

import gameEngine.controller.Controller;
import gameEngine.model.TowerInfo;
import gameEngine.view.Button;
import gameEngine.view.Mediator;


public class TowerStoreButton extends Button {

    private String name;

    private Mediator mediator;
    private TowerInfo towerInfo;


    public TowerStoreButton (TowerInfo tower, Mediator mediator,Controller controller) {
        super(trimName(tower.getName()));

        this.setSize(10, 100);
        this.name = tower.getName();


        this.mediator = mediator;
        this.towerInfo = tower;
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
        mediator.displayTowerInfo(towerInfo);

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
        mediator.placeTower(towerInfo);
      

    }   
    
    



}
