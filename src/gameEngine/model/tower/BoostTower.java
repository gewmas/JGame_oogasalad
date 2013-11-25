package gameEngine.model.tower;

import gameEngine.constant.GameEngineConstant;
import gameEngine.factory.magicFactory.MagicsFactory;
import gameEngine.model.Detector;
import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;
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
                       int cost,
                       double recyclePrice,
                       String description,
                       
                       double boostFactor,

                       String type,
                       String id,
                       boolean unique_id,
                       double x,
                       double y,
                       int collisionid,
                       String image,
                       
                       PurchaseInfo purchaseInfo) {
        super(type, id, damage, attackSpeed, range, cost, recyclePrice, description,
              unique_id, x, y, collisionid, image,
              purchaseInfo);

        this.detector = new Detector<Tower>(this.eng, Tower.class);
        this.boostFactor = boostFactor;
        
        addDescription();
        addBoostEffect();
        
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(GameEngineConstant.TOWER_BOOST_FACTOR, df.format(boostFactor));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_BOOST_FACTOR, df.format(boostFactor*upgradeFactor));
        
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
    public void upgrade (GameInfo gameInfo) {
        upgrade(upgradeFactor);
        gameInfo.loseGold(upgradePrice);
    }

    public void downgrade(){
        downgrade(upgradeFactor);
    }

    //IMagicable Interface Method
    //BoostTower can't be boosted by other BoostTower
    @Override
    public void upgrade (double factor) {
        boostFactor *= factor;
        addDescription();
    }
    
    @Override
    public void downgrade (double factor) {
        boostFactor /= factor;
        addDescription();
    }

    
}
