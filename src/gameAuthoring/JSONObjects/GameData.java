package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import javax.swing.JFileChooser;


/**
 * Class that stores all the game data before it is written to JSON. By storing all the data in a
 * single JSONObject, all the information can be written using the JSONObject's built in toString()
 * method
 * 
 */
public class GameData extends JSONObject {
    private static final JFileChooser INPUT_CHOOSER =
            new JFileChooser(System.getProperties().getProperty("user.dir") + "/resources/JSON");

    private String myGameName;
    private int myGold;
    private int myLives;

    private String mySplashImage;
    private String myBackgroundImage;
    private int myWindowWidth;
    private int myWindowHeight;
    private int myTilesPerRow;
    private double myDifficultyScale;

    JSONArray myTowerList = new JSONArray();
    JSONArray myEnemyList = new JSONArray();
    JSONArray myLevelList = new JSONArray();

    MapJSONObject myMap;

    /**
     * Constructor for GameData class
     */
    public GameData () {
        super();
    }

    /**
     * Returns object mapped to key string. Possibilities of return types include String, int,
     * double, JSONArray, JSONObject
     */
    public Object get (String objectName) {
        return this.get(objectName);
    }

    /**
     * Sets name of game
     * 
     * @param gameName Name of game
     */
    public void setGameName (String gameName) {
        myGameName = gameName;
        this.put("name", myGameName);
    }

    /**
     * Sets starting quantity of gold
     * 
     * @param gold Quantity of gold
     */
    public void setGold (int gold) {
        myGold = gold;
        this.put("gold", myGold);
    }

    /**
     * Sets starting number of lives
     * 
     * @param lives
     */
    public void setLives (int lives) {
        myLives = lives;
        this.put("numberOfLives", myLives);
    }

    /**
     * Sets splash image
     * 
     * @param splashImage Name of splash image
     */
    public void setSplashImage (String splashImage) {
        mySplashImage = splashImage;
        this.put("splashImage", mySplashImage);
    }

    /**
     * Sets background image
     * 
     * @param backgroundImage
     */
    public void setBackgroundImage (String backgroundImage) {
        myBackgroundImage = backgroundImage;
        this.put("BGImage", myBackgroundImage);
    }

    /**
     * Sets window width
     * 
     * @param windowWidth Width of game window (pixels)
     */
    public void setWindowWidth (int windowWidth) {
        myWindowWidth = windowWidth;
        this.put("widthOfWindow", myWindowWidth);
    }

    /**
     * Sets window height
     * 
     * @param windowHeight Height of game window (pixels)
     */
    public void setWindowHeight (int windowHeight) {
        myWindowHeight = windowHeight;
        this.put("heightOfWindow", myWindowHeight);
    }

    /**
     * Sets tiles per row
     * 
     * @param tilesPerRow Tiles per row
     */
    public void setTilesPerRow (int tilesPerRow) {
        myTilesPerRow = tilesPerRow;
        this.put("tilesPerRow", myTilesPerRow);
    }

    // TODO: Determine if this is necessary
    /**
     * Sets difficulty scale of game
     * 
     * @param difficultyScale Difficulty scale (float > 1)
     */
    public void setDifficultyScale (float difficultyScale) {
        myDifficultyScale = difficultyScale;
        this.put("difficultyScale", myDifficultyScale);
    }

    /**
     * Adds a tower to the towerList JSONArray
     * 
     * @param name Tower ID
     * @param imagePath Tower image name
     * @param damage Damage inflicted by tower bullet
     * @param attackSpeed Tower attack speed
     * @param range Tower range
     * @param cost Tower cost (gold)
     * @param recyclePrice Quantity of gold gained when tower is sold
     */
    public void addTower (String type,
                          String name,
                          String imagePath,
                          int damage,
                          int attackSpeed,
                          int attackMode,
                          int range,
                          int cost,
                          int recyclePrice,
                          String description) {

        myTowerList.put(new TowerJSONObject(type, name, imagePath, damage, attackSpeed, attackMode,
                                            range, cost, recyclePrice, description));

    }

    /**
     * Add a level to myLevelList JSONArray
     * 
     * @param level LevelJSONObject to be added
     */
    public void addLevel (LevelJSONObject level) {
        myLevelList.put(level);
    }

    /**
     * Adds an enemy to myEnemyList JSONArray
     * 
     * @param name Enemy ID
     * @param gold Quantity of gold gained upon defeating enemy
     * @param image Enemy image name
     * @param life Number enemy lives (hits enemy can endure)
     * @param speed Enemy speed
     */
    public void addEnemy (String name, int gold, String image, int life, int speed) {
        myEnemyList.put(new EnemyJSONObject(name, gold, image, life, speed));
    }

    /**
     * Returns JSONArray of enemies
     * 
     * @return JSONArray of currently created enemies
     */
    public JSONArray getEnemyList () {
        return myEnemyList;
    }

    /**
     * Adds map
     * 
     * @param pathImage Name of path image
     * @param pointList List of points in path
     */
    public void setMap (String pathImage,
                        Collection<Point2D> pointList) {
        myMap = new MapJSONObject(pathImage, pointList);
        this.put("map", myMap);
    }

    /**
     * Adds enemy, tower, and level lists to GameData
     */
    private void addListData () {
        this.put("levels", myLevelList.length());
        this.put("towerType", myTowerList);
        this.put("enemyType", myEnemyList);
        this.put("levelData", myLevelList);
    }

    /**
     * Opens file chooser dialogue to save current state of GameData object into a file
     */
    public void writeToFile () {
        addListData();

        int result = INPUT_CHOOSER.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = INPUT_CHOOSER.getSelectedFile();
            try {
                PrintStream out = new PrintStream(file);
                out.println(this.toString(3));
                out.close();
            }
            catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        // System.out.println(this.toString(3));
    }

    /**
     * @author Lalita Maraj
     *         This method is used to create a temporary file so that a user can simmulate
     *         a game based on the parameters the game designer specified
     *         without the hassel of saving, then loading a file
     * @return 
     */
    public File createSimmulationFile () {
        if (isComplete()) {
            File file = new File("tmp.JSON");
            PrintStream out;
            try {
                file.createNewFile();
                out = new PrintStream(file);
                out.println(this.toString(3));
                out.close();
                return file;
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {

                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * Method to check if the gameData is completely
     * filled out.
     * 
     * @return boolean that indicates if gameData is complete
     * @author Lalita Maraj
     */
    private boolean isComplete () {
        // TODO Auto-generated method stub
        return true;
    }
}
