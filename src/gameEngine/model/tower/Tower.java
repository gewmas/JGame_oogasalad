package gameEngine.model.tower;

import java.text.DecimalFormat;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.GameInfo;
import gameEngine.model.magic.ITMagicable;
import gameEngine.model.purchase.PurchaseInfo;
import jgame.JGObject;


/**
 * @author Yuhua
 * 
 * Tower will shoot the Enemy within shooting range with Bullet
 */

public abstract class Tower extends JGObject implements ITMagicable {
    //    private String type;
    //    private String id;
    //    private String image;

    protected double damage;
    protected double attackSpeed;   
    protected double range;
   
    protected double x;
    protected double y;

    protected int cost;
    protected int upgradePrice;
    protected int sellPrice;

    protected double upgradeFactor = 1.2;

    protected int currentMagic = 0;

    private String description;

    protected PurchaseInfo purchaseInfo;

    DecimalFormat df = new DecimalFormat("#.#");
    
    public Tower (String type,
                  String id,

                  double damage,
                  double attackSpeed,
                  double range,
                  int cost,
                  double recyclePrice,
                  String description,

                  boolean unique_id,
                  double x,
                  double y,
                  int collisionid,
                  String gfxname,

                  PurchaseInfo purchaseInfo) {
        super(id, unique_id, x, y, collisionid, gfxname);

        //        this.type = type;
        //        this.id = id;
        //        this.image = gfxname;

        this.damage = damage;
        this.attackSpeed = attackSpeed;

        this.range = range;
        this.cost = cost;
        this.upgradePrice = cost / 3;
        this.sellPrice = (int)recyclePrice;

        this.description = description;

        this.x = x;
        this.y = y;

        this.purchaseInfo = purchaseInfo;
    }

    public void addDescription () {
        
        purchaseInfo.addToMap(GameEngineConstant.TOWER_RANGE, String.valueOf(range));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_SELL_PRICE, String.valueOf(sellPrice));
        purchaseInfo.addToMap(GameEngineConstant.PURCHASE_INFO_DESCRIPTION, String.valueOf(description));
        purchaseInfo.addToMap(GameEngineConstant.TOWER_UPGRADE_PRICE, String.valueOf(upgradePrice));
    }

    /**
     * Tower Function Method
     */
    public void sell (GameInfo gameInfo){
        gameInfo.addGold(sellPrice);
        this.remove();
    }
    public abstract void upgrade (GameInfo gameInfo);

    public void setAttackMode (int mode) {
    } // not all tower need this, serve as public interface

    /**
     * TowerInfo Getter Method
     */
    public PurchaseInfo getPurchaseInfo () {
        return purchaseInfo;
    }

    /**
     * Magic Method
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
    public int getCurrentMagics () {
        return currentMagic;
    }

    @Override
    public void setCurrentMagic (int magic) {
        currentMagic = magic;
    }
    
    @Override
    public double changePercentSpeed(double offset){
       double change=0;
        if(offset<1&&offset>-1)
            change=attackSpeed*offset;
        else
            change=offset;
        attackSpeed+=change;
        return change;   
    }
    
    @Override
    public double changeSpeed(double offset){
        double change=attackSpeed*offset;
        attackSpeed+=change;
        return change;
    }
    
    @Override
    public double changeRange(double offset){
        range+=offset;
        return offset;  
    }
    
    @Override
    public double changePercentRange(double percent){
        double change=range*percent;
        range+=change;
        return change;
    }
    
}
