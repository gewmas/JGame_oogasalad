package gameAuthoring;

import java.util.ArrayList;
import java.util.List;


public class EnemyDesignData {

    // Enemy Info
    private List<Integer> myEnemyGoldValues = new ArrayList<Integer>();
    private List<String> myEnemyImages = new ArrayList<String>();
    private List<Integer> myEnemyLives = new ArrayList<Integer>();
    private List<Integer> myEnemySpeeds = new ArrayList<Integer>();
    private List<Integer> myEnemyDamages = new ArrayList<Integer>();
    private List<Integer> myEnemySpawnTime = new ArrayList<Integer>();

    public EnemyDesignData () {
        // TODO Auto-generated constructor stub
    }

    public void addEnemyGoldValue (int enemyGoldValue) {
        myEnemyGoldValues.add(enemyGoldValue);
    }

    public List<Integer> getEnemyGoldValues () {
        return myEnemyGoldValues;
    }

    public void addEnemyImage (String enemyImage) {
        myEnemyImages.add(enemyImage);
    }

    public List<String> getEnemyImages () {
        return myEnemyImages;
    }

    public void addEnemyLives (int enemyLife) {
        myEnemyLives.add(enemyLife);
    }

    public List<Integer> getEnemyLives () {
        return myEnemyLives;
    }

    public void addEnemySpeed (int enemySpeed) {
        myEnemySpeeds.add(enemySpeed);
    }

    public List<Integer> getEnemySpeeds () {
        return myEnemySpeeds;
    }

    public void addEnemyDamage (int enemyDamage) {
        myEnemyDamages.add(enemyDamage);
    }

    public List<Integer> getEnemyDamages () {
        return myEnemyDamages;
    }

}
