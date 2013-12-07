package gameEngine.model;

import gameEngine.model.warehouse.EnemyWarehouse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;


/**
 * @author Jiaran
 *         One Wave of enemies. The user can specify what kinds of enemies he wants
 *         how many for each type of enemy, the spawn rate, and the interval to next
 *         wave. We use a timer to be precise about when an enemy should be created.
 */
public class Wave implements ActionListener {

    private int mySpawnPeriodInMilliSecond = 0;
    private String[] myEnemyType = null;
    private Integer[] myNumberOfEnemies;
    private Timer myTimer = null;
    private long myIntervalInMilliSecond = 0;
    private EnemyWarehouse myEnemyWarehouse = null;
    private int myCurrentIndex = 0;

    /**
     * To facilitate user, we take in period and interval in seconds. But we still
     * deal with these number in milliseconds inside the Wave class. Type.length
     * should be equal to Number.length. Or it won't generate any enemy.
     */
    public Wave (String type[], Integer[] number, double period, long interval, EnemyWarehouse ew) {
        if (period >= 60) {
            period = 60;
        }
        mySpawnPeriodInMilliSecond = (int) (period * 1000);
        myEnemyType = type;
        myNumberOfEnemies = number;
        if (myEnemyType.length != myNumberOfEnemies.length)
            return;
        myEnemyWarehouse = ew;
        int totalNumber = 0;
        for (int i = 0; i < myNumberOfEnemies.length; i++) {
            totalNumber += myNumberOfEnemies[i];
        }
        if (type.length != number.length) { return; }
        myIntervalInMilliSecond = interval * 1000 + totalNumber * mySpawnPeriodInMilliSecond;
        myTimer = new Timer(mySpawnPeriodInMilliSecond, this);

    }

    /**
     * Generate one type of enemy and if it is done then move on to generate another kind.
     * If all is finished, (index>= the length) the stop the timer.
     */
    @Override
    public void actionPerformed (ActionEvent e) {

        if (myEnemyWarehouse == null)
            return;
        if (myNumberOfEnemies[myCurrentIndex] <= 0) {
            myCurrentIndex++;
        }
        if (myCurrentIndex < myNumberOfEnemies.length) {
            myEnemyWarehouse.create(myEnemyType[myCurrentIndex]);
            myNumberOfEnemies[myCurrentIndex]--;

        }
        else myTimer.stop();

    }

    public long getInterval () {
        return myIntervalInMilliSecond;
    }

    public void waveStart () {
        myTimer.start();
    }

    public void stop () {
        myTimer.stop();
    }

}
