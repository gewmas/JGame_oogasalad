package gameEngine.view;

import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.view.gameFrame.GameFrameColleague;
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
    public void placeTower (TowerFactory towerInfo) {

    }

    @Override
    public void displayTowerInfo (TowerFactory tower) {

    }

    @Override
    public void purchaseTower () {

    }

}
