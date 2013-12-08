package gameEngine.view.gameFrame.towerUpdater;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import gameEngine.constant.GameEngineConstant;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameFrame;
import gameEngine.view.gameFrame.gameObjects.RangeDisplay;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;


public class TowerUpgrader {
    InfoDisplayPanel display;
    GameFrame gameFrame;
    RangeDisplay rangeDisplay;
    private Collection<TowerUpgraderButton> TowerUpgraderButtons;

    public TowerUpgrader (InfoDisplayPanel display, GameFrame gameFrame, View view) {
        this.display = display;
        this.gameFrame = gameFrame;

        this.TowerUpgraderButtons = new ArrayList();
        TowerUpgraderButtons.add(new UpgradeButton(this, view));
        TowerUpgraderButtons.add(new SellButton(this, view));
        for (TowerUpgraderButton button : TowerUpgraderButtons) {
            display.add(button);
        }

    }

    public void clearDisplay () {
        this.display.clearDisplay();
        for (TowerUpgraderButton button : TowerUpgraderButtons) {
            button.setVisible(false);
        }

        rangeDisplay.suspend();
    }

    public void displayStoreInformation (Map<String, String> information,
                                         Map<String, String> displayValues) {
        this.display.displayInformation(information, displayValues);

        for (TowerUpgraderButton button : TowerUpgraderButtons) {
            button.setVisible(false);
        }

        rangeDisplay.suspend();

    }

    public void displayCheckedInformation (Map<String, String> information,
                                           Map<String, String> display,
                                           int mouseX,
                                           int mouseY) {
        this.display.displayInformation(information, display);

        for (TowerUpgraderButton button : TowerUpgraderButtons) {
            button.setVisible(true);
        }

        for (TowerUpgraderButton button : TowerUpgraderButtons) {
            button.setTowerPosition(information, mouseX, mouseY);
        }

        rangeDisplay.setTower(Double.parseDouble(information.get(GameEngineConstant.TOWER_RANGE)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_X)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_Y)));
        rangeDisplay.resume();
    }

    public void updateDisplay (Map<String, String> toDisplay) {
        this.display.updateInformation(toDisplay);
    }

    public void createRangeDisplay () {
        this.rangeDisplay = new RangeDisplay("RangeDisplay", false, -1, -1, 256);
    }

}
