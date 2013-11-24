package gameEngine.model.tower;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import gameEngine.model.magic.ITMagicable;
import gameEngine.model.purchase.PurchaseInfo;
import jgame.JGObject;


/**
 * @author Yuhua
 * 
 *         Tower will shoot the Enemy within shooting range with Bullet
 */

public abstract class Tower extends JGObject implements PurchaseInfo, ITMagicable{
    String type;
    String id; 
    String image;

    double damage;
    double attackSpeed;

    double range;

    double x;
    double y;

    double cost;
    double upgradePrice;
    double recyclePrice;
    
    double upgradeFactor = 1.2;
    
    int currentMagic=0;

    String description;

    Map<String, String> info;

    public Tower (String type,
                  String id,
                  
                  double damage,
                  double attackSpeed,
                  double range,
                  double cost,
                  double recyclePrice,
                  String description,
                  
                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname) {
        super(id, unique_id, x, y, collisionid, gfxname);

        this.type = type;
        this.id = id;        
        this.image = gfxname;
        
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        
        this.range = range;
        this.cost = cost;
        this.upgradePrice = (int)cost/3;
        this.recyclePrice = recyclePrice;

        this.description = description;
        
        this.x = x;
        this.y = y;
        
        //add tower description
        this.info = new LinkedHashMap<String, String>();
    }
    
    public void addDescription(){
        info.put("Tower Type", type);
        info.put("Tower Name", id);
        info.put("Image", image);
        info.put("Damage", String.valueOf(damage));
        info.put("Attack Speed", String.valueOf(attackSpeed));
        info.put("Range", String.valueOf(range));
        info.put("X", String.valueOf(x));
        info.put("Y", String.valueOf(y));
        info.put("Cost", String.valueOf(cost));
        info.put("Sell Price", String.valueOf(recyclePrice));
        info.put("Description", String.valueOf(description));

        info.put("Upgrade Price", String.valueOf(upgradePrice));
        info.put("Upgrade Damage", String.valueOf(damage*upgradeFactor));
        info.put("Upgrade Attack Speed", String.valueOf(attackSpeed*upgradeFactor));        
    }
    
  

    /**
     * Tower Function Method
     */
    public abstract void sell();
    public abstract void upgrade();
    public abstract void downgrade(); //when sell BoostTower, downgrade Tower in range
    public void setAttackMode(int mode){} //not all tower need this, serve as public interface
    
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
        return id;
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
    
    public int getCurrentMagics(){
        return currentMagic;
    }
    public void setCurrentMagic(int magic){
        currentMagic=magic;
    }
    
}
