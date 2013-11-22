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

    public GameData () {
        super();
    }

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

    public void setGold (int gold) {
        myGold = gold;
        this.put("gold", myGold);
    }

    public void setLives (int lives) {
        myLives = lives;
        this.put("numberOfLives", myLives);
    }

    public void setSplashImage (String splashImage) {
        mySplashImage = splashImage;
        this.put("splashImage", mySplashImage);
    }

    public void setBackgroundImage (String backgroundImage) {
        myBackgroundImage = backgroundImage;
        this.put("BGImage", myBackgroundImage);
    }

    public void setWindowWidth (int windowWidth) {
        myWindowWidth = windowWidth;
        this.put("widthOfWindow", myWindowWidth);
    }

    public void setWindowHeight (int windowHeight) {
        myWindowHeight = windowHeight;
        this.put("heightOfWindow", myWindowHeight);
    }

    public void setTilesPerRow (int tilesPerRow) {
        myTilesPerRow = tilesPerRow;
        this.put("tilesPerRow", myTilesPerRow);
    }

    public void setDifficultyScale (float difficultyScale) {
        myDifficultyScale = difficultyScale;
        this.put("difficultyScale", myDifficultyScale);
    }

    public void addTower (String name, String imagePath,
                             int damage,
                             int attackSpeed,
                             int range,
                             int cost,
                             int recyclePrice) {

        myTowerList.put(new TowerJSONObject(name, imagePath, damage, attackSpeed, range, cost,
                                            recyclePrice));

    }

    public void addLevel (LevelJSONObject level) {
        myLevelList.put(level);
    }

    public void addEnemy (String name, int gold, String image, int life, int speed) {
        myEnemyList.put(new EnemyJSONObject(name, gold, image, life, speed));
    }

    public JSONArray getEnemyList () {
        return myEnemyList;
    }

    public void setMap (String pathImage,
                           Collection<Point2D> pointList) {
        myMap = new MapJSONObject(pathImage, pointList);
        this.put("map", myMap);
    }

    private void addDataTothis () {
        this.put("levels", myLevelList.length());
        this.put("towerType", myTowerList);
        this.put("enemyType", myEnemyList);
        this.put("levelData", myLevelList);
    }

    /**
     * Opens file chooser dialogue to save current state of GameData object into a file
     */
    public void writeToFile () {
        addDataTothis();

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
        System.out.println(this.toString(3));
    }

    /**
     * @author Lalita Maraj
     *         This method is used to create a temporary file so that a user can simmulate
     *         a game based on the parameters the game designer specified
     *         without the hassel of saving, then loading a file
     */
    public void createSimmulationFile () {
        if (isComplete()) {
            File file = new File("tmp.JSON");
            PrintStream out;
            try {
                file.createNewFile();
                out = new PrintStream(file);
                out.println(this.toString(3));
                out.close();
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {

                e.printStackTrace();
            }

        }
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
