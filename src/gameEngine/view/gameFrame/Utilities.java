package gameEngine.view.gameFrame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Map;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.gameObjects.RangeDisplay;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;

public class Utilities {
    InfoDisplayPanel display;
    GameFrame gameFrame;
    UpgradeButton upgradeButton;
    SellButton sellButton;
    RangeDisplay rangeDisplay;
    public Utilities(InfoDisplayPanel display, GameFrame gameFrame, View view){
        this.display = display;
        this.gameFrame = gameFrame;
        this.upgradeButton=new UpgradeButton(this,view);
        this.sellButton=new SellButton(this,view);
        display.add(upgradeButton);
        display.add(sellButton);
    }
    
    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * @param displayInformation TODO
     */
    public void displayInformation (Map<String, String> displayInformation) {
        display.displayInformation(displayInformation);
        upgradeButton.setVisible(false);
        sellButton.setVisible(false);
    }

    public void clearDisplay(){
        this.display.clearDisplay();
        upgradeButton.setVisible(false);
        sellButton.setVisible(false);
        rangeDisplay.suspend();
    }
    public void displayStoreInformation (Map<String, String> information, Map<String, String> displayValues) {
        this.display.displayInformation(information, displayValues);
        upgradeButton.setVisible(false);
        sellButton.setVisible(false);
        rangeDisplay.suspend();
    }
    
    public void displayCheckedInformation (Map<String, String> information, Map<String, String> display,int mouseX, int mouseY) {
        this.display.displayInformation(information, display);
        upgradeButton.setVisible(true);
        sellButton.setVisible(true);
        upgradeButton.setTowerPosition(information,mouseX,mouseY);  
        sellButton.setTowerPosition(mouseX, mouseY);
        rangeDisplay.setTower(Double.parseDouble(information.get(GameEngineConstant.TOWER_RANGE)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_X)),
                              Double.parseDouble(information.get(GameEngineConstant.TOWER_Y)));
        rangeDisplay.resume();
    }
    
    public void updateDisplay (Map<String,String> toDisplay) {
        this.display.updateInformation(toDisplay);
    }
    
    public void setCursorImage(PurchaseInfo itemInformation){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("src/resources/img/" + itemInformation.getInfo().get("Image") + ".png");
        Cursor c = toolkit.createCustomCursor(image, new Point(image.getWidth(null)/2, image.getHeight(null)/2), "tower");
        gameFrame.setCursor(c);
    }
    
    public void restoreDefaultCursor() {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public void createRangeDisplay () {
        this.rangeDisplay=new RangeDisplay("RangeDisplay", false, -1, -1, 256);
    }
    
}
