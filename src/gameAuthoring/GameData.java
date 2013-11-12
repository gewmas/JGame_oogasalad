package gameAuthoring;

import gameAuthoring.JSONObjects.EnemyJSONObject;
import gameAuthoring.JSONObjects.LevelJSONObject;
import gameAuthoring.JSONObjects.MapJSONObject;
import gameAuthoring.JSONObjects.TowerJSONObject;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;


public class GameData {
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/JSON");

    private String myGameName;
    private int myGold;
    private int myLives;

    private String mySplashImage;
    private int myWindowWidth;
    private int myWindowHeight;
    private int myTilesPerRow;
    private double myDifficultyScale;

    JSONArray myTowerList = new JSONArray();
    JSONArray myEnemyList = new JSONArray();
    JSONArray myLevelList = new JSONArray();

    MapJSONObject myMap;

    // private Collection<TowerJSONObject> myTowers;
    // private Collection<EnemyJSONObject> myEnemies;

    private JSONObject container;

    public GameData () {
        container = new JSONObject();
    }

    public void setGameName (String gameName) {
        myGameName = gameName;
    }

    public void setGold (int gold) {
        myGold = gold;
    }

    public void setLives (int lives) {
        myLives = lives;
    }

    public void setSplashImage (String splashImage) {
        mySplashImage = splashImage;
    }

    private void setWindowWidth (int windowWidth) {
        myWindowWidth = windowWidth;
    }

    private void setWindowHeight (int windowHeight) {
        myWindowHeight = windowHeight;
    }

    private void setTilesPerRow (int tilesPerRow) {
        myTilesPerRow = tilesPerRow;
    }

    private void setDifficultScale (float difficultyScale) {
        myDifficultyScale = difficultyScale;
    }

    public void addTower (String name, String imagePath,
                          int damage,
                          int attackSpeed,
                          int range,
                          int cost,
                          int recyclePrice) {

        myTowerList
                .put(new TowerJSONObject(name, imagePath, damage, attackSpeed, range, cost,
                                         recyclePrice));

    }

    public void addEnemy (String name, int gold, String image, int life, int speed) {
        myEnemyList.put(new EnemyJSONObject(name, gold, image, life, speed));
    }

    public JSONArray getEnemyList () {
        return myEnemyList;
    }

    public void testWrite () { // This method is here only for the sake of testing (don't judge)

        this.setGameName("Tower Destruction");
        this.setGold(200);
        this.setLives(100);

        this.addTower("bob", "path", 10, 15, 11, 9, 8);
        this.addTower("pewpew", "path", 10, 15, 11, 9, 8);

        this.addEnemy("Enemy1", 100, "path", 10, 11);
        this.addEnemy("Enemy2", 100, "path", 10, 11);

        mySplashImage = "blargh";
        myWindowWidth = 600;
        myWindowHeight = 400;
        myTilesPerRow = 15;
        myDifficultyScale = 1.5;

        Collection<Point2D> woop = new ArrayList<Point2D>();
        woop.add(new Point2D.Double(1, 2));
        woop.add(new Point2D.Double(2, 3));
        woop.add(new Point2D.Double(3, 3));

        myMap = new MapJSONObject("backgroundImg",
                                  "pathImg",
                                  new Point2D.Double(1, 2),
                                  new Point2D.Double(1, 2),
                                  woop);

        container.put("map", myMap);

        ArrayList<Map<String, Integer>> enemyMapList = new ArrayList<Map<String, Integer>>();
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        Map<String, Integer> map2 = new HashMap<String, Integer>();

        map1.put("steve", 11);
        map2.put("steve", 11);

        enemyMapList.add(map1);
        enemyMapList.add(map2);

        myLevelList.put(new LevelJSONObject(1, 2, enemyMapList));
        map1.put("bob", 10);
        myLevelList.put(new LevelJSONObject(2, 2, enemyMapList));

        addDataToContainer();
        writeToFile();

    }

    public void addDataToContainer () {
        container.put("name", myGameName);
        container.put("gold", myGold);
        container.put("numberOfLives", myLives);
        container.put("splashImage", mySplashImage);
        container.put("widthOfWindow", myWindowWidth);
        container.put("heightOfWindow", myWindowHeight);
        container.put("difficultyScale", myDifficultyScale);
        container.put("tilesPerRow", myTilesPerRow);

        container.put("levels", myLevelList.length());
        container.put("towerType", myTowerList);
        container.put("enemyType", myEnemyList);
        container.put("levelData", myLevelList);
        container.put("levelData", myLevelList);
    }

    public void writeToFile () {
        int result = INPUT_CHOOSER.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = INPUT_CHOOSER.getSelectedFile();
            try {
                PrintStream out = new PrintStream(file);
                out.println(container.toString(3));
                out.close();
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }

    }

    public static void main (String[] args) {
        GameData x = new GameData();
        x.testWrite();
    }

}
