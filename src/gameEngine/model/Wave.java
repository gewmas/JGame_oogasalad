package gameEngine.model;

import gameEngine.model.warehouse.EnemyWarehouse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 * @author Jiaran
 *         One Wave of enemies.
 */
public class Wave implements ActionListener {
    private int mySpawnPeriodInMilliSecond = 0;
    private String[] myEnemyType = null;
    private Integer[] myNumberOfEnemies ;
    private Timer myTimer = null;
    private long myIntervalInMilliSecond = 0;
    private EnemyWarehouse myEnemyWarehouse = null;
    private int myCurrentIndex=0;

    public Wave (String type[], Integer[] number, double period, long interval, EnemyWarehouse ew) {
        if(period>=60){
            period=60;
        }
        mySpawnPeriodInMilliSecond = (int)(period*1000);
        myEnemyType = type;
        myNumberOfEnemies = number;
        if(myEnemyType.length!=myNumberOfEnemies.length)
            return;
        myEnemyWarehouse = ew;
        int totalNumber= 0;
        for(int i=0;i<myNumberOfEnemies.length;i++){
            totalNumber+= myNumberOfEnemies[i];
        }
        myIntervalInMilliSecond = interval*1000+totalNumber*mySpawnPeriodInMilliSecond;
        myTimer = new Timer(mySpawnPeriodInMilliSecond, this);

    }

    @Override
    public void actionPerformed (ActionEvent e) {
        if (myCurrentIndex < myNumberOfEnemies.length) {
            if (myEnemyWarehouse == null)
                return;
            if (myNumberOfEnemies[myCurrentIndex] <= 0) {
                myCurrentIndex++;
            }

            myEnemyWarehouse.create(myEnemyType[myCurrentIndex]);
            myNumberOfEnemies[myCurrentIndex]--;
            
        }
        else
            myTimer.stop();

    }

    public long getInterval () {
        return myIntervalInMilliSecond;
    }

    public void waveStart () {
        myTimer.start();
    }
    
    
}
