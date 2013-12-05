package gameEngine.model.magic;

import gameEngine.model.enemy.Enemy;
import jgame.JGObject;
import jgame.JGRectangle;

public class EArmourMagic extends Magic{
    public static final String NAME="ArmourMagic";
    private double myTargetLife;
    public EArmourMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        magicOn();
        myTargetLife=((Enemy) myTarget).getLife();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void move(){
        super.move();
        double temp=((Enemy) myTarget).getLife();
        if(temp>myTargetLife){
            myTargetLife=temp;     
        }
        ((Enemy) myTarget).setLife((int)myTargetLife);
    }
}
