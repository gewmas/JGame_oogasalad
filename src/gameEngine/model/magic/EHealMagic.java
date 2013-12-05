package gameEngine.model.magic;

public class EHealMagic extends Magic {
    public static final String NAME = "HealMagic";

    public EHealMagic (int expire, IMagicable target, double heal, int ID, int CID) {
        super(expire, target, null, NAME, CID, NAME, ID);
        magicOn();
        myChangeRecord = ((IEMagicable) myTarget).changePercentLife(heal / expiry);
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }

    public void move () {
        super.move();
        ((IEMagicable) myTarget).changeLife(myChangeRecord);
    }

}
