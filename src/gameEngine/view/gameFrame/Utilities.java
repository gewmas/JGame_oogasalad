package gameEngine.view.gameFrame;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
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
    
    public void setCursorImage(PurchaseInfo itemInformation){
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = toolkit.getImage("resources/img/" + itemInformation.getImage() + ".png");
        Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "tower");
        gameFrame.setCursor(c);
        
    }
    
    public void restoreDefaultCursor() {
        gameFrame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
    
}
