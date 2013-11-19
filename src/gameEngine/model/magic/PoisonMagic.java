package gameEngine.model.magic;

/*
 * wenxin shi
 */
public class PoisonMagic extends Magic {
    public static final String NAME = "PoisonMagic";

    private double myPoisonStrength;
    private long myTime;

    public PoisonMagic (int expire, IMagicable target, double attack,int ID,int CID) {
        super(expire, target, NAME, CID, NAME, ID);
        myPoisonStrength = attack;
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
            target.changeLife(-myPoisonStrength * timeLapse / (expiry * 1000));
    }

}
