package gameEngine.view.gameFrame.gameObjects;

import jgame.JGColor;
import jgame.JGObject;
import jgame.JGPoint;

/**
 * 
 * @author alex
 * Draws a circle around a Tower to indicate its range
 */
public class RangeDisplay extends JGObject {
    
    double range;
    
    public RangeDisplay (String name,
                            boolean unique_id,
                            double x,
                            double y,
                            int collisionid) {
        super(name, unique_id, x, y, collisionid, null);
        this.resume_in_view=false;
        this.suspend();
    }
    
    @Override
    public void move(){
        
    }
    
    @Override
    public void paint(){
        eng.drawOval(x,y,range,range,false,true,4,JGColor.red);
    }

    public void setTower (double range,int mouseX, int mouseY) {
        this.range=range;
        JGPoint tileCoords=eng.getTileCoord(eng.getTileIndex(mouseX, mouseY));
        this.setPos(tileCoords.x+tilewidth/2, tileCoords.y+tileheight/2);
    }

}