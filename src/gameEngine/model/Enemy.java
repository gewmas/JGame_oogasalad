package gameEngine.model;

import jgame.JGObject;

/**
 * 
 * @author Fabio, Yuhua
 *
 */
public class Enemy extends JGObject {

    double gold;
    double life;
    double speed;
    
    Level level;
    
    public Enemy (
                  double gold,
                  double life,
                  double speed,
                  Level level,
                  
                  String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        
        this.level = level;
    }
    
    @Override
    public void move() {
        //For test
        x -= 0.1;
        y += 0.1;
    }

    @Override
    public void hit(JGObject obj) {
        //hit the target enemy, destroy that enemy
        System.out.println("Bullet Hit");
        if(obj.colid == 3){
            level.removeEnemy(this);        
            obj.remove();
            remove();
               

        }
    }

}
