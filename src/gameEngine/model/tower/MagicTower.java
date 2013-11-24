package gameEngine.model.tower;

import java.util.Map;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.purchase.PurchaseInfo;

/**
 * 
 * @author Yuhua
 *
 * Freeze Tower would slow down or other functionall enemies in range by slowFactor
 * The enemies get back normal speed when out of range
 * 
 */
public class MagicTower extends DefaultTower {

    private Detector<Enemy> detector;
    private double magicFactor;

    public MagicTower (double damage,
                       double attackSpeed,
                       int attackMode,
                       double range,
                       double cost,
                       double recyclePrice,
                       String description,
                       
                       double magicFactor,
                       int magic,

                       String type,
                       String id,
                       boolean unique_id,
                       double x,
                       double y,
                       int collisionid,
                       String image,
                       
                       PurchaseInfo purchaseInfo) {
        super(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, 
              type, id, unique_id, x, y,
              collisionid, image,
              purchaseInfo);

        this.detector = new Detector<Enemy>(this.eng, Enemy.class);
        this.magicFactor = magicFactor;
        this.currentMagic = magic;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap("Magic Factor", String.valueOf(magicFactor));

        purchaseInfo.addToMap("Upgrade Magic Factor", String.valueOf(magicFactor*upgradeFactor));

    }

    
    @Override
    public void upgrade () {
        upgrade(upgradeFactor);
    }

    @Override
    public void downgrade(){
        downgrade(upgradeFactor);
    }
    
    @Override
    public void upgrade (double factor) {
        this.magicFactor *= factor;
        super.upgrade(factor);
    }
    
    @Override
    public void downgrade (double factor) {
        this.magicFactor /= factor;
        super.downgrade(factor);
    }

}
