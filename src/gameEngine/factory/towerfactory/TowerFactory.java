package gameEngine.factory.towerfactory;

import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;


public abstract class TowerFactory implements PurchaseInfo {

    String type;
    String id;
    
    String description;
    String image;

    double damage;
    double attackSpeed;

    double range;

    double cost;
    double recyclePrice;

    Map<String, String> info;

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

        info = new LinkedHashMap<String, String>();
    }

    public void addDescription () {
        info.put("Tower Type", type);
        info.put("Tower Name", id);
        info.put("Image", image);
        info.put("Damage", String.valueOf(damage));
        info.put("Attack Speed", String.valueOf(attackSpeed));
        info.put("Range", String.valueOf(range));
        info.put("Cost", String.valueOf(cost));
        info.put("Sell Price", String.valueOf(recyclePrice));
        info.put("Description", String.valueOf(description));
    }

    public abstract Tower create (int x, int y);

    /**
     * Implement PurchaseInfo
     */
    @Override
    public Map<String, String> getInfo () {
        return info;
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
