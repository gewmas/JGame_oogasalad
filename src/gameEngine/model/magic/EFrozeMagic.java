package gameEngine.model.magic;


public class EFrozeMagic extends EMagic {

    public static final String NAME="FrozeMagic";
    private static final double FROZESPEEDCHANGE=1;
    
    public EFrozeMagic ( int expire,IEMagicable target,int ID, int CID) {
        super(expire,target,NAME,CID,NAME,ID);
        magicOn();
    }

    @Override
    void magicOnAction (IEMagicable target) {
        target.changeSpeed(-FROZESPEEDCHANGE);
    }

    @Override
    void magicOffAction (IEMagicable target) {
        target.changeSpeed(FROZESPEEDCHANGE);
    }



}
