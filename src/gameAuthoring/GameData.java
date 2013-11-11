package gameAuthoring;

import gameAuthoring.JSONObjects.EnemyJSONObject;
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
import javax.swing.JFileChooser;


public class GameData {
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources");

    private String myGameName;
    private int myGold;
    private int myLives;

    private String mySplashImage = "blargh";
    private int myWindowWidth = 600;
    private int myWindowHeight = 400;
    private int myTilesPerRow = 15;
    private double myDifficultyScale = 1.5;

    JSONArray towerList = new JSONArray();
    JSONArray enemyList = new JSONArray();
    JSONArray levelList = new JSONArray();

    MapJSONObject myMap;

    // private Collection<TowerJSONObject> myTowers;
    // private Collection<EnemyJSONObject> myEnemies;

    private JSONObject container;

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

        towerList
                .put(new TowerJSONObject(name, imagePath, damage, attackSpeed, range, cost,
                                         recyclePrice));

    }

    public void addEnemy (String name, int gold, String image, int life, int speed) {
        enemyList.put(new EnemyJSONObject(name, gold, image, life, speed));
    }

    public void testWrite () {

        container = new JSONObject();

        this.setGameName("Tower Destruction");
        this.setGold(200);
        this.setLives(100);

        this.addTower("bob", "path", 10, 15, 11, 9, 8);
        this.addTower("pewpew", "path", 10, 15, 11, 9, 8);

        this.addEnemy("Enemy1", 100, "path", 10, 11);
        this.addEnemy("Enemy2", 100, "path", 10, 11);

        // container.put("name", myGameName);
        // container.put("gold", myGold);
        // container.put("numberOfLives", myLives);
        // container.put("splashImage", mySplashImage);
        // container.put("widthOfWindow", myWindowWidth);
        // container.put("heightOfWindow", myWindowHeight);
        // container.put("difficultyScale", myDifficultyScale);
        // container.put("tilesPerRow", myTilesPerRow);
        //
        // container.put("levels", levelList.length());
        // container.put("towerType", towerList);
        // container.put("enemyType", enemyList);
        // container.put("levelData", levelList);

        Collection<Point2D> woop = new ArrayList<Point2D>();
        woop.add(new Point2D.Double(1, 2));
        woop.add(new Point2D.Double(2, 3));
        woop.add(new Point2D.Double(3, 3));

        myMap = new MapJSONObject("OMG",
                                  "WHAT",
                                  new Point2D.Double(1, 2),
                                  new Point2D.Double(1, 2),
                                  woop);

        container.put("map", myMap);
        System.out.println(container.toString(1));

        // writeToFile();

    }

    public void writeToFile () {
        int result = INPUT_CHOOSER.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = INPUT_CHOOSER.getSelectedFile();
            try {
                PrintStream out = new PrintStream(file);
                out.println(container.toString(1));
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
