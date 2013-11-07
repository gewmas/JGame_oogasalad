package gameEngine.model;

import gameEngine.model.warehouse.EnemyWarehouse;
import gameEngine.model.warehouse.TowerWarehouse;
import gameEngine.model.warehouse.Warehouse;
import gameEngine.parser.Parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
    private Warehouse towerWarehouse;
    private EnemyWarehouse enemyWarehouse;

//    private Rule rule; 

    public Model(){
        // 1 parse jsonfile

        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + "/src/gameEngine/test/testTowerEnemyBullet/mygame.json"));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        parser = new Parser(scanner);

        // 2 create factory by warehouse - hashmap of different kind of tower, enemy
        //     warehouse - store lists of towers, enemies
        towerWarehouse = new TowerWarehouse(parser);
        towerWarehouse.create("DefaultTower"); //test, should be called within Rule
        enemyWarehouse = new EnemyWarehouse(parser);

        // 3 create gameInfo
        gameInfo = new GameInfo(1000, 1000, 1000, null);
        //finally all rules and waves should be created according to JSon
        Rule r= new Rule();
        
        Wave w= new Wave("1", 5, 1000, 10000, enemyWarehouse);
        r.addWave(w);
        r.ruleStart();
        // 4 create path
        
        // 5 create rule - how each waves created, ruleStart, ruleStop
        //     rule - waves -> create enemies
        //new Rule();

    }





}
