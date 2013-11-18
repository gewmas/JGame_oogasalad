package gameEngine.model.tower;

import java.util.HashMap;
import java.util.Map;
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

    Map<String, String> info;

    public Tower (String name,
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);


        this.towerName = name;        
        this.image = gfxname;
        
        this.info = new HashMap<String, String>();
        
    }

    /**
     * Tower Function Method
     */
    public abstract void sell();
    public abstract void upgrade();
    public abstract void downgrade(); 
    public abstract void upgrade(double factor);
    public abstract void downgrade(double factor); //when sell BoostTower, downgrade Tower in range
    public abstract void setAttackMode (int attackMode);
    
    /**
     * TowerInfo Getter Method
     */
    
    public double getX () {
        return x;
    }

    public double getY () {
        return y;
    }

    public String getItemName(){
        return towerName;
    }

    public String getDescription () {
        return description;
    }

    public double getDamage (){
        return damage;
    }

    public double getAttackSpeed (){
        return attackSpeed;
    }

    public int getAttackMode() {
        return attackMode;
    }

    public double getRange (){
        return range;
    }
    public double getRecyclePrice (){
        return recyclePrice;
    }

    public int getCost () {
        return (int)cost;
    }

    public String getImage () {
        return image;
    }
    
    
}
