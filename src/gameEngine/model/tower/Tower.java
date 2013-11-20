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
        
        //add tower description
        this.info = new HashMap<String, String>();
    }
    
    public void addDescription(){
        info.put("Tower Name", towerName);
        info.put("Image", image);
        info.put("Damage", String.valueOf(damage));
        info.put("Attack Speed", String.valueOf(attackSpeed));
        info.put("Range", String.valueOf(range));
        info.put("X", String.valueOf(x));
        info.put("Y", String.valueOf(y));
        info.put("Cost", String.valueOf(cost));
        info.put("Sell Price", String.valueOf(recyclePrice));
        info.put("Description", String.valueOf(description));
    }

    /**
     * Tower Function Method
     */
    public abstract void sell();
    public abstract void upgrade();
    public abstract void downgrade(); //when sell BoostTower, downgrade Tower in range
    
    /**
     * TowerInfo Getter Method
     */
    public Map<String, String> getInfo () {
        return info;
    }
    
    @Deprecated
    public double getX () {
        return x;
    }

    @Deprecated
    public double getY () {
        return y;
    }

    @Deprecated
    public String getItemName(){
        return towerName;
    }

    @Deprecated
    public String getDescription () {
        return description;
    }

    @Deprecated
    public double getDamage (){
        return damage;
    }

    @Deprecated
    public double getAttackSpeed (){
        return attackSpeed;
    }

//    @Deprecated
//    public int getAttackMode() {
//        return attackMode;
//    }

    @Deprecated
    public double getRange (){
        return range;
    }
    @Deprecated
    public double getRecyclePrice (){
        return recyclePrice;
    }
    @Deprecated
    public int getCost () {
        return (int)cost;
    }
    @Deprecated
    public String getImage () {
        return image;
    }
    
    
}
