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
    int pathIndex;
    double pathStep;
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
        this.pathIndex = 0;
        this.x = x;
        this.y = y;

        calculatePathStep();
        calculateNewDirection();
    }

    @Override
    public void move () {
        // Should walk along the Path
        if (reachedPoint()) {
            // System.out.println("Reached point!");
            calculateNewDirection();
        }
        x += xMovement * speed;
        y += yMovement * speed;
    }

    public boolean reachedPoint () {
        if (yMovement == 0) {
            double x1 = path.get(pathIndex - 1).getCenterX();
            if (Math.abs(x1 - x) > pathStep) { return true; }
        }
        else {
            double y1 = path.get(pathIndex - 1).getCenterY();
            if (Math.abs(y1 - y) > pathStep) { return true; }
        }

        return false;
    }

    @Override
    public void hit (JGObject obj) {
        // hit the target enemy, destroy that enemy
        // System.out.println("Bullet Hit");
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

    public void calculateNewDirection () {
        pathIndex = pathIndex + 1;
        double x1 = path.get(pathIndex).getCenterX();
        double y1 = path.get(pathIndex).getCenterY();
        if (Math.abs(x - x1) < Math.abs(y - y1)) {
            if ((y - y1) > 0) {
                this.yMovement = -1;
            }
            else {
                this.yMovement = 1;
            }
            this.xMovement = 0;

        }
        else {
            if ((x - x1) > 0) {
                this.xMovement = -1;
            }
            else {
                this.xMovement = 1;
            }
            this.yMovement = 0;
        }
    }

    public void calculatePathStep () {
        double x1 = path.get(pathIndex).getCenterX();
        double y1 = path.get(pathIndex).getCenterY();

        double x2 = path.get((pathIndex + 1)).getCenterX();
        double y2 = path.get((pathIndex + 1)).getCenterY();

        if (x1 == x2) {
            // Y direction distance
            pathStep = Math.abs(y1 - y2);
        }
        else {
            // x direction distance
            pathStep = Math.abs(x1 - x2);
        }
    }

}
