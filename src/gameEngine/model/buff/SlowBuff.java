package gameEngine.model.buff;
/*
 * wenxin shi
 */
public class SlowBuff extends Buff {
    public static final int SLOWBUFFID = 0;
    public static final int SLOWBUFFCID=0;
    public static String NAME="SlowBuff";
    private double mySlowStrength;

    
    public SlowBuff(int expire,IBuffable target){
        super(expire, target, NAME, SLOWBUFFCID, NAME, SLOWBUFFID);
        mySlowStrength =0.5;
    }
    
    public SlowBuff (int expire, IBuffable target, double strength) {
        super(expire, target, NAME, SLOWBUFFCID, NAME, SLOWBUFFID);
        mySlowStrength = strength;
    }


    @Override
    public void buffOn (IBuffable target) {
        target.changeSpeed(mySlowStrength);
    }

    @Override
    public void buffOff (IBuffable target) {
        target.changeSpeed(-mySlowStrength);
    }

    @Override
    public void move (IBuffable target) {
        this.x = target.getX();
        this.y = target.getY();
    }

}
