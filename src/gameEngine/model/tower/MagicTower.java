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
public class MagicTower extends Tower {

    private Detector<Enemy> detector;
    private double magicFactor;

    public MagicTower (double damage,
                       double attackSpeed,
                       double range,
                       double cost,
                       double recyclePrice,
                       String description,
                       
                       double magicFactor,

                       String name,
                       boolean unique_id,
                       double x,
                       double y,
                       int collisionid,
                       String image) {
        super(name, unique_id, x, y, collisionid, image);

        this.detector = new Detector<Enemy>(this.eng, Enemy.class);
        this.magicFactor = magicFactor;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        info.put("Magic Factor", String.valueOf(magicFactor));
    }

    @Override
    public void sell () {
        // TODO Auto-generated method stub

    }

    @Override
    public void upgrade () {

    }

    @Override
    public void downgrade(){

    }

    @Override
    public void upgrade(double factor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void downgrade(double factor) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAttackMode (int attackMode) {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, String> getInfo () {
        // TODO Auto-generated method stub
        return null;
    }

}
