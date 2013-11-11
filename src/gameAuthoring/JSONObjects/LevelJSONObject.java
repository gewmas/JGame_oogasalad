package gameAuthoring.JSONObjects;

import java.util.ArrayList;
import java.util.Map;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


public class LevelJSONObject extends JSONObject {

    public LevelJSONObject (int level, int numWaves, ArrayList<Map<String, Integer>> enemyMapList) {
        this.put("level", level);
        this.put("numberWaves", numWaves);
        JSONArray myWaves = new JSONArray();

        for (int i = 1; i <= numWaves; i++) {
            myWaves.put(new WaveJSONObject(i, enemyMapList.get(i - 1)));
        }

        this.put("enemiesOfWave", myWaves);

    }

}
