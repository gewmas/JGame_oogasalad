package gameEngine.model.tower;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import gameEngine.Constant.Constant;
import gameEngine.model.Detector;
import gameEngine.model.buff.IBuffable;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.enemy.comparator.FurthestDistanceEnemyComparator;
import gameEngine.model.enemy.comparator.ShortestDistanceEnemyComparator;
import gameEngine.model.enemy.comparator.StrongestEnemyComparator;
import gameEngine.model.enemy.comparator.WeakestEnemyComparator;
import jgame.JGObject;

/**
 * 
 * @author Yuhua
 *
 * DefaultTower can attack enemy, those who also enemies should extends DefaultTower
 * Like MultipleShootTower which shoot multiple Bullet at one time
 * However, Tower like BoostTower and FreezeTower would not extends DefaultTower 
 * because they will not shoot, but change properties
 * 
 */
public class DefaultTower extends Tower implements IBuffable{

    long prevTime;
    private Detector<Enemy> detector;

    /**
     * AttackMode include 
     * 0 - shoot the closest enemy
     * 1 - shoot the farthest enemy
     * 2 - shoot weakest enemy with least life
     * 3 - shoot strongest enemy with most life
     */
    int attackMode;

    //Number of shooting at one time
    int attackAmount;
    List<Enemy> targetEnemies;


    public DefaultTower (
                         double damage,
                         double attackSpeed,
                         int attackMode,
                         double range,
                         double cost,
                         double recyclePrice,
                         String description,

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
        this.attackAmount = 1;
        
        this.range = range;
        this.cost = cost;
        this.recyclePrice = recyclePrice;

        this.x = x;
        this.y = y;

        this.prevTime = System.currentTimeMillis();
        this.detector = new Detector<Enemy>(this.eng, Enemy.class);

        this.description = description;


        addDescription();

        targetEnemies = new ArrayList<Enemy>();
    }

    public void addDescription(){
        super.addDescription();
        info.put("Attack Mode", String.valueOf(attackMode));
    }

    @Override
    public void move () {
        // check the enemies within the shooting range
        // create bullets
        // check the time after last shoot
        long deltaTime = (System.currentTimeMillis() - prevTime) / 1000; // convert to second

        if (deltaTime > 1 / attackSpeed) {
            targetEnemies.clear();
            getEnemyInRange();

            //            System.out.println(targetEnemies.size());
            for(Enemy targetEnemy : targetEnemies){
                new Bullet(targetEnemy, damage, "bullet", true, x, y, Constant.BULLET_CID, "bullet");
            }
            prevTime = System.currentTimeMillis();
        }
    }

    public void getEnemyInRange(){
        List<Enemy> enemies = detector.getTargetsInRange((int) x, (int) y, (int) range);
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
        //    	if(targetEnemies.isEmpty() && !enemiesInRange.isEmpty()){
        //    	    targetEnemies.add(enemiesInRange.get(0));
        //    	}
    }



    //Implement AttackMode
    public void getClosestEnemy(List<Enemy> enemiesInRange){
        ShortestDistanceEnemyComparator comparator = new ShortestDistanceEnemyComparator(this);
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    public void getFurtherestEnemy(List<Enemy> enemiesInRange){
        FurthestDistanceEnemyComparator comparator = new FurthestDistanceEnemyComparator(this);
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }


    public void getWeakestEnemy(List<Enemy> enemiesInRange){
        WeakestEnemyComparator comparator = new WeakestEnemyComparator();
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    public void getStrongestEnemy(List<Enemy> enemiesInRange){
        StrongestEnemyComparator comparator = new StrongestEnemyComparator();
        PriorityQueue<Enemy> queue = new PriorityQueue<Enemy>(attackAmount, comparator);

        UpdateTargetEnemies(queue, enemiesInRange);
    }

    private void UpdateTargetEnemies (PriorityQueue<Enemy> queue, List<Enemy> enemiesInRange) {
        for(Enemy e : enemiesInRange){
            queue.add(e);
        }

        //        System.out.println("Queue size" + queue.size());
        for(int i = 0; i < attackAmount && !queue.isEmpty(); i++){
            targetEnemies.add(queue.remove());
        }
    }

    @Override
    public void hit (JGObject obj) {

    }

    @Override
    public void sell () {
        // level.getGameInfo().addGold((int)recyclePrice);
        // level.getTowers().remove(this);
        this.remove();
    }

    @Override
    public void upgrade () {
        damage++;
        attackSpeed+=1;
    }

    @Override
    public void downgrade(){
        damage--;
        attackSpeed-=1;
    }

    @Override
    public void upgrade(double factor) {
        damage *= factor;
        attackSpeed *= factor;
    }

    @Override
    public void downgrade(double factor) {
        damage /= factor;
        attackSpeed /= factor;
    }

    @Override
    public void setAttackMode (int attackMode) {
        this.attackMode = attackMode;
    }

    @Override
    public Map<String, String> getInfo () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getImmuneBuffs () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAttackBuffs () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCurrentBuffs () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void changeCurrentBuffs (int buff) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeLife (double lifePercent) {
        // TODO Auto-generated method stub

    }

    @Override
    public void changeSpeed (double speedPercent) {
        this.attackSpeed *= speedPercent;
    }

    @Override
    public void changeAttack (double attackPercent) {
        // TODO Auto-generated method stub

    }

    public int getAttackMode () {
        // TODO Auto-generated method stub
        return 0;
    }

}
