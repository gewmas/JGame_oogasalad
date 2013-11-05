package gameEngine.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import temporary.TemporaryEnemyFactory;

/**
 * @author Jiaran
 * One Wave of enemies.
 */
public class Wave implements ActionListener {
    private int mySpawnPeriodInMillisecond=0;
    private String myEnemyType=null;
    private int myNumberOfEnemies=0;
    private Timer myTimer= null;
    //for test. Real Enemy Factory needs to be implemented.This wave can 
    //require different factory by myEnemyType
    private TemporaryEnemyFactory f = new TemporaryEnemyFactory();
    public Wave(String type,int num,int period){
        mySpawnPeriodInMillisecond=period;
        myEnemyType=type;
        myNumberOfEnemies=num;
        myTimer= new Timer(mySpawnPeriodInMillisecond, this);
        
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
    
    public void waveStart(){
        myTimer.start();
    }
}
