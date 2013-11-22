package gameEngine.view.gameFrame.gameObjects;

import jgame.JGObject;

/**
 * 
 * @author alex
 * Slider to set frame rate proportional to slider position
 */
public class FrameRateSlider extends JGObject {

    protected double initial_x;
    
    public FrameRateSlider (String name,
                            boolean unique_id,
                            double x,
                            double y,
                            int collisionid,
                            String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        initial_x=x;
        this.resume_in_view=false;
    }
    
    @Override
    public void move(){
        if (this.eng.getMouseButton(1)){
            if (Math.abs(this.eng.getMouseX()-initial_x)<=100){
                this.setPos(this.eng.getMouseX(),y);
            }
            this.eng.setFrameRate((x-initial_x)+101, 2);
        }
    }

}
