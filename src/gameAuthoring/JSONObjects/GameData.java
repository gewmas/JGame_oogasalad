package gameAuthoring.JSONObjects;

import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
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
    JSONArray myTowerList = new JSONArray();
    JSONArray myEnemyList = new JSONArray();
    JSONArray myBarrierList = new JSONArray();
    JSONArray myWaveList = new JSONArray();

    MapJSONObject myMap;
    ResourcesJSONObject myResources = new ResourcesJSONObject();

    /**
     * Constructor for GameData class
     */
    public GameData () {
        super();
        this.put("Tower", myTowerList);
        this.put("enemyType", myEnemyList);
        this.put("temporaryBarrierType", myBarrierList);
        this.put("wave", myWaveList);
        this.put("resources", myResources);
    }

    /**
     * Sets name of game
     * 
     * @param gameName Name of game
     */
    public void setGameName (String gameName) {
        this.put("name", gameName);
    }

    /**
     * Sets starting quantity of gold
     * 
     * @param gold Quantity of gold
     */
    public void setGold (int gold) {
        this.put("gold", gold);
    }

    /**
     * Sets starting number of lives
     * 
     * @param lives
     */
    public void setLives (int lives) {
        this.put("numberOfLives", lives);
    }

    /**
     * Sets splash image
     * 
     * @param splashImage Name of splash image
     */
    public void setSplashImage (String splashImage) {
        this.put("splashImage", splashImage);
    }

    /**
     * Sets background image
     * 
     * @param backgroundImage
     */
    public void setBackgroundImage (String backgroundImage) {
        this.put("BGImage", backgroundImage);
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
                          double attackSpeed,
                          int attackMode,
                          int range,
                          int cost,
                          int recyclePrice,
                          String description) {

        myTowerList.put(new TowerJSONObject(type, name, imagePath, damage, attackSpeed, attackMode,
                                            range, cost, recyclePrice, description));

    }
    
    public void addMagicTower(String type,
                          String name,
                          String imagePath,
                          int damage,
                          double attackSpeed,
                          int attackMode,
                          int range,
                          int cost,
                          int recyclePrice,
                          String description,
                          double magicFactor,
                          String magic){
        
        myTowerList.put(new TowerJSONObject(type, name, imagePath, damage, attackSpeed, attackMode,
                                            range, cost, recyclePrice, description, magicFactor, magic));
    }
    
    public void addMultipleShootingTower(String type,
                              String name,
                              String imagePath,
                              int damage,
                              double attackSpeed,
                              int attackMode,
                              int attackAmount,
                              int range,
                              int cost,
                              int recyclePrice,
                              String description){
            
            myTowerList.put(new TowerJSONObject(type, name, imagePath, damage, attackSpeed, attackMode, attackAmount,
                                                range, cost, recyclePrice, description));
        }
    
    public void addBoostTower(String type,
                              String name,
                              String imagePath,
                              int damage,
                              double attackSpeed,
                              int range,
                              int cost,
                              int recyclePrice,
                              String description, 
                              double boostFactor){
            
            myTowerList.put(new TowerJSONObject(type, name, imagePath, damage, attackSpeed,
                                                range, cost, recyclePrice, description, boostFactor));
        }
    
    public void addTower (TowerJSONObject t) {
        myTowerList.put(t);
    }

    /**
     * Adds wave to myWaveList JSONArray
     * 
     * @param type
     * @param number
     * @param period
     * @param interval
     */
    public void addWave (String type, int number, double period, int interval) {
        myWaveList.put(new WaveJSONObject(type, number, period, interval));
    }

    /**
     * Adds barrier to myBarrierList JSONArray
     * 
     * @param name Barrier name
     * @param image Barrier image
     * @param damage Barrier damage
     * @param cost Cost of barrier in gold
     * @param expire Time barrier is active in game
     * @param description Description of barrier
     */
    public void addBarrier (String name,
                            String image,
                            int damage,
                            int cost,
                            int expire,
                            String description) {

        myBarrierList.put(new TemporaryBarrierJSONObject(name, image, damage, cost, expire,
                                                         description));

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
    public void addEnemy (String name, int gold, String image, int life, double speed, String skill) {
        myEnemyList.put(new EnemyJSONObject(name, gold, image, life, speed, skill));
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

    public void addBarrier (int x, int y, String imageName) {
        myMap.addBarrier(x, y, imageName);
    }

    public void addImage (String id, String url) {
        myResources.addImage(id, url);
    }

    public void addAudio (String id, String url) {
        myResources.addAudio(id, url);
    }

    public void addAnimation (String id, List<String> imagePaths) {
        myResources.addAnimation(id, imagePaths);
    }

    public ResourcesJSONObject getResources () {
        return myResources;
    }
    
    public void addBulletImage(String imageID){
        this.put("bulletImage", imageID);
    }
    
    public void addBGAudio(String audioID){
        this.put("bgMusic", audioID);
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
       return (this.has("name") && this.has("splashImage") && this.has("BGImage") && this.has("gold") && this.has("numberOfLives"));
 
    }
}
