package gameEngine.model.magic;

/*
 * wenxin shi
 */
public class PoisonMagic extends Magic {

    public static final int POISONMAGICID = 0;
    public static final int POISONMAGICCID = 0;
    public static final String NAME = "PoisonMagic";

    private double myAttack;
    private long myTime;

    public PoisonMagic (int expire, IMagicable target, double attack) {
        super(expire, target, NAME, POISONMAGICCID, NAME, POISONMAGICID);
        myAttack = attack;
    }

    public PoisonMagic (int expire, IMagicable target) {
        super(expire, target, NAME, POISONMAGICCID, NAME, POISONMAGICID);
        myAttack = 0.5;
    }

    @Override
    public void magicOn (IMagicable target) {
        myTime = System.currentTimeMillis();
    }

    @Override
    public void magicOff (IMagicable target) {
    }

    @Override
    public void move (IMagicable target) {
        long timeLapse = myTime - System.currentTimeMillis();
        if (expiry > 0)
            target.changeLife(-myAttack * timeLapse / (expiry * 1000));
    }

}
