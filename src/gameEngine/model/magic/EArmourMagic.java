package gameEngine.model.magic;

public class EArmourMagic extends Magic{
    public static final String NAME="ArmourMagic";
    public EArmourMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        magicOn();
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }
    
    public void magicOnAction () {
     //   ((IEMagicable)myTarget).changeSpeed(-FROZESPEEDCHANGE);
    }

    public void magicOffAction () {
       // ((IEMagicable)myTarget).changeSpeed(FROZESPEEDCHANGE);
    }

}
