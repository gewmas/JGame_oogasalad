package gameEngine.model.enemy.comparator;

import gameEngine.model.enemy.Enemy;
import java.util.Comparator;

/**
 * 
 * @author Yuhua
 *
 * EnemyComparator use to compare enemy properties for tower to choose which to shoot
 * Like shortest/furthest enemy, strongest/weakest enemy
 *
 */

public abstract class EnemyComparator implements Comparator<Enemy> {
    @Override
    public  abstract int compare (Enemy enemy1, Enemy enemy2);
}
