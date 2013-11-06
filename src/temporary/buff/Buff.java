package temporary.buff;

import jgame.JGObject;
import temporary.TemporaryEnemy;


public abstract class Buff extends JGObject {
    private IBuffable myTarget;

    public Buff (String name,
                 boolean unique_id,
                 double x,
                 double y,
                 int collisionid,
                 String gfxname,int expire,
                 IBuffable target) {
        super(name, unique_id, x, y, collisionid, gfxname,expire);
        myTarget = target;
    
        buffOn(myTarget);

    }

    public void remove () {
        buffOff(myTarget);
        super.remove();
    }
    
    public void move(){
        this.x=myTarget.getX()-10;
        this.y=myTarget.getY()-10;
    }

    abstract public void buffOn (IBuffable target);
    abstract public void buffOff (IBuffable target); 

}
