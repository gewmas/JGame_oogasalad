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
import java.util.Collection;
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
    private JSONObject container;

    public GameData () {
        container = new JSONObject();
    }

    public void setGameName (String gameName) {
        myGameName = gameName;
        container.put("name", myGameName);
    }

    public void setGold (int gold) {
        myGold = gold;
        container.put("gold", myGold);
    }

    protected void setLives (int lives) {
        myLives = lives;
        container.put("numberOfLives", myLives);
    }

    protected void setSplashImage (String splashImage) {
        mySplashImage = splashImage;
        container.put("splashImage", mySplashImage);
    }

    protected void setWindowWidth (int windowWidth) {
        myWindowWidth = windowWidth;
        container.put("widthOfWindow", myWindowWidth);
    }

    protected void setWindowHeight (int windowHeight) {
        myWindowHeight = windowHeight;
        container.put("heightOfWindow", myWindowHeight);
    }

    protected void setTilesPerRow (int tilesPerRow) {
        myTilesPerRow = tilesPerRow;
        container.put("tilesPerRow", myTilesPerRow);
    }

    protected void setDifficultyScale (float difficultyScale) {
        myDifficultyScale = difficultyScale;
        container.put("difficultyScale", myDifficultyScale);
    }

    protected void addTower (String name, String imagePath,
                             int damage,
                             int attackSpeed,
                             int range,
                             int cost,
                             int recyclePrice) {

        myTowerList
                .put(new TowerJSONObject(name, imagePath, damage, attackSpeed, range, cost,
                                         recyclePrice));

    }

    protected void addLevel (LevelJSONObject level) {
        myLevelList.put(level);
    }

    protected void addEnemy (String name, int gold, String image, int life, int speed) {
        myEnemyList.put(new EnemyJSONObject(name, gold, image, life, speed));
    }

    protected JSONArray getEnemyList () {
        return myEnemyList;
    }

    protected void setMap (String bgImage,
                           String pathImage,
                           Point2D start,
                           Point2D end,
                           Collection<Point2D> pointList) {
        myMap = new MapJSONObject(bgImage,
                                  pathImage,
                                  start,
                                  end,
                                  pointList);

        container.put("map", myMap);
    }

    private void addDataToContainer () {
        container.put("levels", myLevelList.length());
        container.put("towerType", myTowerList);
        container.put("enemyType", myEnemyList);
        container.put("levelData", myLevelList);
    }

    public void writeToFile () {
        addDataToContainer();

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

}
