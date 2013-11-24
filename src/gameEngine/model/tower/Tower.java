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

public abstract class Tower extends JGObject implements ITMagicable{
    private String type;
    private String id; 
    private String image;

    protected double damage;
    protected double attackSpeed;

    protected double range;

    protected double x;
    protected double y;

    protected double cost;
    protected double upgradePrice;
    protected double recyclePrice;
    
    protected double upgradeFactor = 1.2;
    
    protected int currentMagic=0;

    private String description;

    protected PurchaseInfo purchaseInfo;

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
                  String gfxname,
                  
                  PurchaseInfo purchaseInfo) {
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
        
        this.purchaseInfo = purchaseInfo;
    }
    
    public void addDescription(){
        purchaseInfo.addToMap("Damage", String.valueOf(damage));
        purchaseInfo.addToMap("Attack Speed", String.valueOf(attackSpeed));
        purchaseInfo.addToMap("Range", String.valueOf(range));
        purchaseInfo.addToMap("Sell Price", String.valueOf(recyclePrice));
        purchaseInfo.addToMap("Description", String.valueOf(description));

        purchaseInfo.addToMap("Upgrade Price", String.valueOf(upgradePrice));
        purchaseInfo.addToMap("Upgrade Damage", String.valueOf(damage*upgradeFactor));
        purchaseInfo.addToMap("Upgrade Attack Speed", String.valueOf(attackSpeed*upgradeFactor));        
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
    public PurchaseInfo getPurchaseInfo () {
        return purchaseInfo;
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
