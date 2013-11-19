package gameEngine.model.bullet;

import gameEngine.model.enemy.Enemy;
import jgame.JGObject;


/**
 * 
 * @author Yuhua
 * 
 *         Bullet will follow the target Enemy until it hits
 * 
 */
public class Bullet extends JGObject {

    Enemy targetEnemy;
    int currentMagic = 1;

    double damage;

    public Bullet (
                   Enemy targetEnemy,
                   double damage,

                   String name,
                   boolean unique_id,
                   double x,
                   double y,
                   int collisionid,
                   String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);

        this.targetEnemy = targetEnemy;
        this.damage = 1;
    }

    @Override
    public void move () {
        // if target enemy destroy other bullets, remove
        if (!targetEnemy.isAlive()) remove();

        double dx = targetEnemy.x - x;
        double dy = targetEnemy.y - y;
        double ds = Math.sqrt(dx * dx + dy * dy);

        x += dx / ds;
        y += dy / ds;
    }

    @Override
    public void hit (JGObject obj) {
        // Bullet can only kill target Enemy
        if (obj == targetEnemy) {
            remove();
        }
    }

    public double getDamage () {
        return damage;
    }

    public Enemy getTargetEnemy () {
        return targetEnemy;
    }

    public int getCurrentMagic () {
        return currentMagic;
    }

    public void setCurrentMagic (int magic) {
        currentMagic = magic;
    }

}
