package gameEngine.model.tower;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.Detector;
import gameEngine.model.GameInfo;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.enemy.comparator.FurthestDistanceEnemyComparator;
import gameEngine.model.enemy.comparator.ShortestDistanceEnemyComparator;
import gameEngine.model.enemy.comparator.StrongestEnemyComparator;
import gameEngine.model.enemy.comparator.WeakestEnemyComparator;
import gameEngine.model.purchase.PurchaseInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import jgame.JGObject;


/**
 * 
 * @author Yuhua
 * 
 * DefaultTower can attack enemy, those who also kill enemies extends DefaultTower
 * Like MultipleShootTower which shoot multiple Bullet at one time
 * However, Tower like BoostTower and FreezeTower would not extends DefaultTower
 * because they will not shoot, but change properties
 * 
 */

public class DefaultTower extends Tower {

    long prevTime;
    private Detector<Enemy> detector;

    /**
     * AttackMode include
     * 0 - shoot the closest enemy
     * 1 - shoot the farthest enemy
     * 2 - shoot weakest enemy with least life
     * 3 - shoot strongest enemy with most life
     */
    private static final String[] attackModeDescripition = {"Closest", "Furthest", "Weakest", "Strongest"};
    int attackMode;
    /**
     * Specialty is a special skill for current Tower.
     * The Tower can shoot Bullet with Spcialty and have special effect on Enemy with the same Specialty.
     */
    int specialty;

    // Number of shooting at one time
    int attackAmount; //MultipleShootingTower
    int currentMagic; //MagicTower
    
    List<Enemy> targetEnemies;

    public DefaultTower (
                         double damage,
                         double attackSpeed,
                         int attackMode,
                         int specialty,
                         double range,
                         int cost,
                         double recyclePrice,
                         String description,

                         String type,
                         String id,
                         boolean unique_id,
                         double x,
                         double y,
                         int collisionid,
                         String image,
                         
                         PurchaseInfo purchaseInfo) {

        super(type, id, damage, attackSpeed, range, cost, recyclePrice, description,
              unique_id, x, y, collisionid, image, 
              purchaseInfo);

        this.attackMode = attackMode;
        this.specialty = specialty;
        
        this.attackAmount = 1;
        this.currentMagic = 0;

        this.prevTime = System.currentTimeMillis();
        this.detector = new Detector<Enemy>(this.eng, Enemy.class);

        addDescription();

        targetEnemies = new ArrayList<Enemy>();
    }

    public void addDescription () {
        super.addDescription();
        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_MODE, attackModeDescripition[attackMode]);
        purchaseInfo.addToMap(GameEngineConstant.TOWER_DAMAGE, df.format(Math.abs(damage)));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_SPEED, df.format(attackSpeed));

        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_DAMAGE, df.format(Math.abs(damage * upgradeFactor)));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_ATTACK_SPEED, df.format(attackSpeed * upgradeFactor));

    }

    @Override
    public void move () {
        // check the enemies within the shooting range
        // create bullets
        // check the time after last shoot
        super.move();
        long deltaTime = (System.currentTimeMillis() - prevTime) / 1000; // convert to second

        if (attackSpeed>0&&deltaTime > 1 / attackSpeed) {
            targetEnemies.clear();
            getEnemyInRange();

            // System.out.println(targetEnemies.size());
            for (Enemy targetEnemy : targetEnemies) {
                new Bullet(targetEnemy, damage, currentMagic, specialty, "bullet", true, x, y, GameEngineConstant.BULLET_CID, "bullet");
            }
            prevTime = System.currentTimeMillis();
        }
    }

    public void getEnemyInRange () {
        List<Enemy> enemies = detector.getTargetsInRange((int) x, (int) y, (int) range);
        if (enemies.isEmpty()) return; // No enemy in range

        List<Enemy> enemiesInRange = new ArrayList<Enemy>();
        for (Enemy e : enemies) {
            // check distance between this tower and e then shoot bullets
            double dist = Math.sqrt(Math.pow(e.x - x, 2) + Math.pow(e.y - y, 2));
            if (dist < range) {
                enemiesInRange.add(e);
            }
        }

        if (enemiesInRange.isEmpty()) return; // No enemy in range, double check

        if (attackMode == 0) {
            getClosestEnemy(enemiesInRange);
        }
        else if (attackMode == 1) {
            getFurtherestEnemy(enemiesInRange);
        }
        else if (attackMode == 2) {
            getWeakestEnemy(enemiesInRange);
        }
        else if (attackMode == 3) {
            getStrongestEnemy(enemiesInRange);
        }

        // if can't find one, but there is enemy in range
        // assign one
        // if(targetEnemies.isEmpty() && !enemiesInRange.isEmpty()){
        // targetEnemies.add(enemiesInRange.get(0));
        // }
    }

    // Implement AttackMode
    public void getClosestEnemy (List<Enemy> enemiesInRange) {
        ShortestDistanceEnemyComparator comparator = new ShortestDistanceEnemyComparator(this);
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    public void getFurtherestEnemy (List<Enemy> enemiesInRange) {
        FurthestDistanceEnemyComparator comparator = new FurthestDistanceEnemyComparator(this);
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    public void getWeakestEnemy (List<Enemy> enemiesInRange) {
        WeakestEnemyComparator comparator = new WeakestEnemyComparator();
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    public void getStrongestEnemy (List<Enemy> enemiesInRange) {
        StrongestEnemyComparator comparator = new StrongestEnemyComparator();
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    private void UpdateTargetEnemies (PriorityQueue<Enemy> queue, List<Enemy> enemiesInRange) {
        for (Enemy e : enemiesInRange) {
            queue.add(e);
        }

        // System.out.println("Queue size" + queue.size());
        for (int i = 0; i < attackAmount && !queue.isEmpty(); i++) {
            targetEnemies.add(queue.remove());
        }
    }

    @Override
    public void hit (JGObject obj) {

    }
    
    @Override
    public void upgrade (GameInfo gameInfo) {
        upgrade(upgradeFactor);
        gameInfo.loseGold(upgradePrice);
    }

    public void downgrade () {
        downgrade(upgradeFactor);
    }
    
    @Override
    public void upgrade (double factor) {
        damage *= factor;
        attackSpeed *= factor;
        addDescription();
    }
    
    @Override
    public void downgrade (double factor) {
        damage /= factor;
        attackSpeed /= factor;
        addDescription();        
    }
    
    public int getAttackMode () {
        return attackMode;
    }
    
    public void setAttackMode (int mode) {
    	this.attackMode = mode;
    	addDescription();
    }
}
