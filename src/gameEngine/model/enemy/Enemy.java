package gameEngine.model.enemy;

import java.util.LinkedList;
import gameEngine.Constant.Constant;
import gameEngine.model.bullet.Bullet;
import jgame.JGObject;
import gameEngine.model.Tile;


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
    double xMovement;
    double yMovement;
    
    LinkedList<Tile> path;

    public Enemy (
                  double gold,
                  double life,
                  double speed,

                  String id,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String image,
                  LinkedList<Tile> path) {
        super(id, unique_id, x, y, collisionid, image);

        this.id = id;
        this.image = image;
        this.xMovement = 1;
        this.yMovement = 0;

        this.gold = gold;
        this.life = life;
        this.speed = speed;
        this.path = path;
        this.x = x;
        this.y = y;
    }

    @Override
    public void move () {
        // Should walk along the Path
        if(x == path.element().getCenterX() && y == path.element().getCenterY()) {
            calculateNewDirection();
        }
        x += xMovement*speed;
        y += yMovement*speed;
    }

    @Override
    public void hit (JGObject obj) {
        // hit the target enemy, destroy that enemy
        System.out.println("Bullet Hit");
        if (obj.colid == Constant.BULLET_CID) {
            life -= ((Bullet) obj).getDamage();
            obj.remove();

            if (life <= 0) {
                // level.getGameInfo().addGold((int)gold);
                // level.getEnemies().remove(this);

                remove();
            }

        }
    }
    
    public void calculateNewDirection() {
        path.pop();
        if(x == path.element().getCenterX()) {
            if(y < path.element().getCenterY()) {
                this.yMovement = 1;
            } else {
                this.yMovement = -1;
            }
            this.xMovement = 0;
        } else {
            if(x < path.element().getCenterX()) {
                this.xMovement = 1;
            } else {
                this.xMovement = -1;
            }
            this.yMovement = 0;
        }
    }

}
