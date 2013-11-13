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
    String towerName;
    String image;
    
    double damage;
    double attackSpeed;
    double range;

    double x;
    double y;

    double cost;
    double recyclePrice;

    String description;
    


    public Tower (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);


        this.towerName = name;        
        this.image = gfxname;
    }

    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }
    
    @Override
    public String getTowerName(){
        return towerName;
    }
    
    @Override
    public String getDescription () {
        return description;
    }
    
    @Override
    public double getDamage (){
        return damage;
    }
    
    @Override
    public double getAttackSpeed (){
        return attackSpeed;
    }
    
    @Override
    public double getRange (){
        return range;
    }
    @Override
    public double getRecyclePrice (){
        return recyclePrice;
    }

    @Override
    public int getCost () {
        return (int)cost;
    }

    @Override
    public String getImage () {
        return image;
    }
}
