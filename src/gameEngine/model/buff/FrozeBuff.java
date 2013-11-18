package gameEngine.model.buff;

public class FrozeBuff extends Buff {
    public static final int FROZEBUFFCID=0;
    public static final int FROZEBUFFID=0;
    public static final String NAME="FrozeBuff";
    private static final double FROZESPEEDCHANGE=1;
    
    public FrozeBuff ( int expire,IBuffable target) {
        super(expire,target,NAME,FROZEBUFFCID,NAME,FROZEBUFFID);
    }

    @Override
    public void buffOn (IBuffable target) {
        target.changeSpeed(-FROZESPEEDCHANGE);
    }

    @Override
    public void buffOff (IBuffable target) {
        target.changeSpeed(FROZESPEEDCHANGE);
    }

    @Override
    public void move (IBuffable target) {
        
    }

}
