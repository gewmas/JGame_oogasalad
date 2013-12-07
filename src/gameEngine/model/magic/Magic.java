package gameEngine.model.magic;

import gameEngine.constant.GameEngineConstant;
import jgame.JGObject;
import jgame.JGRectangle;


/*
 * wenxin shi
 */

public abstract class Magic extends JGObject {
    IMagicable myTarget;
    int myMagicId;
    IMagicable mySender;
    
    double pathX;
    double pathY;
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
        JGRectangle box=this.getImageBBox();
        
        pathX=myTarget.getX();
        pathY=myTarget.getY();
        
        this.x=pathX-(box.width-GameEngineConstant.PIXELSPERTILE)/2;
        this.y=pathY-(box.height-GameEngineConstant.PIXELSPERTILE);
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
    public void magicOnAction(){};
    public void magicOffAction(){};
    
}
