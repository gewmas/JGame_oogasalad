package gameEngine.model.magic;

import gameEngine.constant.GameEngineConstant;
import jgame.JGObject;
import jgame.JGRectangle;

/**
 * 
 * @author wenxin
 *
 */

public class TBoostMagic extends Magic{
    public static final String NAME="BoostMagic";
    private double myFactor;
    double alphIncrement;
    public TBoostMagic (IMagicable target, IMagicable sender,double factor,int ID, int CID) {
        super(-1, target,sender ,NAME,CID,NAME,ID);
        myFactor=factor;
        magicOn();
    }
    
   public void magicOnAction () {
        ((ITMagicable)myTarget).downgrade(myFactor);
    }
    public void magicOffAction () {
        ((ITMagicable)myTarget).upgrade(myFactor);
    }
    @Override
    boolean removeCondition () {
        return !myTarget.isAlive()||!mySender.isAlive();
    }
    
    public void moveNextStep (){                       
        if(alpha>0.99){
            alphIncrement=-0.01;
        }
        else if(alpha<0.2){
           alphIncrement=0.01; 
        }
        alpha+=alphIncrement;
        
        double x=myTarget.getX();
        double y=myTarget.getY();
        JGRectangle boxTarget=((JGObject) myTarget).getBBox();
        JGRectangle box=this.getBBox();
        this.x=x-(box.width-boxTarget.width)/2;
        this.y=y-(box.height-boxTarget.height);
    }

    
}
