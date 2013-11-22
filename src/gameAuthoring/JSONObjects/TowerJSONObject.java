package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * Class that represents Tower in form of JSONObject
 * 
 *
 */
public class TowerJSONObject extends JSONObject {

    /**
     * Constructor for TowerJSONObject class
     * 
     * @param gameName Name of tower
     * @param imagePath Name of tower image
     * @param damage Damage each tower bullet inflicts
     * @param attackSpeed Tower attack speed
     * @param range Tower attack range
     * @param cost Cost in gold of purchasing tower
     * @param recyclePrice Gold gained by selling tower
     */
    public TowerJSONObject (String name,
                            String imagePath,
                            int damage,
                            int attackSpeed,
                            int range,
                            int cost,
                            int recyclePrice) {
        this.put("id", name);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        this.put("range", range);
        this.put("cost", cost);
        this.put("recyclePrice", recyclePrice);
    }

}
