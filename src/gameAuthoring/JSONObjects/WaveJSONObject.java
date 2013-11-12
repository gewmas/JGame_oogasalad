package gameAuthoring.JSONObjects;

import java.util.Map;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;


public class WaveJSONObject extends JSONObject {

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
