package gameEngine.model;

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
    private List<Tower> towers;
    private List<Enemy> enemies;

    public Model(){
        towers = new ArrayList<Tower>();
        enemies = new ArrayList<Enemy>();

        iniGameLevelOne();
    }

    /**
     * @author Yuhua
     * Test just one level
     */
    public void iniGameLevelOne(){
        Tower tower = new Tower(1,0.8,100,1,1, this, "tower", true, 100.0, 100.0, 1, "tower");
        towers.add(tower);

        Enemy enemy1 = new Enemy(1, 1, 1, this, "enemy", true, 150.0, 150.0, 2, "enemy");
        enemies.add(enemy1);
        Enemy enemy2 = new Enemy(1, 1, 1, this, "enemy", true, 100.0, 80.0, 2, "enemy");
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

}
