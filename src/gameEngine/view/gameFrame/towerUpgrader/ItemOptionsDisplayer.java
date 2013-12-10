package gameEngine.view.gameFrame.towerUpgrader;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import gameEngine.constant.GameEngineConstant;
import gameEngine.controller.ControllerToViewInterface;
import gameEngine.view.View;
import gameEngine.view.gameFrame.GameInitializable;
import gameEngine.view.gameFrame.GameUpdatable;
import gameEngine.view.gameFrame.gameObjects.RangeDisplay;
import gameEngine.view.gameFrame.tools.DisplayValue;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;


public class ItemOptionsDisplayer implements GameInitializable{
    private InfoDisplayPanel display;
    private RangeDisplay rangeDisplay;
    private List<TowerOptionButton> TowerUpgraderButtons;

    public ItemOptionsDisplayer (InfoDisplayPanel display, ControllerToViewInterface controller) {
        this.display = display;
        this.TowerUpgraderButtons = new ArrayList<TowerOptionButton>();
        TowerUpgraderButtons.add(new UpgradeButton(this, controller));
        TowerUpgraderButtons.add(new SellButton(this, controller));
        TowerUpgraderButtons.add(new AttackModeButton(this,controller));
        for (TowerOptionButton button : TowerUpgraderButtons) {
            display.add(button);
        }

    }

    public void clearDisplay () {
        this.display.clearDisplay();
        for (TowerOptionButton button : TowerUpgraderButtons) {
            button.setVisible(false);
        }
        rangeDisplay.suspend();
    }

    public void displayStoreInformation (Map<String, String> information,
                                         Collection<DisplayValue> displayValues) {
        clearDisplay ();
        updateDisplay(displayValues);

    }


    /**
     * Adds a circle around the tower that was clicked
     */
    public void createRangeDisplay () {
        this.rangeDisplay = new RangeDisplay("RangeDisplay", false, -1, -1, 256);
    }

    public void displayTowerInformation (Map<String, String> information,
                                           List<DisplayValue> display,
                                           int mouseX,
                                           int mouseY) {
        clearDisplay ();
        this.display.updateDisplayInformation( display);

        for (TowerOptionButton button : TowerUpgraderButtons) {
            button.setVisible(true);
        }
        
        if (!information.containsKey(GameEngineConstant.TOWER_ATTACK_MODE)){
            TowerUpgraderButtons.get(2).setVisible(false);
        }

        for (TowerOptionButton button : TowerUpgraderButtons) {
            button.setTowerPosition(information, mouseX, mouseY);
        }

        rangeDisplay.setTower(Double.parseDouble(information.get(GameEngineConstant.TOWER_RANGE)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_X)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_Y)));
        rangeDisplay.resume();
    }

    public void updateDisplay (Collection<DisplayValue> displayValues) {
        this.display.updateDisplayInformation( displayValues);
        
    }
    
    public void closeDisplay(){
        display.setVisible(false);
    }

    @Override
    public void initialize () {
        createRangeDisplay ();
        
    }

}
