package gameAuthoring.JSONObjects;

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
       this.put("type", type);
       this.put("number", number);
       this.put("period", period);
       this.put("interval", interval);
    }

}
