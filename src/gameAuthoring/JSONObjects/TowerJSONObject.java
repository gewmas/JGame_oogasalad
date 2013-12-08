package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * Class that represents Tower in form of JSONObject
 * 
 * 
 */
public class TowerJSONObject extends JSONObject {
    // TODO: REFACTOR THIS

    /**
     * Constructor for TowerJSONObject class
     * 
     * @param type Type of tower
     * @param name Name of tower
     * @param imagePath Name of tower image
     * @param damage Damage each tower bullet inflicts
     * @param attackSpeed Tower attack speed
     * @param attackMode Tower attack mode
     * @param range Tower attack range
     * @param cost Cost in gold of purchasing tower
     * @param recyclePrice Gold gained by selling tower
     * @param description Brief description of tower behavior
     */
    public TowerJSONObject (String type,
                            String name,
                            String imagePath,
                            int damage,
                            double attackSpeed,
                            int attackMode,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description) {

        this.put("type", type);
        this.put("id", name);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        this.put("attackMode", attackMode);
        this.put("range", range);
        this.put("cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("description", description);
    }

    //MultipleShootingTower
    public TowerJSONObject (String type,
                            String name,
                            String imagePath,
                            int damage,
                            double attackSpeed,
                            int attackMode,
                            int attackAmount,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description) {

        this.put("type", type);
        this.put("id", name);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        this.put("attackMode", attackMode);
        this.put("attackAmount", attackAmount);
        this.put("range", range);
        this.put("cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("description", description);
    }
    
    //BoostTower
    public TowerJSONObject (String type,
                            String name,
                            String imagePath,
                            int damage,
                            double attackSpeed,
                            //int attackMode, //Not in JSON
                            int range,
                            int cost,
                            int recyclePrice,
                            String description,
                            double boostFactor) {

        this.put("type", type);
        this.put("id", name);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        //this.put("attackMode", attackMode);
        this.put("range", range);
        this.put("cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("description", description);
        this.put("boostFactor", boostFactor);
    }
    
    //MagicTower
    public TowerJSONObject (String type,
                            String name,
                            String imagePath,
                            int damage,
                            double attackSpeed,
                            int attackMode,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description,
                            double magicFactor,
                            String magic) {

        this.put("type", type);
        this.put("id", name);
        this.put("image", imagePath);
        this.put("damage", damage);
        this.put("attackSpeed", attackSpeed);
        this.put("attackMode", attackMode);
        this.put("range", range);
        this.put("cost", cost);
        this.put("recyclePrice", recyclePrice);
        this.put("description", description);
        this.put("magicFactor", magicFactor);
        JSONArray magicArray = new JSONArray();
        magicArray.put(magic);
        this.put("magic", magicArray);
    }
    

}
