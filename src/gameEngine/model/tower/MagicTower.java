package gameEngine.model.tower;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.GameInfo;
import gameEngine.model.purchase.PurchaseInfo;


/**
 * 
 * @author Yuhua
 * 
 *         Freeze Tower would slow down or other functionall enemies in range by slowFactor
 *         The enemies get back normal speed when out of range
 * 
 */
public class MagicTower extends DefaultTower {

    private double magicFactor;

    public MagicTower (double damage,
                       double attackSpeed,
                       int attackMode,
                       int specialty,
                       double range,
                       int cost,
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
        super(damage, attackSpeed, attackMode, specialty, range, cost, recyclePrice, description,
              type, id, unique_id, x, y,
              collisionid, image,
              purchaseInfo);

        this.magicFactor = magicFactor;
        currentMagic = magic;

        addDescription();
    }

    @Override
    public void addDescription () {
        super.addDescription();
        purchaseInfo.addToMap(GameEngineConstant.TOWER_MAGIC_FACTOR, df.format(magicFactor));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_MAGIC_FACTOR,
                              df.format(magicFactor * upgradeFactor));
    }

    @Override
    public void upgrade (GameInfo gameInfo) {
        upgrade(upgradeFactor);
        gameInfo.loseGold(upgradePrice);
    }

    @Override
    public void downgrade () {
        downgrade(upgradeFactor);
    }

    @Override
    public void upgrade (double factor) {
        magicFactor *= factor;
        super.upgrade(factor);
        addDescription();
    }

    @Override
    public void downgrade (double factor) {
        magicFactor /= factor;
        super.downgrade(factor);
        addDescription();
    }

}
