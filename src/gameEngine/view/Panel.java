package gameEngine.view;

import gameEngine.model.TowerInfo;
import java.awt.BorderLayout;
import javax.swing.JPanel;


/**
 * @author lalitamaraj
 *         Panels used to divide the different sections
 *         of the GUI. Panels implement the Colleague interface because they
 *         are view components that can interact with other view componetns.
 * 
 */
public class Panel extends JPanel implements GameFrameColleague {
    public Panel () {
        super();
    }

    @Override
    public void placeTower (TowerInfo towerInfo) {

    }

    @Override
    public void displayTowerInfo (TowerInfo tower) {

    }

    @Override
    public void purchaseTower () {

    }

    @Override
    public void updateStoreStatus () {
        // TODO Auto-generated method stub

    }

}
