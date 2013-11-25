package gameEngine.model.tower;

import gameEngine.Constant.Constant;
import gameEngine.model.purchase.PurchaseInfo;

public class MultipleShootingTower extends DefaultTower {

    public MultipleShootingTower (double damage,
                                  double attackSpeed,
                                  int attackMode,
                                  int attackAmount,
                                  double range,
                                  int cost,
                                  double recyclePrice,
                                  String description,
                                  
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
        
        this.attackAmount = attackAmount;
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        
        purchaseInfo.addToMap(Constant.TOWER_ATTACK_AMOUNT, String.valueOf(attackAmount));
        purchaseInfo.addToMap(Constant.TOWER_UPGRADE_ATTACK_AMOUNT, String.valueOf(attackAmount*upgradeFactor));
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
        this.attackAmount *= factor;
        super.upgrade(factor);
    }
    
    @Override
    public void downgrade (double factor) {
        this.attackAmount /= factor;
        super.downgrade(factor);
    }

}
