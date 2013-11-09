package gameEngine.view;

import gameEngine.model.TowerInfo;
import java.awt.BorderLayout;
import javax.swing.JPanel;


/**
 * @author lalitamaraj
 *         Panels used to divide the different sections
 *         of the GUI.
 */
public class Panel extends JPanel implements Colleague {
    public Panel () {
        super(new BorderLayout());
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
