package gameAuthoring.JSONObjects;

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
     * @param type Enemy type
     * @param number Number of enemies
     * @param period Duration of wave
     * @param interval 
     */
    public WaveJSONObject(String type, int number, double period, int interval){
       super();
       JSONArray typeArray = new JSONArray();
       typeArray.put(type);
       JSONArray numberArray = new JSONArray();
       numberArray.put(number);
       this.put("type", typeArray);
       this.put("number", numberArray);
       this.put("period", period);
       this.put("interval", interval);
    }

}
