package gameEngine.model;

import gameEngine.factory.Factory;
import gameEngine.factory.TowerFactory;
import gameEngine.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Yuhua
 * 
 * Level with new waves of enemies
 * Similar to Rule by @author Jiaran
 *
 */
public class Wave {

    private GameInfo gameInfo;
    private Parser parser;

    private List<Tower> towers;
    private List<Enemy> enemies;

    public Wave(GameInfo gameInfo, Parser parser){
        this.gameInfo = gameInfo;
        this.parser = parser;
        towers = new ArrayList<Tower>();
        enemies = new ArrayList<Enemy>();

        initGameLevelOne();
    }

    /**
     * @author Yuhua
     * Test just one level
     */
    public void initGameLevelOne(){
        
        Factory factory = new Factory(parser, this);

        Tower tower = factory.tower().createFromId("1");
        towers.add(tower);

        Enemy enemy1 = factory.enemy().createFromId("1");
        enemies.add(enemy1);
        Enemy enemy2 = factory.enemy().createFromId("2");
        enemies.add(enemy2);
    }

    public List<Tower> getTowers () {
        return towers;
    }

    public List<Enemy> getEnemies () {
        return enemies;
    }

    public GameInfo getGameInfo () {
        return gameInfo;
    }
}
