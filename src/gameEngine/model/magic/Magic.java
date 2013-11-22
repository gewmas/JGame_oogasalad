package gameEngine.model.magic;

import jgame.JGObject;


/*
 * wenxin shi
 */

public abstract class Magic extends JGObject {
    IMagicable myTarget;
    int myMagicId;
    IMagicable mySender;
    public Magic (int expire,
                 IMagicable target,
                 IMagicable sender,
                 String name,
                 int collisionid,
                 String gfxname,
                 int magicId
                 ) {
        super(name, true,target.getX(),target.getY(), collisionid, gfxname, expire);
        myTarget = target;
        mySender=sender;
        myMagicId=magicId;      
    }

    public void remove () {
        magicOff();
        super.remove();
    }

    public void move() {
        if (removeCondition()) {
            remove();
            return;
        }
        moveNextStep();
    }
    
    protected void moveNextStep (){
        this.x=myTarget.getX();
        this.y=myTarget.getY();
    }
    
    public void magicOn () {
        myTarget.setCurrentMagic(myTarget.getCurrentMagics()|myMagicId);
        magicOnAction();
        
    }

    public void magicOff () {
        myTarget.setCurrentMagic(myTarget.getCurrentMagics()&(~myMagicId));
        magicOffAction();
    }
    
    abstract boolean removeCondition();
    abstract void magicOnAction();
    abstract void magicOffAction();
    
}
