package gameAuthoring;

import java.util.List;
import java.util.Map;


public class GameData {

    // Basic Game Info
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

    private List<String> myTowerIDs;
    private List<String> myTowerImages;
    private List<Integer> myTowerDamages;
    private List<Integer> myTowerAttackSpeeds;
    private List<Integer> myTowerAttackRanges;
    private List<Integer> myTowerCosts;
    private List<Integer> myTowerRecyclePrices;
    private List<Integer> myTowerLives;

    // Enemy Info
    private List<Integer> myEnemyGoldValue;
    private List<String> myEnemyImages;
    private List<Integer> myEnemyLives;
    private List<Integer> myEnemySpeeds;
    private List<Integer> myEnemyDamages;
    private List<Integer> myEnemySpawnTime;

    // Level Info
    private List<Integer> myLevelNumWaves;
    private List<Integer> myLevelNumEnemies;
    private List<Map<String, Integer>> myLevelWavesEnemyQuantities;

}
