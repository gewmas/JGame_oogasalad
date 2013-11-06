package gameEngine.view.store;

import javax.swing.ImageIcon;
import gameEngine.view.Button;
import gameEngine.view.Panel;

/**
 * @author lalitamaraj
 *Panel to hold the store used to purchase towers
 */
public class TowerStorePanel extends Panel {
    public TowerStorePanel(){
    ImageIcon icon = new ImageIcon("right.gif");
    InfoPanel ip = new InfoPanel();
    setOpaque(true);
    add(new Button("yo"));
    }
}
