package gameEngine.model;

import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


/**
 * @author Jiaran
 *         This class is to decide how to generate enemies, or detected win condition
 *         (clear all the levels) or lose condition(lose all hp)
 *         Finally, this class should be created based on the JSen file.(rate, types,
 *         time, number of level etc.
 */
public class Rule {
    private List<Wave> Waves = new ArrayList<Wave>();
    private int myCurrentWaveIndex = 0;
    private Timer myTimer = new Timer();
    // user can set how much time for the user to get familiar with the game.
    private long myInitialDelayInMilliseconds = 2000;
    private EnemyWarehouse myEnemyWarehouse=null;

    public Rule (long delay, EnemyWarehouse e) {
        myInitialDelayInMilliseconds = delay;
        myEnemyWarehouse=e;
    }
    

    public Rule () {

    }

    public void addWave (Wave wave) {
        Waves.add(wave);
    }

    public void ruleStart () {
        myTimer.schedule(new StartWave(), myInitialDelayInMilliseconds);
    }

    class StartWave extends TimerTask {

        @Override
        public void run () {
            if (myCurrentWaveIndex < Waves.size()) {
                Wave w = Waves.get(myCurrentWaveIndex);
                w.waveStart();
                myTimer.schedule(new StartWave(), w.getInterval());
                myCurrentWaveIndex++;
            }
            // else deal with end game;
        }

    }
    
    public void readWaveFromJSon (JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject oneWave = jsonArray.getJSONObject(i);
            String[] type = translateJSONArray(oneWave.getJSONArray("type"),String.class);
            Integer[] number = translateJSONArray(oneWave.getJSONArray("number"),Integer.class);
            
            double period = oneWave.getDouble("period");
            long interval = oneWave.getLong("interval");
           
            addWave(new Wave(type, number, period, interval, myEnemyWarehouse));
        }
    }
    
   
    @SuppressWarnings("unchecked")
    private <T> T[] translateJSONArray(JSONArray array,Class<T> c ) {
        
        T[] result= (T[])Array.newInstance(c,array.length());
        for(int i = 0; i < array.length(); i++){
            result[i]= (T) array.get(i);
        }
        return result;
    }

}
