package gameEngine.model.magic;

import jgame.JGObject;


/*
 * wenxin shi
 */

public abstract class Magic extends JGObject {
    IMagicable myTarget;
    int myCurrMagicIds;
    public Magic (int expire,
                 IMagicable target,
                 String name,
                 int collisionid,
                 String gfxname,
                 int magicIds
                 ) {
        super(name, true,target.getX(),target.getY(), collisionid, gfxname, expire);
        myTarget = target;
        myCurrMagicIds=magicIds;      
    }

    public void remove () {
        magicOff();
        super.remove();
    }

    public void move() {
        if (!myTarget.isAlive()) {
            remove();
            return;
        }
        moveNextStep();
    }
    
    protected void moveNextStep (){
        this.x=myTarget.getX();
        this.y=myTarget.getY();
    }

    public abstract void magicOn ();

    public abstract void magicOff ();
    


}
