package gameAuthoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class LevelDesignData {
    // Level Info
    private List<HashMap<String, Integer>> myEnemyQuantitiesPerWaves =
            new ArrayList<HashMap<String, Integer>>();

    public LevelDesignData () {
        // TODO Auto-generated constructor stub
    }

    public void addEnemyQuantity (HashMap<String, Integer> waveData) {
        myEnemyQuantitiesPerWaves.add(waveData);
    }

}
