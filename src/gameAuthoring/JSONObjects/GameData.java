package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
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
    private JSONArray myTowerList;
    private JSONArray myEnemyList;
    private JSONArray myBarrierList;
    private JSONArray myWaveList;
    private MapJSONObject myMap;
    private ResourcesJSONObject myResources;

    public final static String TOWER_LIST_KEY = "Tower";
    public final static String ENEMY_LIST_KEY = "enemyType";
    public final static String TEMPBARRIER_LIST_KEY = "temporaryBarrierType";
    public final static String WAVE_LIST_KEY = "wave";
    public final static String RESOURCE_KEY = "resources";

    public final static String GAME_NAME_KEY = "name";
    public final static String GOLD_KEY = "gold";
    public final static String GOLD_NAME_KEY = "goldName";
    public final static String LIVES_KEY = "numberOfLives";
    public final static String LIVES_NAME_KEY = "livesName";

    public final static String SPLASH_IMAGE_KEY = "splashImage";
    public final static String BG_IMAGE_KEY = "BGImage";
    public final static String BULLET_IMAGE_KEY = "bulletImage";
    public final static String BG_MUSIC_KEY = "bgMusic";

    /**
     * Constructor for GameData class
     */
    public GameData () {
        super();
        myTowerList = new JSONArray();
        myEnemyList = new JSONArray();
        myBarrierList = new JSONArray();
        myWaveList = new JSONArray();
        myResources = new ResourcesJSONObject();

        this.put(TOWER_LIST_KEY, myTowerList);
        this.put(ENEMY_LIST_KEY, myEnemyList);
        this.put(TEMPBARRIER_LIST_KEY, myBarrierList);
        this.put(WAVE_LIST_KEY, myWaveList);
        this.put(RESOURCE_KEY, myResources);

        Collection<Point2D> pathList = new ArrayList<Point2D>();
        myMap = new MapJSONObject("path.png", pathList);
        this.put("map", myMap);
    }

    /**
     * Sets name of game
     * 
     * @param gameName Name of game
     */
    public void setGameName (String gameName) {
        this.put(GAME_NAME_KEY, gameName);
    }

    /**
     * Sets starting quantity of gold
     * 
     * @param gold Quantity of gold
     */
    public void setGold (int gold) {
        this.put(GOLD_KEY, gold);
    }

    /**
     * Sets name of gold
     * 
     * @param goldName Alternative name of gold
     */
    public void setGoldName (String goldName) {
        this.put(GOLD_NAME_KEY, goldName);
    }

    /**
     * Sets starting number of lives
     * 
     * @param lives
     */
    public void setLives (int lives) {
        this.put(LIVES_KEY, lives);
    }

    /**
     * Set name of lives
     * 
     * @param livesName Alternative name of lives
     */
    public void setLivesName (String livesName) {
        this.put(LIVES_NAME_KEY, livesName);
    }

    /**
     * Sets splash image
     * 
     * @param splashImage Name of splash image
     */
    public void setSplashImage (String splashImage) {
        this.put(SPLASH_IMAGE_KEY, splashImage);
    }

    /**
     * Sets background image
     * 
     * @param backgroundImage
     */
    public void setBackgroundImage (String backgroundImage) {
        this.put(BG_IMAGE_KEY, backgroundImage);
    }

    /**
     * Adds tower to myTowerList JSONArray
     * 
     * @param tower TowerJSONObject to be added
     */
    public void addTower (TowerJSONObject tower) {
        myTowerList.put(tower);
    }

    /**
     * Adds wave to myWaveList JSONArray
     * 
     * @param wave WaveJSONOBject to be added
     */
    public void addWave (WaveJSONObject wave) {
        myWaveList.put(wave);
    }

    /**
     * Adds barrier to myBarrierList JSONArray
     * 
     * @param barrier Barrier to be added
     */
    public void addBarrier (TemporaryBarrierJSONObject barrier) {
        myBarrierList.put(barrier);
    }

    /**
     * Adds enemy to myEnemyListJSONArray
     * 
     * @param enemy Enemy to be added
     */
    public void addEnemy (EnemyJSONObject enemy) {
        myEnemyList.put(enemy);
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
     * Adds a barrier to the myMap
     * 
     * @param x x coordinate
     * @param y
     * @param imageName
     */
    public void addBarrier (int x, int y, String imageName) {
        myMap.addBarrier(x, y, imageName);
    }

    /**
     * Adds image myResources
     * 
     * @param id image ID
     * @param url image URL
     */
    public void addImage (String id, String url) {
        myResources.addImage(id, url);
    }

    /**
     * 
     * @param image
     */
    public void addImage (ResourceJSONObject image) {
        myResources.addImage(image);
    }

    /**
     * Adds audio to myResources
     * 
     * @param id Image ID
     * @param url Image URL
     */
    public void addAudio (String id, String url) {
        myResources.addAudio(id, url);
    }

    /**
     * Adds audio to myResources
     * 
     * @param audio ResourceJSONObject to be added
     */
    public void addAudio (ResourceJSONObject audio) {
        myResources.addAudio(audio);
    }

    /**
     * Adds animation to myResources
     * 
     * @param animation Animation to be added
     */
    public void addAnimation (AnimationJSONObject animation) {
        myResources.addAnimation(animation);
    }

    /**
     * Adds image for bullet
     * 
     * @param imageID ID of image
     */
    public void addBulletImage (String imageID) {
        this.put(BULLET_IMAGE_KEY, imageID);
    }

    /**
     * Adds audio for background music
     * 
     * @param audioID ID of audio
     */
    public void addBGAudio (String audioID) {
        this.put(BG_MUSIC_KEY, audioID);
    }

    /**
     * Opens file chooser dialogue to save current state of GameData object into a file
     */
    public void writeToFile () {

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
    }

    /**
     * @author Lalita Maraj
     *         This method is used to create a temporary file so that a user can simmulate
     *         a game based on the parameters the game designer specified
     *         without the hassel of saving, then loading a file
     * @return
     */
    public File createSimmulationFile () {
        if (true) {
            File file = new File("tmp.JSON");
            System.out.println("dlkfjsdjfdsjfdsjfs");
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
        return (this.has("name") && this.has("splashImage") && this.has("BGImage") &&
                this.has("gold") && this.has("numberOfLives"));

    }
}
