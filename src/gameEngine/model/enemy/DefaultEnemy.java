package gameEngine.model.enemy;

import gameEngine.model.Tile;
import java.util.LinkedList;

public class DefaultEnemy extends Enemy {

    public DefaultEnemy (double gold,
                         double life,
                         double speed,
                         String id,
                         boolean unique_id,
                         double x,
                         double y,
                         int collisionid,
                         String image,
                         LinkedList<Tile> path) {
        super(gold, life, speed, id, unique_id, x, y, collisionid, image, path);
        // TODO Auto-generated constructor stub
    }

}
