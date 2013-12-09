package gameEngine.view.gameFrame.towerUpdrader;

import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.View;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;


public class SellButton extends TowerUpgraderButton {
    private static final String NAME = "Sell Tower";
    private static final int BUTTON_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 20;

    private ControllerToViewInterface controller;
    private int towerX, towerY;
    private ItemOptionsDisplayer towerUpgrader;

    public SellButton (ItemOptionsDisplayer utility, ControllerToViewInterface controller_in) {
        super(NAME);
        this.controller = controller_in;
        this.towerUpgrader = utility;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                controller.sellTower(towerX, towerY);
                towerUpgrader.clearDisplay();
            }
        });
        this.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        this.setVisible(false);
        towerX = -1;
        towerY = -1;
    }

    public void setTowerPosition (Map<String, String> information, int mouseX, int mouseY) {
        towerX = mouseX;
        towerY = mouseY;
    }

}
