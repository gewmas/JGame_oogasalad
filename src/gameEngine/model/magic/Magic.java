package gameEngine.model.magic;

import jgame.JGObject;


/*
 * wenxin shi
 */

public abstract class Magic extends JGObject {
    private IMagicable myTarget;
    private int myMagicId;

    public Magic (int expire,
                 IMagicable target,
                 String name,
                 int collisionid,
                 String gfxname, 
                 int magicId
                 ) {
        super(name, true, target.getX(), target.getY(), collisionid, gfxname, expire);

        myMagicId = magicId;
        myTarget = target;

        magicOn(myTarget);
    }

    public void remove () {
        magicOff(myTarget);
        myTarget.changeCurrentMagics(myTarget.getCurrentMagics() & (~myMagicId));
        super.remove();
    }

    public void move () {
        if (((myTarget.getCurrentMagics() & myMagicId) == 0) || myTarget == null) {
            remove();
            return;
        }
        move(myTarget);
    }

    public abstract void magicOn (IMagicable target);

    public abstract void magicOff (IMagicable target);

    public abstract void move (IMagicable target);
}
