package temporary.buff;

import jgame.JGObject;
import temporary.TemporaryEnemy;


public abstract class Buff extends JGObject {
    private TemporaryEnemy myEnemy;

    public Buff (String name,
                 boolean unique_id,
                 double x,
                 double y,
                 int collisionid,
                 String gfxname,int expire,
                 TemporaryEnemy enemy) {
        super(name, unique_id, x, y, collisionid, gfxname,expire);
        myEnemy = enemy;
        
        myEnemy.addBuff(this);
        buffOn(myEnemy);

    }

    public void remove () {
        myEnemy.removeBuff(this);
        buffOff(myEnemy);
        super.remove();
    }
    
    public void move(){
        this.x=myEnemy.x-10;
        this.y=myEnemy.y;
    }

    abstract public void buffOn (TemporaryEnemy enemy);
    abstract public void buffOff (TemporaryEnemy enemy); 

}
