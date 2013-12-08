package gameEngine.model.enemy;

import gameEngine.model.Model;
import gameEngine.model.tile.Tile;
import java.util.LinkedList;


public class DefaultEnemy extends Enemy {

    public DefaultEnemy (double gold,
                         double life,
                         double speed,
                         String id,
                         boolean unique_id,
                         int collisionid,
                         String image,
                         int specialty,
                         Model model) {
        super(gold, life, speed, id, unique_id, collisionid, image, specialty,model);
        // TODO Auto-generated constructor stub
    }

}
