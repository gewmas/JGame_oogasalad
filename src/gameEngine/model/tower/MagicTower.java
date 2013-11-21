package gameEngine.model.tower;

import java.util.Map;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;

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

                       String type,
                       String id,
                       boolean unique_id,
                       double x,
                       double y,
                       int collisionid,
                       String image) {
        super(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, 
              type, id, unique_id, x, y,
              collisionid, image);

        this.detector = new Detector<Enemy>(this.eng, Enemy.class);
        this.magicFactor = magicFactor;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        info.put("Magic Factor", String.valueOf(magicFactor));
    }

    
    @Override
    public void upgrade () {
        super.upgrade();
        this.magicFactor += 1.0;
    }

    @Override
    public void downgrade(){
        super.downgrade();
        this.magicFactor -= 1.0;
    }

}
