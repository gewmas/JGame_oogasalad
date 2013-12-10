package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * Class that contains the data necessary for temporary barrier game object
 *
 */
public class TemporaryBarrierJSONObject extends JSONObject{

    /**
     * Constructor for TemporaryBarrierJSONObject
     * 
     * @param id Temporary barrier name
     * @param image Temporary barrier image ID
     * @param damage Temporary barrier damage
     * @param cost Temporary barrier cost
     * @param expire Temporary barrier expiry time
     * @param description Temporary barrier description
     */
    public TemporaryBarrierJSONObject (String id, String image, int damage, int cost, int expire, String description) {
        super();
        this.put("id", id);
        this.put("image", image);
        this.put("cost", cost);
        this.put("damage", damage);
        this.put("expire", expire);
        this.put("description", description );
    }

}
