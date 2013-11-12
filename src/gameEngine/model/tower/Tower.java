package gameEngine.model.tower;

import gameEngine.model.Detector;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import java.util.List;
import jgame.JGObject;


/**
 * @author Yuhua
 * 
 *         Tower will shoot the Enemy within shooting range with Bullet
 */

public class Tower extends JGObject {

    double damage;
    double attackSpeed;
    double range;

    double x;
    double y;
    
    double cost;
    double recyclePrice;
    
    

    public Tower (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        System.out.println("haha");
        
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

}
