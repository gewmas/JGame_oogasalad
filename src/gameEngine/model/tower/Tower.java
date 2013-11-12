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

public class Tower extends JGObject implements TowerInfo{

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
        
        
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

    public double getDamage () {
        return damage;
    }

    public double getAttackSpeed () {
        return attackSpeed;
    }

    public double getRange () {
        return range;
    }

    public double getCost () {
        return cost;
    }

    public double getRecyclePrice () {
        return recyclePrice;
    }

}
