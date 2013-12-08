package gameEngine.model.effect;

import jgame.JGObject;

/**
 * FadeEffect makes a JGObject to fade and when it is totally transparent,
 * it is removed.
 * @author Jiaran
 */
public class FadeEffect extends Effect {
    private boolean isDown=false; 
    public FadeEffect (String effectName, double x, double y, JGObject o) {
        super(effectName, x, y, o);
        myObject.alpha=0.1f;
    }

    public void move () {
        if (!isDown) {
            myObject.alpha+=0.025;
            if (myObject.alpha >= 1) {
                myObject.alpha = 1;
                isDown = true;
            }
        }
        else {
            myObject.alpha-=0.025;
            if(myObject.alpha<=0){
                myObject.remove();
                remove();
            }

        }
            
        

    }
}
