package gameEngine.view.gameFrame.towerUpdater;

import java.util.Map;
import gameEngine.view.Button;


public abstract class TowerUpgraderButton extends Button {

    public TowerUpgraderButton (String label) {
        super(label);

    }

    public abstract void setTowerPosition (Map<String, String> information, int mouseX, int mouseY);
}
