package gameEngine.model;

import jgame.JGObject;

/**
 * 
 * @author Fabio, Yuhua
 *
 */
public class Enemy extends JGObject {

    String id;
    String image;
    
    double gold;
    double life;
    double speed;
    
    Level level;
    
    public Enemy (
                  double gold,
                  double life,
                  double speed,
                  Level level,
                  
                  String id,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String image) {
        super(id, unique_id, x, y, collisionid, image);
        
        this.id = id;
        this.image = image;
        
        this.gold = gold;
        this.life = life;
        this.speed = speed;
        this.level = level;
    }
    
    @Override
    public void move() {
        //Should walk along the Path
        x += speed;
        y += 0;
    }

    @Override
    public void hit(JGObject obj) {
        //hit the target enemy, destroy that enemy
        System.out.println("Bullet Hit");
        if(obj.colid == 3){
            life -= ((Bullet)obj).getDamage();
            obj.remove();
            
            if(life <= 0){
                level.getGameInfo().addGold((int)gold);
                level.getEnemies().remove(this);
                
                remove();
            }

        }
    }

}
