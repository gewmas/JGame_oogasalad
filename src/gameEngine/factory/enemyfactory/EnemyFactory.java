package gameEngine.factory.enemyfactory;

import gameEngine.model.Model;
import gameEngine.model.enemy.Enemy;


/**
 * @Author Fabio Berger
 */
public interface EnemyFactory {
    public Enemy create (Model model);
}
