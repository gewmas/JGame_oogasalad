package gameEngine.model.magic;

import jgame.JGObject;
import jgame.JGRectangle;

public class EArmourMagic extends Magic{
    public static final String NAME="ArmourMagic";
    private static final double ENLARGE=1.2;
    
    public EArmourMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        magicOn();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void magicOnAction () {
     JGRectangle box=((JGObject) myTarget).getBBox();
     this.setBBox(box.x, box.y, (int) (box.width*ENLARGE),(int) (box.height*ENLARGE));
    }
}
