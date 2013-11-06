package temporary;

import jgame.JGObject;

public class TemporaryEnemy extends JGObject {

    public TemporaryEnemy (String name,
                           boolean unique_id,
                           double x,
                           double y,
                           int collisionid,
                           String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        this.xspeed=10;
        
    }
    @Override
    public void move(){
        super.move();
    }
    
    public void increaseSpeed(){
        this.xspeed++;
    }

}
