package gameEngine.model.tower;

import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import java.util.List;

/**
 * 
 * @author Yuhua
 *
 * BoostTower will boost several properties of Tower in range by boostFactor
 * Reverse back when the BoostTower is sold
 */

public class BoostTower extends Tower{

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

        info.put("Upgrade Boost Factor", String.valueOf(boostFactor*upgradeFactor));
    }

    //create magic to towers in range
    public void addBoostEffect(){
        List<Tower> towers = detector.getTargetsInRange((int)x, (int)y, (int)range);
        for(Tower target : towers){
            MagicsFactory.getInstance().createMagics(target, this, 2,target.getCurrentMagics());
        }
    }
    
    //wenxin shi every frame check weather there is new tower added into
    public void move(){
        super.move();
        addBoostEffect();
    }
    
    @Override
    public void sell () {
        remove();
    }

    @Override
    public void upgrade () {
        upgrade(upgradeFactor);
    }

    @Override
    public void downgrade(){
        downgrade(upgradeFactor);
    }

    //IMagicable Interface Method
    //BoostTower can't be boosted by other BoostTower
    @Override
    public void upgrade (double factor) {
        boostFactor *= factor;
        super.updateDescription();
    }
    
    @Override
    public void downgrade (double factor) {
        boostFactor /= factor;
        super.updateDescription();
    }

    
}
