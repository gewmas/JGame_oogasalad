package gameEngine.model.magic;

public class FrozeMagic extends Magic {
    public static final int FROZEMAGICCID=0;
    public static final int FROZEMAGICID=0;
    public static final String NAME="FrozeMagic";
    private static final double FROZESPEEDCHANGE=1;
    
    public FrozeMagic ( int expire,IMagicable target) {
        super(expire,target,NAME,FROZEMAGICCID,NAME,FROZEMAGICID);
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
