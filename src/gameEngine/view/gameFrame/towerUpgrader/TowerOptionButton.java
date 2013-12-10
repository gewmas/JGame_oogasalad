package gameEngine.view.gameFrame.towerUpgrader;

import java.util.Map;
import javax.swing.JButton;


public abstract class TowerOptionButton extends JButton {

    public TowerOptionButton (String label) {
        super(label);

    }

    public abstract void setTowerPosition (Map<String, String> information, int mouseX, int mouseY);
}
