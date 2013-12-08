package gameEngine.model.magic;

import gameEngine.model.enemy.Enemy;

public class EPoisonMagic extends Magic{

    public static final String NAME = "PoisonMagic";
    private double myHealthLevel;

    public EPoisonMagic (int expire, IMagicable target, double poison, double healthBlood,int ID, int CID) {
        super(expire, target, null, NAME, CID, NAME, ID);
        magicOn();
        myHealthLevel=healthBlood;
        if(expire<0){
            myChangeRecord = ((IEMagicable) myTarget).changePercentLife(poison / expiry);
        }
        else
            myChangeRecord=poison;
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }

    public void move () {
        super.move();
        if(((Enemy) myTarget).getLife()>myHealthLevel)
            ((Enemy) myTarget).setImage("Healthy");
        else
            ((IEMagicable) myTarget).changeLife(myChangeRecord);
    }
}
