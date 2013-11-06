package gameEngine.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Yuhua
 * 
 * Level with new waves of enemies
 * Similar to Rule by @author Jiaran
 *
 */
public class Level {

    private GameInfo gameInfo;

    private List<Tower> towers;
    private List<Enemy> enemies;

    public Level(GameInfo gameInfo){
        this.gameInfo = gameInfo;

        towers = new ArrayList<Tower>();
        enemies = new ArrayList<Enemy>();

        initGameLevelOne();
    }

    /**
     * @author Yuhua
     * Test just one level
     */
    public void initGameLevelOne(){
        Tower tower = new Tower(1,0.8,100,100,1, this, "tower", true, 100.0, 100.0, 1, "tower1");
        towers.add(tower);

        Enemy enemy1 = new Enemy(15, 3, 0.1, this, "enemy", true, 10.0, 150.0, 2, "enemy1");
        enemies.add(enemy1);
        Enemy enemy2 = new Enemy(15, 3, 0.1, this, "enemy", true, 50.0, 80.0, 2, "enemy2");

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
