package gameEngine.model.magic;


public class EFrozeMagic extends Magic {
    public static final String NAME="FrozeMagic";
    private static final double FROZESPEEDCHANGE=1;
    
    public EFrozeMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,null,NAME,CID,NAME,ID);
        magicOn();
    }

    public void magicOnAction () {
        ((IEMagicable)myTarget).changeSpeed(-FROZESPEEDCHANGE);
    }

    public void magicOffAction () {
        ((IEMagicable)myTarget).changeSpeed(FROZESPEEDCHANGE);
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }





}
