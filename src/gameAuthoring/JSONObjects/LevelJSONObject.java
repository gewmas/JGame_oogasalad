package gameAuthoring.JSONObjects;

import java.util.List;
import java.util.Map;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * JSONObject that contains level data
 *
 */
public class LevelJSONObject extends JSONObject {

    /**
     * Constructor for LevelJSONObject class. 
     * 
     * @param level Level ID number
     * @param numWaves Number of waves in level
     * @param enemyMapList List of maps of enemy name to enemy quantity
     */
    public LevelJSONObject (int level, int numWaves, List<Map<String, Integer>> enemyMapList) {
        this.put("level", level);
        this.put("numberWaves", numWaves);
        JSONArray myWaves = new JSONArray();

        for (int i = 1; i <= numWaves; i++) {
            myWaves.put(new WaveJSONObject(i, enemyMapList.get(i - 1)));
        }

        this.put("enemiesOfWave", myWaves);

    }

}
