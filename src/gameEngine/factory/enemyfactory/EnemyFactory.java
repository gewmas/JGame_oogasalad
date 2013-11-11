package gameEngine.factory.enemyfactory;

import java.util.LinkedList;
import gameEngine.model.Tile;
import gameEngine.model.enemy.Enemy;


/**
 * @Author Fabio Berger
 */
public interface EnemyFactory {
    public Enemy create (LinkedList<Tile> path);
}
