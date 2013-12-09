package gameEngine.model.enemy.comparator;

import gameEngine.model.enemy.Enemy;

public class StrongestEnemyComparator extends EnemyComparator {

    @Override
    public int compare (Enemy enemy1, Enemy enemy2) {
        if(enemy1.getLife() > enemy2.getLife()){
            return -1;
        }else if(enemy1.getLife() < enemy2.getLife()){
            return 1;
        }
        return 0;
    }
}
