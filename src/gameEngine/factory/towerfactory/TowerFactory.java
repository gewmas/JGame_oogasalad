package gameEngine.factory.towerfactory;

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
    protected double recyclePrice;

    protected PurchaseInfo purchaseInfo;

    public TowerFactory (JSONObject currTower) {
        type = currTower.getString("type");
        id = currTower.getString("id");
        image = currTower.getString("image");
        damage = currTower.getDouble("damage");
        attackSpeed = currTower.getDouble("attackSpeed");
        range = currTower.getDouble("range");
        cost = currTower.getDouble("cost");
        recyclePrice = currTower.getDouble("recyclePrice");
        description = currTower.getString("description");

        this.purchaseInfo = new PurchaseInfo(type, id, image, description,(int)cost);
    }

    public void addDescription () {
        purchaseInfo.addToMap("Tower Type", type);
        purchaseInfo.addToMap("Tower Name", id);
        purchaseInfo.addToMap("Image", image);
        purchaseInfo.addToMap("Damage", String.valueOf(damage));
        purchaseInfo.addToMap("Attack Speed", String.valueOf(attackSpeed));
        purchaseInfo.addToMap("Range", String.valueOf(range));
        purchaseInfo.addToMap("Cost", String.valueOf(cost));
        purchaseInfo.addToMap("Sell Price", String.valueOf(recyclePrice));
        purchaseInfo.addToMap("Description", String.valueOf(description));
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
        return recyclePrice;
    }

    

}
