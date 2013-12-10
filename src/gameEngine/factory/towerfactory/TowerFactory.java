package gameEngine.factory.towerfactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * 
 * @author Yuhua Fabio
 *
 * TowerFactory can create different types of Tower when called by the create() method
 * 
 */
public abstract class TowerFactory {

    protected String type;
    protected String id;

    protected String description;
    protected String image;

    protected double damage;
    protected double attackSpeed;

    protected double range;

    protected int cost;
    protected double sellPrice;

    protected PurchaseInfo purchaseInfo;

    public TowerFactory (JSONObject currTower) {
        type = currTower.getString(GameEngineConstant.PURCHASE_INFO_TYPE);
        id = currTower.getString(GameEngineConstant.PURCHASE_INFO_NAME);
        image = currTower.getString(GameEngineConstant.PURCHASE_INFO_IMAGE);
        damage = currTower.getDouble(GameEngineConstant.TOWER_DAMAGE);
        attackSpeed = currTower.getDouble(GameEngineConstant.TOWER_ATTACK_SPEED);
        range = currTower.getDouble(GameEngineConstant.TOWER_RANGE);
        cost = currTower.getInt(GameEngineConstant.PURCHASE_INFO_COST);
        sellPrice = currTower.getDouble(GameEngineConstant.TOWER_SELL_PRICE);
        description = currTower.getString(GameEngineConstant.PURCHASE_INFO_DESCRIPTION);

        purchaseInfo = new PurchaseInfo(type, id, image, description, cost);
    }

    public void addDescription () {
        purchaseInfo.addToMap(GameEngineConstant.TOWER_RANGE, String.valueOf(range));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_SELL_PRICE, String.valueOf(sellPrice));
    }

    public abstract Tower create (int x, int y);

    /**
     * Implement PurchaseInfo
     */
    public PurchaseInfo getPurchaseInfo () {
        return purchaseInfo;
    }

    public int getCost () {
        return cost;
    }


}
