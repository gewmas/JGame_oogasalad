package gameEngine.model.enemy.comparator;

import gameEngine.model.enemy.Enemy;
import gameEngine.model.tower.Tower;

public class FurthestDistanceEnemyComparator extends EnemyComparator {

    Tower tower;
    
    public FurthestDistanceEnemyComparator(Tower tower){
        super();
        this.tower = tower;
    }
    
    @Override
    public int compare (Enemy enemy1, Enemy enemy2) {
        double distance1 = enemy1.getDistanceFromTower(tower);
        double distance2 = enemy2.getDistanceFromTower(tower);
        
        if(distance1 < distance2){
            return -1;
        }else if(distance1 > distance2){
            return 1;
        }
        return 0;
    }
}
