package gameEngine.model.buff;

/*
 * wenxin shi
 */
public class PoisonBuff extends Buff {

    public static final int POISONBUFFID = 0;
    public static final int POISONBUFFCID = 0;
    public static final String NAME = "PoisonBuff";

    private double myAttack;
    private long myTime;

    public PoisonBuff (int expire, IBuffable target, double attack) {
        super(expire, target, NAME, POISONBUFFCID, NAME, POISONBUFFID);
        myAttack = attack;
    }

    public PoisonBuff (int expire, IBuffable target) {
        super(expire, target, NAME, POISONBUFFCID, NAME, POISONBUFFID);
        myAttack = 0.5;
    }

    @Override
    public void buffOn (IBuffable target) {
        myTime = System.currentTimeMillis();
    }

    @Override
    public void buffOff (IBuffable target) {
    }

    @Override
    public void move (IBuffable target) {
        long timeLapse = myTime - System.currentTimeMillis();
        if (expiry > 0)
            target.changeLife(-myAttack * timeLapse / (expiry * 1000));
    }

}
