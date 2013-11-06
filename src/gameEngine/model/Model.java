package gameEngine.model;

import gameEngine.parser.Parser;
import java.util.ArrayList;
import java.util.List;

public class Model {

    /**
     * @author Yuhua
     * towers and enemies should associate with each level
     * which should store in some class call Level or Rule
     * 
     * Instance variables here used for test purpose
     */
    private GameInfo gameInfo;
//    prviate Factory facotry;
//  private Warehouse towerWarehouse;
//  private Warehouse enemyWarehouse;
    
    private Level level; // delete later
    
    public Model(Parser parser){
        // 1 parse jsonfile
        // 2 create factory
        // 3 create gameInfo
        gameInfo = new GameInfo(1000, 1000, 1000);
        // 4 create warehouse - hashmap of different kind of tower, enemy
        //     warehouse - store lists of towers, enemies
        // 5 create rule - how each waves created, ruleStart, ruleStop
        //     rule - waves -> create enemies
        //new Rule();

        level = new Level(gameInfo, parser); // delete later
    }
    
    
   


}
