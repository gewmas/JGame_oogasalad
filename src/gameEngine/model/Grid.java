package gameEngine.model;

import jgame.JGObject;
/**
 * 
 * @author Harris
 * 
 * Each grid square will be a Grid object
 *
 */
public class Grid extends JGObject {
    
    private boolean onPath;
    private double xPos, yPos;
        
    public Grid(String name, boolean unique_id, double x, double y, int collisionid, String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        onPath = false;
        xPos = x;
        yPos = y;
    }
    
    public void setOnPath() {
        onPath = true;
    }
    
    public boolean isOnPath() {
        return onPath;
    }
    
    public double getXPos() {
        return xPos;
    }
    
    public double getYPos() {
        return yPos;
    }
    
}
