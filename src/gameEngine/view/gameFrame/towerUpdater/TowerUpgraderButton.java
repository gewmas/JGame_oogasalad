package gameEngine.view.gameFrame.towerUpdater;

import java.util.Map;
import javax.swing.JButton;


public abstract class TowerUpgraderButton extends JButton {

    public TowerUpgraderButton (String label) {
        super(label);

    }

    public abstract void setTowerPosition (Map<String, String> information, int mouseX, int mouseY);
}
