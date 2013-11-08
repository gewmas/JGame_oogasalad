package gameEngine.model.enemy;

import gameEngine.Constant.Constant;
import gameEngine.model.bullet.Bullet;
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
    
    
    public Enemy (
                  double gold,
                  double life,
                  double speed,
                  
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
        if(obj.colid == Constant.BULLET_CID){
            life -= ((Bullet)obj).getDamage();
            obj.remove();
            
            if(life <= 0){
//                level.getGameInfo().addGold((int)gold);
//                level.getEnemies().remove(this);
                
                remove();
            }

        }
    }

}
