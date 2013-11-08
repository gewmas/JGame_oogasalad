package gameEngine.model;

import gameEngine.model.warehouse.EnemyWarehouse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @author Jiaran
 * One Wave of enemies.
 */
public class Wave implements ActionListener {
    private int mySpawnPeriodInMilliSecond=0;
    private String myEnemyType=null;
    private int myNumberOfEnemies=0;
    private Timer myTimer= null;
    private long myIntervalInMilliSecond=10000;
    //for test. Real Enemy Factory needs to be implemented.This wave can 
    //require different factory by myEnemyType
    private EnemyWarehouse myEnemyWarehouse = null;
    public Wave(String type,int num,int period,long interval,EnemyWarehouse ew){
        mySpawnPeriodInMilliSecond=period;
        myEnemyType=type;
        myNumberOfEnemies=num;
        myEnemyWarehouse= ew;
        myIntervalInMilliSecond=interval;
        myTimer= new Timer(mySpawnPeriodInMilliSecond, this);
        
    }


    @Override
    public void actionPerformed (ActionEvent e) {
       if(myNumberOfEnemies<=0){
           myTimer.stop();
           
       }
       else{
           if(myEnemyWarehouse==null)
               //must be something is wrong
               return;
           myEnemyWarehouse.create(myEnemyType);
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
