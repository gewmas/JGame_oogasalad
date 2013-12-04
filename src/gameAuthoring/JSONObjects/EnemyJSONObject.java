package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONObject that contains enemy data
 *
 */
public class EnemyJSONObject extends JSONObject {

    /**
     * Constructor for EnemyJSONObject
     * 
     * @param ID Enemy name
     * @param gold Gold received when enemy is destroyed
     * @param imagePath Enemy image name
     * @param lives Enemy's number of lives (hits that can be taken)
     * @param speed Enemy's speed
     */
    public EnemyJSONObject (String ID, int gold, String imagePath, int lives, double speed) {
        super();
        this.put("id", ID);
        this.put("gold", gold);
        this.put("image", imagePath);
        this.put("life", lives);
        this.put("speed", speed);
    }

}
