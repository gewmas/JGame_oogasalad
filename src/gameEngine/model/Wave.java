package gameEngine.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import javax.swing.Timer;
import temporary.TemporaryEnemyFactory;

/**
 * @author Jiaran
 * One Wave of enemies.
 */
public class Wave implements ActionListener {
    private int mySpawnPeriodInMilliSecond=0;
    private String myEnemyType=null;
    private int myNumberOfEnemies=0;
    private Timer myTimer= null;
    private long myIntervalInMilliSecond=5000;
    //for test. Real Enemy Factory needs to be implemented.This wave can 
    //require different factory by myEnemyType
    private TemporaryEnemyFactory f = new TemporaryEnemyFactory();
    public Wave(String type,int num,int period){
        mySpawnPeriodInMilliSecond=period;
        myEnemyType=type;
        myNumberOfEnemies=num;
        myTimer= new Timer(mySpawnPeriodInMilliSecond, this);
        
    }


    @Override
    public void actionPerformed (ActionEvent e) {
       if(myNumberOfEnemies<=0){
           myTimer.stop();
           
       }
       else{
           f.create();
           myNumberOfEnemies--;
       }
        
    }
    
    public long getInterval(){
        return myIntervalInMilliSecond;
    }


    public void waveStart(){
        myTimer.start();
    }
}
