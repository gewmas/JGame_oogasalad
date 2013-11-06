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
        Tower tower = new Tower(1,0.8,100,100,1, this, "tower", true, 100.0, 100.0, 1, "tower");
        towers.add(tower);

        Enemy enemy1 = new Enemy(15, 3, 3, this, "enemy", true, 150.0, 150.0, 2, "enemy");
        enemies.add(enemy1);
        Enemy enemy2 = new Enemy(15, 10, 10, this, "enemy", true, 80.0, 80.0, 2, "enemy");
        enemies.add(enemy2);
    }

    public List<Tower> getTowers () {
        return towers;
    }

    public List<Enemy> getEnemies () {
        return enemies;
    }

    public void removeEnemy(Enemy enemy){
        enemies.remove(enemies.indexOf(enemy));
    }

    public GameInfo getGameInfo () {
        return gameInfo;
    }
}
