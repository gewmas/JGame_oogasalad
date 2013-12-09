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

	private Enemy targetEnemy;
    private int currentMagic;

    private double damage;
    private int specialty;
    
    private double speed;

    public Bullet (
                   Enemy targetEnemy,
                   double damage,
                   
                   int magic,
                   int specialty,

                   String name,
                   boolean unique_id,
                   double x,
                   double y,
                   int collisionid,
                   String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);

        this.targetEnemy = targetEnemy;
        this.damage = damage;
        this.currentMagic = magic;
        this.specialty = specialty;
        
        this.speed = 2;
    }

    @Override
    public void move () {
        // if target enemy destroy other bullets, remove
        if (!targetEnemy.isAlive()) remove();

        double dx = targetEnemy.x - x;
        double dy = targetEnemy.y - y;
        double ds = Math.sqrt(dx * dx + dy * dy);

        x += dx / ds * speed;
        y += dy / ds * speed;
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

	public int getSpecialty() {
		return specialty;
	}

}
