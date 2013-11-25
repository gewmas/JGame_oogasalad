package gameEngine.model.tower;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.GameInfo;
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

        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_AMOUNT, String.valueOf(attackAmount));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_ATTACK_AMOUNT, String.valueOf(attackAmount*upgradeFactor));
    }

    @Override
    public void upgrade (GameInfo gameInfo) {
        upgrade(upgradeFactor);
        gameInfo.loseGold(upgradePrice);
    }

    @Override
    public void downgrade(){
        downgrade(upgradeFactor);
    }

    @Override
    public void upgrade (double factor) {
        this.attackAmount *= factor;
        super.upgrade(factor);
        addDescription();
    }

    @Override
    public void downgrade (double factor) {
        this.attackAmount /= factor;
        super.downgrade(factor);
        addDescription();
    }

}
