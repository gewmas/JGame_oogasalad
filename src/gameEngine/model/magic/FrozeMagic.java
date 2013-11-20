package gameEngine.model.magic;


public class FrozeMagic extends Magic {

    public static final String NAME="FrozeMagic";
    private static final double FROZESPEEDCHANGE=1;
    
    public FrozeMagic ( int expire,IMagicable target,int ID, int CID) {
        super(expire,target,NAME,CID,NAME,ID);
    }

    @Override
    public void magicOn (IMagicable target) {
        target.changeSpeed(-FROZESPEEDCHANGE);
    }

    @Override
    public void magicOff (IMagicable target) {
        target.changeSpeed(FROZESPEEDCHANGE);
    }

    @Override
    public void move (IMagicable target) {
       
    }
}
