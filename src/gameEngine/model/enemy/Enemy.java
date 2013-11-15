package gameEngine.model.enemy;

import java.util.LinkedList;
import gameEngine.Constant.Constant;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.tower.Tower;
import jgame.JGObject;
import gameEngine.model.Model;
import gameEngine.model.Tile;


/**
 * 
 * @author Fabio, Yuhua
 * 
 */
public class Enemy extends JGObject {

    String id;
    String image;

    Model model;
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
                  int collisionid,
                  String image,
                  Model model) {
        super(id, unique_id, model.getPathList().get(0).getCenterX(), model.getPathList().get(0).getCenterX(), collisionid, image);

        this.model = model;
        this.id = id;
        this.image = image;
        this.xMovement = 1;
        this.yMovement = 0;

        this.gold = gold;
        this.life = life;
        this.speed = speed;
        this.path = model.getPathList();
        this.pathIndex = 0;

        this.x = path.get(0).getCenterX();
        this.y = path.get(0).getCenterY();

        //Yuhua change it
        //        this.x = x;
        //        this.y = y;


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
            Bullet bullet = (Bullet)obj;
            if(this == bullet.getTargetEnemy()){
                /**
                 * @author Yuhua
                 * bullet can only hurt target enemy
                 * no obj.remove(), let bullet kill itself
                 */
                life -= ((Bullet) obj).getDamage();
                if (life <= 0) {
                    // level.getGameInfo().addGold((int)gold);
                    // level.getEnemies().remove(this);

                    remove();
                }
            }
 
        }
    }


    public void reachedGoal() {
        model.getGameInfo().loseLife();
        remove();
    }



    public void calculateNewDirection () {

        pathIndex = pathIndex + 1;
        if(pathIndex == path.size()) {
            reachedGoal();
            return;
        }
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

    public double getLife() {
        return life;
    }
    
    /**
     * @author Yuhua
     * For comparator to compare shortest/furthest enemy
     */
    public double getDistanceFromTower(Tower tower){
        return Math.sqrt(Math.pow(x - tower.getX(), 2) + Math.pow(y - tower.getY(), 2));
    }

}
