package gameEngine.model.effect;

import jgame.JGObject;
import jgame.JGPoint;



public class Effect extends JGObject {
    
    protected JGObject myObject= null;
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
