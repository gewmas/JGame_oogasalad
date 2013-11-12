package gameEngine.model;

import gameEngine.factory.GridFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.tower.Tower;
import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import jgame.impl.JGEngineInterface;


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
    private JGEngineInterface myEng;
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
        enemyWarehouse = new EnemyWarehouse(parser, this);
        
        gameInfo = new GameInfo(1000, 1000, 1000, null);

        
    }
    
    public void startGame(){
        towerWarehouse.create("DefaultTower"); // test, should be called within Rule

        Wave w = new Wave("1", 10, 500, 1000, enemyWarehouse);
        rule.addWave(w);
        rule.ruleStart();

    }
    
    public LinkedList<Tile> getPathList() {
        return path;
    }

    /**
     * return all kinds of TowerFactory
     */
    public List<TowerFactory> getTowerFactory () {
        return towerWarehouse.getTowerFactory();
    }

    // Jiaran purchase, get tower info. If something is wrong plz contact
     
    public boolean purchaseTower (int x, int y, String name) {
        return towerWarehouse.create(x, y, name,gameInfo);
    }
    //Jiaran Im thinking maybe this should return a TowerInfo instead of Tower
    // Tower can implemetns Towerinfo which has getDescription,getDamage....
    // now it is not functional because no myEng, we need discussion on this.
    public Tower getTowerInfo (int x, int y) {
        Detector<Tower> d= new Detector<Tower>(myEng,Tower.class);
        return d.getOneTargetInRange(x, y, 10);
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
    
    public String getBGImage () {
        return gameInfo.getBGImage();
    }
    
    /*
     * Model Getter methods
     */
    
    public GameInfo getGameInfo() {
        return gameInfo;
    }

   
    
}
