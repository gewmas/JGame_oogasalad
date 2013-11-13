package gameEngine.model.tower;

import java.util.ArrayList;
import java.util.List;
import gameEngine.Constant.Constant;
import gameEngine.model.Detector;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import jgame.JGObject;


public class DefaultTower extends Tower {

    long prevTime;
    private Detector<Enemy> dector;
    
    Enemy targetEnemy;

    public DefaultTower (
                         double damage,
                         double attackSpeed,
                         int attackMode,
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
        this.attackMode = attackMode;
        
        this.range = range;
        this.cost = cost;
        this.recyclePrice = recyclePrice;

        this.x = x;
        this.y = y;

        this.prevTime = System.currentTimeMillis();
        this.dector = new Detector<Enemy>(this.eng, Enemy.class);
        // level.getGameInfo().loseGold((int)cost);

    }

    @Override
    public void move () {
        // check the enemies within the shooting range
        // create bullets
    	targetEnemy = null;
        getEnemyInRange();
        
        
        // check the time after last shoot
        long deltaTime = (System.currentTimeMillis() - prevTime) / 1000; // convert to second
        if (targetEnemy != null && deltaTime > 1 / attackSpeed) {
            new Bullet(targetEnemy, damage, "bullet", true, x, y, Constant.BULLET_CID, "bullet");
            prevTime = System.currentTimeMillis();
        }
    }
    
    public void getEnemyInRange(){
    	List<Enemy> enemies = dector.getTargetsInRange((int) x, (int) y, (int) range);
    	if(enemies.isEmpty())return; //No enemy in range
    	
		List<Enemy> enemiesInRange = new ArrayList<Enemy>();
		for (Enemy e : enemies) {
			// check distance between this tower and e then shoot bullets
	        double dist = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
	        if (dist < range) {
	            enemiesInRange.add(e);
	        }
		}
		
		if(enemiesInRange.isEmpty())return; //No enemy in range, double check
		
    	if(attackMode == 0){
    		getClosestEnemy(enemiesInRange);
    	}else if(attackMode == 1){
    		getFurtherestEnemy(enemiesInRange);
    	}else if(attackMode == 2){
    		getWeakestEnemy(enemiesInRange);
    	}else if(attackMode == 3){
    		getStrongestEnemy(enemiesInRange);
    	}
    	
    	//if can't find one, but there is enemy in range
    	//assign one
    	if(targetEnemy == null && !enemiesInRange.isEmpty()){
    		targetEnemy = enemiesInRange.get(0);
    	}
    }
    
    //Implement AttackMode
    public void getClosestEnemy(List<Enemy> enemiesInRange){
    	double closestDist = Double.MAX_VALUE;
    	for(Enemy e : enemiesInRange){
	        double dist = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
	        if(dist < closestDist){
	        	closestDist = dist;
	        	targetEnemy = e;
	        }
    	}
    }
    
    public void getFurtherestEnemy(List<Enemy> enemiesInRange){
    	double furtherestDist = Double.MIN_VALUE;
    	for(Enemy e : enemiesInRange){
	        double dist = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
	        if(dist > furtherestDist){
	        	furtherestDist = dist;
	        	targetEnemy = e;
	        }
    	}
    }
    
    public void getWeakestEnemy(List<Enemy> enemiesInRange){
    	double weakestLife = Double.MAX_VALUE;
    	for(Enemy e : enemiesInRange){
    		double life = e.getLife();
	        if(life < weakestLife){
	        	weakestLife = life;
	        	targetEnemy = e;
	        }
    	}
    }
    
    public void getStrongestEnemy(List<Enemy> enemiesInRange){
    	double strongestLife = Double.MIN_VALUE;
    	for(Enemy e : enemiesInRange){
    		double life = e.getLife();
	        if(life > strongestLife){
	        	strongestLife = life;
	        	targetEnemy = e;
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
