package gameEngine.model.magic;
/*
 * wenxin shi
 */
public class SlowMagic extends Magic {
    public static String NAME="SlowMagic";
    private double mySlowStrength;

    
    public SlowMagic (int expire, IMagicable target, double strength,int ID,int CID) {
        super(expire, target, NAME, CID, null, ID);
        mySlowStrength = strength;
    }


    @Override
    public void magicOn (IMagicable target) {
        target.changeSpeed(mySlowStrength);
    }

    @Override
    public void magicOff (IMagicable target) {
        target.changeSpeed(-mySlowStrength);
    }

    @Override
    public void move (IMagicable target) {
        this.x = target.getX();
        this.y = target.getY();
    }

}
