package gameEngine.model.effect;

import jgame.JGObject;



public class Effect extends JGObject {
    protected String myGraphics = null;
    
    public Effect (
                   double x,
                   double y,
                   String gfxname,
                   String audio) {
        super("effect", true, x, y, 0, gfxname);
        myGraphics = gfxname;
        if(audio!=null){
            eng.playAudio(audio);
        }
    }

    
}
