package gameEngine.model;

import gameEngine.factory.GridFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.Tower;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;


public class Model {

    /**
     * @author Yuhua
     * 
     * warehouse - store different kinds of tower, enemy warehouse 
     * 
     */

    private Scanner scanner;
    private Parser parser;
    private GameInfo gameInfo;
    private TowerWarehouse towerWarehouse;
    private EnemyWarehouse enemyWarehouse;
    private GridFactory gridFactory;
    private LinkedList<Tile> path;
    private Detector detector;
    private Rule rule; //how each waves created, ruleStart, ruleStop

    // private Rule rule;

    public Model () {
        rule = new Rule();
    }
    
    public void newGame(File jsonFile) throws Exception{
        // For test convenience
//        jsonFile = new File(System.getProperty("user.dir") + "/src/gameEngine/test/testTowerEnemyBullet/mygame.json");

        scanner = new Scanner(jsonFile);
        parser = new Parser(scanner);

        gridFactory = new GridFactory(parser);
        gridFactory.initialize();
        path = gridFactory.getPathList();
        
        // 2 create factory by 
              
        towerWarehouse = new TowerWarehouse(parser);
        enemyWarehouse = new EnemyWarehouse(parser, path);
        
        gameInfo = new GameInfo(1000, 1000, 1000, null);

        
    }
    
    public void startGame(){
        towerWarehouse.create("DefaultTower"); // test, should be called within Rule

        Wave w = new Wave("1", 5, 1000, 10000, enemyWarehouse);
        rule.addWave(w);
        rule.ruleStart();

    }
    
    public List<Tile> getPathList() {
        return path;
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
