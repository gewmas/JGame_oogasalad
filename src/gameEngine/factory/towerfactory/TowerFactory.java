package gameEngine.factory.towerfactory;

import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.Map;


public abstract class TowerFactory implements PurchaseInfo {

    String towerName;
    String description;
    String image;

    double x;
    double y;

    double damage;
    double attackSpeed;

    double range;

    double cost;
    double recyclePrice;

    Map<String, String> info;

    public TowerFactory (JSONObject currTower) {
        towerName = currTower.getString("id");
        image = currTower.getString("image");
        damage = currTower.getDouble("damage");
        attackSpeed = currTower.getDouble("attackSpeed");
        range = currTower.getDouble("range");
        cost = currTower.getDouble("cost");
        recyclePrice = currTower.getDouble("recyclePrice");
        description = currTower.getString("description");

        info = new HashMap<String, String>();
    }

    public void addDescription () {
        info.put("Tower Name", towerName);
        info.put("Image", image);
        info.put("Damage", String.valueOf(damage));
        info.put("Attack Speed", String.valueOf(attackSpeed));
        info.put("Range", String.valueOf(range));
        info.put("X", String.valueOf(x));
        info.put("Y", String.valueOf(y));
        info.put("Cost", String.valueOf(cost));
        info.put("Sell Price", String.valueOf(recyclePrice));
        info.put("Description", String.valueOf(description));
    }

    public abstract Tower create (int x, int y);

    @Override
    public String getItemName () {
        return towerName;
    }

    @Override
    public String getImage () {
        return image;
    }

    @Override
    public int getCost () {
        return (int) cost;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public double getDamage () {
        return damage;
    }

    @Override
    public double getAttackSpeed () {
        return attackSpeed;
    }

    @Override
    public double getRange () {
        return range;
    }

    @Override
    public double getRecyclePrice () {
        return recyclePrice;
    }

    @Override
    public Map<String, String> getInfo () {
        return null;
    }

}
