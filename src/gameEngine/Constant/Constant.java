package gameEngine.Constant;

import gameEngine.model.enemy.Enemy;
import gameEngine.model.tower.Tower;


/**
 * @author Jiaran
 *         Stores all the constant value of game engine.
 *         These constants should include all the CIDs of JGObject.
 */
public class Constant {
    public static final int ENEMY_CID = 1;
    public static final int TOWER_CID = 2;
    public static final int BULLET_CID = 4;

    public static int query(Class T){
//        System.out.println(T.getName());
        if(T.getName().equals("gameEngine.model.enemy.Enemy")){
            return ENEMY_CID;
        }
        else if (T.getName().equals("gameEngine.model.tower.Tower")) {
            return TOWER_CID;
        }
        else return 0;
    }
}
