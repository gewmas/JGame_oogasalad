package gameEngine.model;

import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.Tower;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.util.List;
import java.util.Scanner;


public class Model {

    /**
     * @author Yuhua
     * 
     * Pipeline of Model is as below.
     * 
     */

    private Scanner scanner;
    private Parser parser;
    private GameInfo gameInfo;
    private TowerWarehouse towerWarehouse;
    private EnemyWarehouse enemyWarehouse;
    
    private Detector detector;

    // private Rule rule;

    public Model () {
        
    }
    
    public void newGame(File jsonFile) throws Exception{
        // For test convenience
//        jsonFile = new File(System.getProperty("user.dir") + "/src/gameEngine/test/testTowerEnemyBullet/mygame.json");

        scanner = new Scanner(jsonFile);
        parser = new Parser(scanner);

        towerWarehouse = new TowerWarehouse(parser);
        enemyWarehouse = new EnemyWarehouse(parser);
        
    }
    
    public void startGame(){
        // 2 create factory by warehouse - hashmap of different kind of tower, enemy
        // warehouse - store lists of towers, enemies
        towerWarehouse.create("DefaultTower"); // test, should be called within Rule

        // 3 create gameInfo
        gameInfo = new GameInfo(1000, 1000, 1000, null);
        // finally all rules and waves should be created according to JSon
        Rule r = new Rule();

        Wave w = new Wave("1", 5, 1000, 10000, enemyWarehouse);
        r.addWave(w);
        r.ruleStart();
        // 4 create path

        // 5 create rule - how each waves created, ruleStart, ruleStop
        // rule - waves -> create enemies
        // new Rule();

    }

    /**
     * return all kinds of TowerFactory
     */
    public List<TowerFactory> getTowerFactory () {
        return towerWarehouse.getTowerFactory();
    }

    /**
     * Ask TowerWarehouse to create tower
     * 
     * @param x
     * @param y
     * @param tower
     */
    public void purchaseTower (int x, int y, String name) {
        towerWarehouse.create(x, y, name);
    }
    
    public Tower getTowerInfo (int x, int y) {
        //detector not init yet, can't find engineinterface
        int range = 10;
        return detector.getTowerInRange(x, y, range);
    }

    
    /*
     * GameInfo getter method
     */
    public int getMoney () {
        return gameInfo.getGold();
    }

    public int getLife () {
        return gameInfo.getLife();
    }

   
    
}
