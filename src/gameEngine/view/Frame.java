package gameEngine.view;

import gameEngine.model.TowerInfo;
import javax.swing.JFrame;


/**
 * @author lalitamaraj
 *         Frame used to define the Frames in the GUI.
 *         The frame can interact with the view components so
 *         it implements the Colleague interface
 */
public class Frame extends JFrame implements Colleague {

    public Frame () {
        super();
    }

    @Override
    public void addMediator (Mediator mediator) {
        // TODO Auto-generated method stub

    }

    @Override
    public void placeTower (TowerInfo towerInfo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void displayTowerInfo (TowerInfo tower) {
        // TODO Auto-generated method stub

    }

    @Override
    public void purchaseTower () {
        // TODO Auto-generated method stub

    }

}
