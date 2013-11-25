package gameEngine.view.gameFrame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Map;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.View;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;

public class Utilities {
    InfoDisplayPanel display;
    GameFrame gameFrame;
    UpgradeButton upgradeButton;
    public Utilities(InfoDisplayPanel display, GameFrame gameFrame, View view){
        this.display = display;
        this.gameFrame = gameFrame;
        this.upgradeButton=new UpgradeButton(this,view);
        display.add(upgradeButton);
    }
    
    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * @param displayInformation TODO
     */
    public void displayInformation (Map<String, String> displayInformation) {
        display.displayInformation(displayInformation);
        upgradeButton.setVisible(false);
    }

    public void clearDisplay(){
        this.display.clearDisplay();
    }
    public void displayStoreInformation (Map<String, String> information, Map<String, String> displayValues) {
        this.display.displayInformation(information, displayValues);
        upgradeButton.setVisible(false);
        
    }
    
    public void displayCheckedInformation (Map<String, String> information, Map<String, String> display, int mouseX, int mouseY) {
        this.display.displayInformation(information, display);
        upgradeButton.setVisible(true);
        upgradeButton.setTowerPosition(mouseX,mouseY);        
    }
    
    public void updateDisplay (Map<String,String> toDisplay) {
        this.display.updateInformation(toDisplay);
    }
    
    public void setCursorImage(PurchaseInfo itemInformation){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("resources/img/" + itemInformation.getInfo().get("Image") + ".png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        gameFrame.setCursor(c);
        
    }
    
    public void restoreDefaultCursor() {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
}
