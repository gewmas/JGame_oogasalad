package gameEngine.model.buff;

import jgame.JGObject;


/*
 * wenxin shi
 */

public abstract class Buff extends JGObject {
    private IBuffable myTarget;
    private int myBuffId;

    public Buff (int expire,
                 IBuffable target,
                 String name,
                 int collisionid,
                 String gfxname, 
                 int buffId
                 ) {
        super(name, true, target.getX(), target.getY(), collisionid, gfxname, expire);

        myBuffId = buffId;
        myTarget = target;

        buffOn(myTarget);
    }

    public void remove () {
        buffOff(myTarget);
        myTarget.changeCurrentBuffs(myTarget.getCurrentBuffs() & (~myBuffId));
        super.remove();
    }

    public void move () {
        if (((myTarget.getCurrentBuffs() & myBuffId) == 0) || myTarget == null) {
            remove();
            return;
        }
        move(myTarget);
    }

    public abstract void buffOn (IBuffable target);

    public abstract void buffOff (IBuffable target);

    public abstract void move (IBuffable target);
}
