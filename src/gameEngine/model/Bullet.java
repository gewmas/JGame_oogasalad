package gameEngine.model;

import jgame.JGObject;

public class Bullet extends JGObject {

	Enemy targetEnemy;

	public Bullet (
			Enemy targetEnemy,
			String name,
			boolean unique_id,
			double x,
			double y,
			int collisionid,
			String gfxname) {
		super(name, unique_id, x, y, collisionid, gfxname);
		
		this.targetEnemy = targetEnemy;
	}

	@Override
	public void move() {
	    double dx = targetEnemy.x - x;
	    double dy = targetEnemy.y - y;
	    double ds = Math.sqrt(dx*dx+dy*dy);

	    x += dx/ds;
	    y += dy/ds;
	}

	@Override
	public void hit(JGObject obj) {
	    //hit the target enemy, destroy that enemy
//	    System.out.println("Bullet Hit");
//	    if(obj.colid == 2){
//	        obj.remove();
//	           remove();
//
//	    }
	}
}
