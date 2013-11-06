package gameEngine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Jiaran
 * This class is to decide how to generate enemies, or detected win condition
 * (clear all the levels) or lose condition(lose all hp)
 * Finally, this class should be created based on the JSen file.(rate, types,
 * time, number of level etc.
 */
public class Rule {
    private List<Wave> Waves = new ArrayList<Wave>();
    private int myCurrentWaveIndex=0;
    private Timer myTimer = new Timer();
    //user can set how much time for the user to get familiar with the game. 
    private long myInitialDelay=2000;
    public Rule(){
        
    }
    public void addWave(Wave wave){
        Waves.add(wave);
    }
    public void ruleStart(){
        myTimer.schedule(new StartWave(),myInitialDelay);
    }
    class StartWave extends TimerTask{

        @Override
        public void run () {
            if(myCurrentWaveIndex<Waves.size()){
                Wave w= Waves.get(myCurrentWaveIndex);
                w.waveStart();
                myTimer.schedule(new StartWave(), w.getInterval());
                myCurrentWaveIndex++;
            }
            //else deal with end game;
        }
        
    }
    
    
}
