package gameAuthoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameData {

    // Basic Game Info
    // Game name, splash image, etc.
    private String myGameName;
    private String mySplashImage;
    private int myGold;
    private int myLives;
    private int myNumLevels;
    private int[][] myMapData;
    private String myMapBGImage;
    private String myPathImage;

    // Tower Info
    // Index of each list corresponds to one tower
    // Example: Tower 0 will have tower ID that is index 0 in myTowerIDs, tower damage that is index
    // 0 in myTowerDamages, attack speed that is index 0 in myTowerAttackSpeeds...

    private List<String> myTowerIDs = new ArrayList<String>();
    private List<String> myTowerImages = new ArrayList<String>();
    private List<Integer> myTowerDamages = new ArrayList<Integer>();
    private List<Integer> myTowerAttackSpeeds = new ArrayList<Integer>();
    private List<Integer> myTowerAttackRanges = new ArrayList<Integer>();
    private List<Integer> myTowerCosts = new ArrayList<Integer>();
    private List<Integer> myTowerRecyclePrices = new ArrayList<Integer>();
    private List<Integer> myTowerLives = new ArrayList<Integer>();

    // Enemy Info
    private List<Integer> myEnemyGoldValue = new ArrayList<Integer>();
    private List<String> myEnemyImages = new ArrayList<String>();
    private List<Integer> myEnemyLives = new ArrayList<Integer>();
    private List<Integer> myEnemySpeeds = new ArrayList<Integer>();
    private List<Integer> myEnemyDamages = new ArrayList<Integer>();
    private List<Integer> myEnemySpawnTime = new ArrayList<Integer>();

    // Level Info
    private List<Integer> myLevelNumWaves = new ArrayList<Integer>();
    private List<Integer> myLevelNumEnemies = new ArrayList<Integer>();
    private List<HashMap<String, Integer>> myLevelWavesEnemyQuantities =
            new ArrayList<HashMap<String, Integer>>();

}
