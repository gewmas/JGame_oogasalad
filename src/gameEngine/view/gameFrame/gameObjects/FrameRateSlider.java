package gameEngine.view.gameFrame.gameObjects;

import jgame.JGObject;

/**
 * 
 * @author alex
 * Slider to set frame rate proportional to slider position
 */
public class FrameRateSlider extends JGObject {

    protected double initial_x,initial_y;
    
    public FrameRateSlider (String name,
                            boolean unique_id,
                            double x,
                            double y,
                            int collisionid,
                            String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        initial_x=x;
        initial_y=y;
        this.resume_in_view=false;
    }
    
    @Override
    public void move(){
        if (this.eng.getMouseButton(1) && eng.getMouseY() > initial_y){
            if (Math.abs(this.eng.getMouseX()-initial_x)<=100){
                this.setPos(this.eng.getMouseX()-this.getBBox().width/2,y);
            }
            System.out.println("Setting framerate to:");
            System.out.println(Math.pow(2, (x-initial_x)/25)*30);
            this.eng.setFrameRate(Math.pow(2,(x-initial_x)/25)*30, 2);
        }
    }

}
