package gameEngine.model.tower;

import gameEngine.model.purchase.PurchaseInfo;
import jgame.JGObject;


/**
 * @author Yuhua
 * 
 *         Tower will shoot the Enemy within shooting range with Bullet
 */

public abstract class Tower extends JGObject implements PurchaseInfo{
    String towerName;
    String image;

    double damage;
    double attackSpeed;

    /**
     * AttackMode include 
     * 0 - shoot the closest enemy
     * 1 - shoot the farthest enemy
     * 2 - shoot weakest enemy with least life
     * 3 - shoot strongest enemy with most life
     */
    int attackMode;

    double range;

    double x;
    double y;

    double cost;
    double recyclePrice;

    String description;



    public Tower (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);


        this.towerName = name;        
        this.image = gfxname;
        
    }

    /**
     * Tower Function Method
     */
    public abstract void sell();
    public abstract void upgrade();
    public abstract void setAttackMode (int attackMode);
    
    /**
     * TowerInfo Getter Method
     */
    @Override
    public double getX () {
        return x;
    }

    @Override
    public double getY () {
        return y;
    }

    @Override
    public String getItemName(){
        return towerName;
    }

    @Override
    public String getDescription () {
        return description;
    }

    @Override
    public double getDamage (){
        return damage;
    }

    @Override
    public double getAttackSpeed (){
        return attackSpeed;
    }

    public int getAttackMode() {
        return attackMode;
    }

    @Override
    public double getRange (){
        return range;
    }
    @Override
    public double getRecyclePrice (){
        return recyclePrice;
    }

    @Override
    public int getCost () {
        return (int)cost;
    }

    @Override
    public String getImage () {
        return image;
    }
    
    
}
