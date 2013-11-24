package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public abstract class TowerFactory {

    protected String type;
    protected String id;
    
    protected String description;
    protected String image;

    protected double damage;
    protected double attackSpeed;

    protected double range;

    protected double cost;
    protected double sellPrice;

    protected PurchaseInfo purchaseInfo;

    public TowerFactory (JSONObject currTower) {
        type = currTower.getString(Constant.PURCHASE_INFO_TYPE);
        id = currTower.getString(Constant.PURCHASE_INFO_NAME);
        image = currTower.getString(Constant.PURCHASE_INFO_IMAGE);
        damage = currTower.getDouble(Constant.TOWER_DAMAGE);
        attackSpeed = currTower.getDouble(Constant.TOWER_ATTACK_SPEED);
        range = currTower.getDouble(Constant.TOWER_RANGE);
        cost = currTower.getDouble(Constant.PURCHASE_INFO_COST);
        sellPrice = currTower.getDouble(Constant.TOWER_SELL_PRICE);
        description = currTower.getString(Constant.PURCHASE_INFO_DESCRIPTION);

        this.purchaseInfo = new PurchaseInfo(type, id, image, description,(int)cost);
    }

    public void addDescription () {
        purchaseInfo.addToMap(Constant.TOWER_DAMAGE, String.valueOf(damage));
        purchaseInfo.addToMap(Constant.TOWER_ATTACK_SPEED, String.valueOf(attackSpeed));
        purchaseInfo.addToMap(Constant.TOWER_RANGE, String.valueOf(range));
        purchaseInfo.addToMap(Constant.TOWER_SELL_PRICE, String.valueOf(sellPrice));
    }

    public abstract Tower create (int x, int y);

    /**
     * Implement PurchaseInfo
     */
    public PurchaseInfo getPurchaseInfo () {
        return purchaseInfo;
    }
    
    @Deprecated
    public String getItemName () {
        return id;
    }

    @Deprecated
    public String getImage () {
        return image;
    }

    @Deprecated
    public int getCost () {
        return (int) cost;
    }

    @Deprecated
    public String getDescription () {
        return description;
    }

    @Deprecated
    public double getDamage () {
        return damage;
    }

    @Deprecated
    public double getAttackSpeed () {
        return attackSpeed;
    }

    @Deprecated
    public double getRange () {
        return range;
    }

    @Deprecated
    public double getRecyclePrice () {
        return sellPrice;
    }

    

}
