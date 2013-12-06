package gameEngine.model.effect;

import jgame.JGObject;
import jgame.JGPoint;



public class Effect extends JGObject {
    protected String myGraphics = null;
    
    public Effect (
                   double x,
                   double y,
                   String gfxname,
                   String audio) {
       
        super("effect", true, 0, 0, 0, gfxname);
        JGPoint p=eng.getImageSize(gfxname);
        this.x= x-p.x/2;
        this.y=y=p.y/2;
        myGraphics = gfxname;
        if(audio!=null){
            eng.playAudio(audio);
        }
    }

    
}
