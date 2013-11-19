package gameEngine.model.magic;
/*
 * wenxin shi
 */
public class SlowMagic extends Magic {
    public static final int SLOWMAGICID = 0;
    public static final int SLOWMAGICCID=0;
    public static String NAME="SlowMagic";
    private double mySlowStrength;

    
    public SlowMagic(int expire,IMagicable target){
        super(expire, target, NAME, SLOWMAGICCID, NAME, SLOWMAGICID);
        mySlowStrength =0.5;
    }
    
    public SlowMagic (int expire, IMagicable target, double strength) {
        super(expire, target, NAME, SLOWMAGICCID, NAME, SLOWMAGICID);
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
