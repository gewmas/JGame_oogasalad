package gameEngine.model.tile;

import jgame.JGObject;
import gameEngine.Constant.Constant;
import gameEngine.model.purchase.PurchaseInfo;

/**
 * Used to create a temporary barrier in the path (ex. a puddle of water that kills enemies until it dries up)
 * @author: Harris Osserman
 */

public class TemporaryBarrier extends JGObject implements PurchaseInfo{
    private String barrierName, image;
    private double damage, attackSpeed, range, x, y, cost, recyclePrice;
    private static final String DESCRIPTION = "Buy a puddle of water to drown your enemies!  The sun is hot, so let's hope it doesn't evaporate too quickly";
    private static final int COST = 10;
    private Tile tile;

    
    public TemporaryBarrier(String name, boolean unique_id, String gfxname, Tile tile) {
        super(name, unique_id, tile.getCenterX(), tile.getCenterY(), Constant.BULLET_CID, gfxname);
        
        this.tile = tile;
        this.barrierName = name;        
        this.image = gfxname;
    }
        
    @Override
    public String getItemName () {
        return barrierName;
    }

    @Override
    public double getX () {
        return tile.getCenterX();
    }

    @Override
    public double getY () {
        return tile.getCenterY();
    }

    @Override
    public double getDamage () {
        return 0;
    }

    @Override
    public double getAttackSpeed () {
        return 0;
    }

    @Override
    public int getAttackMode () {
        return 0;
    }

    @Override
    public double getRange () {
        return 0;
    }

    @Override
    public int getCost () {
        return COST;
    }

    @Override
    public double getRecyclePrice () {
        //You can't recycle a temporary barrier (ex. a puddle of water)
        return 0;
    }

    @Override
    public String getDescription () {
        return DESCRIPTION;
    }

    @Override
    public String getImage () {
        return image;
    }

}
