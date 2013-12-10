package gameEngine.model.magic;

import gameEngine.model.enemy.Enemy;


public class EPoisonMagic extends Magic {

    public static final String NAME = "PoisonMagic";
    private double myHealthLevel;
    private double increment;
    
    public EPoisonMagic (int expire,
                         IMagicable target,
                         double poison,
                         double healthBlood,
                         int ID,
                         int CID) {
        super(expire, target, null, NAME, CID, NAME, ID);
        magicOn();
        myHealthLevel = healthBlood;
        if (expire < 0) {
            myChangeRecord = ((IEMagicable) myTarget).changePercentLife(poison / expiry);
        }
        else myChangeRecord = poison;
        this.alpha = 0.8f;
        
    }

    @Override
    boolean removeCondition () {
        return !myTarget.isAlive();
    }

    public void move () {
        super.move();
        if (((Enemy) myTarget).getLife() > myHealthLevel){
           this.setImage("Healthy");   
        }
        else {
            ((IEMagicable) myTarget).changeLife(myChangeRecord);
            alpha += increment;
            if (this.alpha > 0.8)
                increment = -0.01;
            else if (this.alpha < 0.3)
                increment = 0.01;
        }
    }
}
