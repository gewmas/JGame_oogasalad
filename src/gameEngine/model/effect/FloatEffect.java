package gameEngine.model.effect;

import jgame.JGObject;

public class FloatEffect extends Effect {
    private final double FLOAT_SPEED=0.1;
    public FloatEffect (double x, double y, JGObject o) {
        super(x, y, o);
        
    }
    public FloatEffect(JGObject o){
        super(o);
    }
    public void move(){
        myObject.y-=FLOAT_SPEED;
    }
    
}
