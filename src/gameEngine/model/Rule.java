package gameEngine.model;

import gameEngine.model.effect.CreateEffect;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import jgame.impl.JGEngineInterface;
import gameEngine.constant.*;


/**
 * Rule manages the creation and execution of Waves. Also, it can
 * return certain information of the game for the View to display
 * such as current wave.
 * 
 * @author Jiaran
 */
public class Rule {
    private List<Wave> Waves = new ArrayList<Wave>();
    private int myCurrentWaveIndex = 0;
    private Timer myTimer = new Timer();
    private boolean isAlive = true;;
    private long myInitialDelayInMilliseconds = 2000;
    private EnemyWarehouse myEnemyWarehouse = null;
    private JGEngineInterface myEng=null;
    
    public Rule (long delay, EnemyWarehouse e, JGEngineInterface eng) {
        myInitialDelayInMilliseconds = delay;
        myEnemyWarehouse = e;
        myEng=eng;
    }

    public void addWave (Wave wave) {
        Waves.add(wave);
    }

    /**
     * start the Game with a initial delay
     */
    public void ruleStart () {
        myTimer.schedule(new StartWave(), myInitialDelayInMilliseconds);
    }

    /**
     * This is the TimerTask that run the Wave.
     * 
     */
    class StartWave extends TimerTask {

        @Override
        public void run () {
            if (myCurrentWaveIndex < Waves.size() && isAlive) {
                Wave w = Waves.get(myCurrentWaveIndex);
                w.waveStart();
                CreateEffect.Words(300, 300, "WAVE "+myCurrentWaveIndex);
                myTimer.schedule(new StartWave(), w.getInterval());
                myCurrentWaveIndex++;
            }
            if (!isAlive)
                Waves.get(myCurrentWaveIndex).stop();
        }

    }

    /**
     * Creates Waves according to JSON. In our design, instead of have initialization
     * method in one place, we let each Object handles its own initialization.
     * @param jsonArray
     * 
     */
    public void readWaveFromJSon (JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject oneWave = jsonArray.getJSONObject(i);
            String[] type = translateJSONArray(oneWave.getJSONArray("type"), String.class);
            Integer[] number = translateJSONArray(oneWave.getJSONArray("number"), Integer.class);

            double period = oneWave.getDouble("period");
            long interval = oneWave.getLong("interval");

            addWave(new Wave(type, number, period, interval, myEnemyWarehouse));
        }
    }

    /**
     * This method is a generic method. It can read an array of any type
     * from the json file. Here we use it to give type(String[]) and number(Integer[])
     * to initialize the wave. I think this method should be put in Parser as a utility
     * 
     * @param array: the array in JSon
     * @param c : The .class Class which holds the information of type
     * @return: an array of certain type of objects
     * 
     * 
     */

    @SuppressWarnings("unchecked")
    private <T> T[] translateJSONArray (JSONArray array, Class<T> c) {

        T[] result = (T[]) Array.newInstance(c, array.length());
        for (int i = 0; i < array.length(); i++) {
            result[i] = (T) array.get(i);
        }
        return result;
    }

    /**
     * @return the current Wave number
     */
    public int getCurrentWaveNum () {
        return myCurrentWaveIndex;
    }

    public boolean isWin(){
        if(myCurrentWaveIndex==Waves.size()){
            if(myEng.countObjects(null,GameEngineConstant.query(Enemy.class)) ==0){
                return true;
            }
            
        }
        return false;
    }
    public void stop () {
        isAlive = false;
    }

}
