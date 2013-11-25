package gameAuthoring.JSONObjects;

import java.util.Map;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONOjbect that contains wave data
 *
 */
public class WaveJSONObject extends JSONObject {

    /**
     * Constructor for WaveJSONObject
     * 
     * @param type
     * @param number
     * @param period 
     * @param interval
     */
    public WaveJSONObject(String type, int number, double period, int interval){
       super();
       this.put("type", type);
       this.put("number", number);
       this.put("period", period);
       this.put("interval", interval);
    }
    
    //TODO: Remove Tab dependency on this method
    /**
     * OLD constructor for WaveJSONObject
     * 
     * @param waveNumber Wave ID number
     * @param enemyMap Map of enemy name to enemy quantity
     */
    public WaveJSONObject (int waveNumber, Map<String, Integer> enemyMap) {
        this.put("wave", waveNumber);
        JSONArray waveData = new JSONArray();

        for (String enemy : enemyMap.keySet()) {
            JSONObject enemyQuantity = new JSONObject();
            enemyQuantity.put("id", enemy);
            enemyQuantity.put("quantity", enemyMap.get(enemy));
            waveData.put(enemyQuantity);
        }

        this.put("enemies", waveData);
    }

}
