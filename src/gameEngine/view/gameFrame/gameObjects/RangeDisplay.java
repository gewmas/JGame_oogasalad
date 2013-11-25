package gameEngine.view.gameFrame.gameObjects;

import jgame.JGColor;
import jgame.JGObject;

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
    
    public void setRange(double range){
        this.range=range;
    }
    
    @Override
    public void move(){
        
    }
    
    @Override
    public void paint(){
        eng.drawOval(x,y,range,range,false,true,4,JGColor.red);
    }

}
