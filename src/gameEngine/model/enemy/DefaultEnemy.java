package gameEngine.model.enemy;

import gameEngine.model.Model;
import gameEngine.model.Tile;
import java.util.LinkedList;


public class DefaultEnemy extends Enemy {

    public DefaultEnemy (double gold,
                         double life,
                         double speed,
                         String id,
                         boolean unique_id,
                         int collisionid,
                         String image,
                         Model model) {
        super(gold, life, speed, id, unique_id, collisionid, image, model);
        // TODO Auto-generated constructor stub
    }

}
