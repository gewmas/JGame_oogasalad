package gameEngine.model.tower;

import java.util.List;
import java.util.Map;
import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.factory.magicFactory.TBoostFactory;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.magic.IMagicable;

/**
 * 
 * @author Yuhua
 *
 * BoostTower will boost several properties of Tower in range by boostFactor
 * Reverse back when the BoostTower is sold
 */

public class BoostTower extends Tower implements IMagicable{

    private Detector<Tower> detector;
    private double boostFactor;

    public BoostTower (double damage,
                       double attackSpeed,
                       double range,
                       double cost,
                       double recyclePrice,
                       String description,
                       
                       double boostFactor,

                       String type,
                       String id,
                       boolean unique_id,
                       double x,
                       double y,
                       int collisionid,
                       String image) {
        super(type, id, damage, attackSpeed, range, cost, recyclePrice, description,
              unique_id, x, y, collisionid, image);

        this.detector = new Detector<Tower>(this.eng, Tower.class);
        this.boostFactor = boostFactor;
        
        addDescription();
        addBoostEffect();
    }

    public void addDescription(){
        super.addDescription();
        info.put("Boost Factor", String.valueOf(boostFactor));
    }

    //create magic to towers in range
    public void addBoostEffect(){
        List<Tower> towers = detector.getTargetsInRange((int)x, (int)y, (int)range);
        for(Tower target : towers){
            MagicsFactory.getInstance().createTowerMagics(target, this, 2);
        }
    }
    
    @Override
    public void sell () {
        remove();
    }

    @Override
    public void upgrade () {
        boostFactor += 1.0;
    }

    @Override
    public void downgrade(){
        boostFactor -= 1.0;
    }

    //IMagicable Interface Method
    @Override
    public void upgrade (double factor) {
        boostFactor *= factor;
    }
    
    @Override
    public void downgrade (double factor) {
        boostFactor /= factor;
    }

    
}
