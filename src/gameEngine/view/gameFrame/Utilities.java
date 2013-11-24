package gameEngine.view.gameFrame;

import java.util.Map;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.view.gameFrame.tools.InfoDisplayPanel;

public class Utilities {
    InfoDisplayPanel display;
    GameFrame gameFrame;
    public Utilities(InfoDisplayPanel display, GameFrame gameFrame){
        this.display = display;
        this.gameFrame = gameFrame;
    }
    
    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * @param displayInformation TODO
     */
    public void displayInformation (Map<String, String> displayInformation) {
        display.displayInformation(displayInformation);
    }

    public void clearDisplay(){
        this.display.clearDisplay();
    }
    public void displayInformation (Map<String, String> information, Map<String, String> displayValues) {
        this.display.displayInformation(information, displayValues);
        
    }
    
    public void setCursorImage(PurchaseInfo towerInfo){
        gameFrame.placeTower(towerInfo);
    }
    
    public void restoreDefaultCursor() {
        gameFrame.restoreDefaultCursor();
    }
    
}
