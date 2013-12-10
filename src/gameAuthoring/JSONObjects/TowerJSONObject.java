package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
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
     * @param type Type of tower
     * @param name Name of tower
     * @param imageID Name of tower image
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
                            String imageID,
                            int damage,
                            double attackSpeed,
                            int attackMode,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description) {

        this.put("Type", type);
        this.put("Name", name);
        this.put("Image", imageID);
        this.put("Damage", damage);
        this.put("Attack Speed", attackSpeed);
        this.put("Attack Mode", attackMode);
        this.put("Range", range);
        this.put("Cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("Description", description);
    }


    /**
     * Constructor for Multiple Shooting Tower
     * 
     * @param type Type of tower
     * @param name Name of tower
     * @param imageID ID of tower image
     * @param damage Damage each tower bullet inflicts
     * @param attackSpeed Tower attack speed
     * @param attackMode Tower attack mode
     * @param attackAmount Tower attack amount
     * @param range Tower attack range
     * @param cost Cost in gold of purchasing tower
     * @param recyclePrice Gold gained by selling tower
     * @param description Brief description of tower behavior
     */
    public TowerJSONObject (String type,
                            String name,
                            String imageID,
                            int damage,
                            double attackSpeed,
                            int attackMode,
                            int attackAmount,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description) {

        this.put("Type", type);
        this.put("Name", name);
        this.put("Image", imageID);
        this.put("Damage", damage);
        this.put("Attack Speed", attackSpeed);
        this.put("Attack Mode", attackMode);
        this.put("Attack Amount", attackAmount);
        this.put("Range", range);
        this.put("Cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("Description", description);
    }
    
    /**
     * Constructor for Boost Tower
     * 
     * @param type Type of tower
     * @param name Name of tower
     * @param imageID ID of tower image
     * @param damage Damage each tower bullet inflicts
     * @param attackSpeed Tower attack speed
     * @param attackAmount Tower attack amount
     * @param range Tower attack range
     * @param cost Cost in gold of purchasing tower
     * @param recyclePrice Gold gained by selling tower
     * @param description Brief description of tower behavior
     * @param boostFactor Boost factor
     */
    public TowerJSONObject (String type,
                            String name,
                            String imagePath,
                            int damage,
                            double attackSpeed,
                            int range,
                            int cost,
                            int recyclePrice,
                            String description,
                            double boostFactor) {

        this.put("Type", type);
        this.put("Name", name);
        this.put("Image", imagePath);
        this.put("Damage", damage);
        this.put("Attack Speed", attackSpeed);
        this.put("Range", range);
        this.put("Cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("Description", description);
        this.put("Boost Factor", boostFactor);
    }
    
    /**
     * Constructor for Magic Tower
     * 
     * @param type Type of tower
     * @param name Name of tower
     * @param imageID ID of tower image
     * @param damage Damage each tower bullet inflicts
     * @param attackSpeed Tower attack speed
     * @param attackMode Tower attack mode
     * @param attackAmount Tower attack amount
     * @param range Tower attack range
     * @param cost Cost in gold of purchasing tower
     * @param recyclePrice Gold gained by selling tower
     * @param description Brief description of tower behavior
     * @param magicFacotor Magic factor
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
                            String description,
                            double magicFactor,
                            String magic) {

        this.put("Type", type);
        this.put("Name", name);
        this.put("Image", imagePath);
        this.put("Damage", damage);
        this.put("Attack Speed", attackSpeed);
        this.put("Attack Mode", attackMode);
        this.put("Range", range);
        this.put("Cost", cost);
        this.put("Sell Price", recyclePrice);
        this.put("Description", description);
        this.put("Magic Factor", magicFactor);
        JSONArray magicArray = new JSONArray();
        magicArray.put(magic);
        this.put("Magic", magicArray);
    }
    

}
