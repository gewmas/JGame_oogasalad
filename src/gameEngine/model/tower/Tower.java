package gameEngine.model.tower;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.magic.ITMagicable;
import gameEngine.model.purchase.PurchaseInfo;
import jgame.JGObject;


/**
 * @author Yuhua
 * 
 * Tower will shoot the Enemy within shooting range with Bullet
 */

public abstract class Tower extends JGObject implements ITMagicable {
//    private String type;
//    private String id;
//    private String image;

    protected double damage;
    protected double attackSpeed;

    protected double range;

    protected double x;
    protected double y;

    protected int cost;
    protected double upgradePrice;
    protected double recyclePrice;

    protected double upgradeFactor = 1.2;

    protected int currentMagic = 0;

    private String description;

    protected PurchaseInfo purchaseInfo;

    public Tower (String type,
                  String id,

                  double damage,
                  double attackSpeed,
                  double range,
                  int cost,
                  double recyclePrice,
                  String description,

                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname,

                  PurchaseInfo purchaseInfo) {
        super(id, unique_id, x, y, collisionid, gfxname);

//        this.type = type;
//        this.id = id;
//        this.image = gfxname;

        this.damage = damage;
        this.attackSpeed = attackSpeed;

        this.range = range;
        this.cost = cost;
        upgradePrice = (int) cost / 3;
        this.recyclePrice = recyclePrice;

        this.description = description;

        this.x = x;
        this.y = y;

        this.purchaseInfo = purchaseInfo;
    }

    public void addDescription () {
        purchaseInfo.addToMap(GameEngineConstant.TOWER_DAMAGE, String.valueOf(damage));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_SPEED, String.valueOf(attackSpeed));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_RANGE, String.valueOf(range));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_SELL_PRICE, String.valueOf(recyclePrice));
        purchaseInfo.addToMap(GameEngineConstant.PURCHASE_INFO_DESCRIPTION, String.valueOf(description));

        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_PRICE, String.valueOf(upgradePrice));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_DAMAGE,
                              String.valueOf((int) damage * upgradeFactor));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_ATTACK_SPEED,
                              String.valueOf(attackSpeed * upgradeFactor));
    }

    /**
     * Tower Function Method
     */
    public abstract void sell ();

    public abstract void upgrade ();

    public abstract void downgrade (); // when sell BoostTower, downgrade Tower in range

    public void setAttackMode (int mode) {
    } // not all tower need this, serve as public interface

    /**
     * TowerInfo Getter Method
     */
    public PurchaseInfo getPurchaseInfo () {
        return purchaseInfo;
    }

    /**
     * Magic Method
     */
    @Override
    public double getX () {
        return x;
    }

    @Override
    public double getY () {
        return y;
    }

    @Override
    public int getCurrentMagics () {
        return currentMagic;
    }

    @Override
    public void setCurrentMagic (int magic) {
        currentMagic = magic;
    }

}
