package gameEngine.model;

import java.util.List;
import jgame.JGObject;

/**
 * @author Yuhua
 *
 * Tower will shoot the Enemy within shooting range with Bullet 
 */

public class Tower extends JGObject {

    double damage;
    double attackSpeed;
    double range;

    double cost;
    double recyclePrice;

    Model model;
    
    long prevTime;

    public Tower ( 
                  double damage,
                  double attackSpeed,
                  double range,
                  double cost,
                  double recyclePrice,

                  Model model,

                  String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname
            ) {

        super(name, unique_id, x, y, collisionid, gfxname);

        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cost = cost;
        this.recyclePrice = recyclePrice;

        this.model = model;
        
        this.prevTime = System.currentTimeMillis();

    }

    @Override
    public void move() {
        //check the enemies within the shooting range
        // create bullets

        List<Enemy> enemies = model.getEnemies();
        for(Enemy e : enemies){
            //check distance between this tower and e then shoot bullets
            double dist = Math.sqrt(Math.pow(e.x-x, 2)+Math.pow(e.y-y, 2));
            long deltaTime = (System.currentTimeMillis() - prevTime)/1000; //convert to second
            if(dist < range && deltaTime > 1/attackSpeed){
                new Bullet(e, damage, "bullet", true, x, y, 3, "bullet");
                prevTime = System.currentTimeMillis();
            }
        }


    }

    @Override
    public void hit(JGObject obj) {


    }


}
