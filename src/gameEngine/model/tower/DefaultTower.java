package gameEngine.model.tower;

import java.util.List;
import gameEngine.Constant.Constant;
import gameEngine.model.Detector;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import jgame.JGObject;


public class DefaultTower extends Tower {

    double damage;
    double attackSpeed;
    double range;

    double cost;
    double recyclePrice;

    long prevTime;

    public DefaultTower (
                         double damage,
                         double attackSpeed,
                         double range,
                         double cost,
                         double recyclePrice,

                         String id,
                         boolean unique_id,
                         double x,
                         double y,
                         int collisionid,
                         String image) {

        super(id, unique_id, x, y, collisionid, image);

        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.range = range;
        this.cost = cost;
        this.recyclePrice = recyclePrice;

        // this.level = level;

        this.prevTime = System.currentTimeMillis();

        // level.getGameInfo().loseGold((int)cost);

    }

    @Override
    public void move () {
        // check the enemies within the shooting range
        // create bullets
        List<Enemy> enemies = dector.getEnemiesInRange((int) x, (int) y, (int) range);
        for (Enemy e : enemies) {
            // check distance between this tower and e then shoot bullets
            double dist = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
            long deltaTime = (System.currentTimeMillis() - prevTime) / 1000; // convert to second

            if (dist < range && deltaTime > 1 / attackSpeed) {
                new Bullet(e, damage, "bullet", true, x, y, Constant.BULLET_CID, "bullet");
                prevTime = System.currentTimeMillis();
            }
        }
    }

    @Override
    public void hit (JGObject obj) {

    }

    public void sell () {
        // level.getGameInfo().addGold((int)recyclePrice);
        // level.getTowers().remove(this);

        this.remove();
    }

}
