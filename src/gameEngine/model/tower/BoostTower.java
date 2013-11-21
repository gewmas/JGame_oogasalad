package gameEngine.model.tower;

import java.util.Map;
import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;

/**
 * 
 * @author Yuhua
 *
 * BoostTower will boost several properties of Tower in range by boostFactor
 * Reverse back when the BoostTower is sold
 */

public class BoostTower extends Tower {

    private Detector<DefaultTower> detector;
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

        this.detector = new Detector<DefaultTower>(this.eng, DefaultTower.class);
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
    public void magicUpgrade(double factor) {
        boostFactor *= factor;
    }

    public void magicDowngrade(double factor) {
        boostFactor /= factor;
    }
}
