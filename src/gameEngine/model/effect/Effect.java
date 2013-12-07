package gameEngine.model.effect;

import jgame.JGObject;
import jgame.JGPoint;



/**
 * Effect Class uses Decoration Pattern to allow user to add certain effect to JGObject
 * It will simplify huge extension hierarchy.
 * 
 * @author Jiaran
 */
public class Effect extends JGObject {
    
    protected JGObject myObject= null;
    /**
     * @param x: the x coordinate(center point to facilitate the user) where the effect will be presented
     * @param y: the y coordinate(center point to facilitate the user) where the effect will be presented
     * @param o: JGObject to be decorated 
     */
    public Effect (
                   double x,
                   double y,
                   JGObject o) {
       
        super("effect", true, 0, 0, 0, null);
        myObject=o;
        JGPoint p=eng.getImageSize(o.getImageName());
        System.out.println(myObject);
        myObject.x=x-p.x/2;
        myObject.y=y-p.y/2;
        this.x=myObject.x;
        this.y=myObject.y;
       
    }
    public Effect(JGObject o){
        super("effect", true, 0, 0, 0, null);
        myObject=o;
    }

    
}
