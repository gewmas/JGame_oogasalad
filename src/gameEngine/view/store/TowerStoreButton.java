package gameEngine.view.store;

import javax.swing.Icon;
import gameEngine.view.Button;

public class TowerStoreButton extends Button {
    InfoPanel infoPanel;
    public TowerStoreButton (String label, InfoPanel infoPanel,Icon towerGraphic ) {
        super(label,towerGraphic);
        this.infoPanel  = infoPanel;
        this.setSize(100, 100);
        
    }

}
