package gameEngine.model.bullet;

import gameEngine.model.enemy.Enemy;
import jgame.JGObject;
import jgame.JGRectangle;


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
    private double dx=0.5;
    private double dy=0.5;

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
        this.speed = 3;
        JGRectangle box=this.getBBox();
        this.setBBox(0, 0, box.width/2, box.height/2);
        expiry=100;
    }

    @Override
    public void move () {
        // if target enemy destroy other bullets, remove
        
        if(targetEnemy.isInvisiable()){
            x+=dx;
            y+=dy;
            return;
        }
        if (!targetEnemy.isAlive()||targetEnemy==null) remove();

        dx = targetEnemy.x - x;
        dy = targetEnemy.y - y;
        double ds = Math.sqrt(dx * dx + dy * dy);

        x += dx / ds * speed;
        y += dy / ds * speed;
    }

    @Override
    public void hit (JGObject obj) {
        // Bullet can only kill target Enemy
        if (obj == targetEnemy||obj.isInView((int) this.x, (int) this.y)) {
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
