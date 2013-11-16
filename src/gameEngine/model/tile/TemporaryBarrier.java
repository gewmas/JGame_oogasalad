package gameEngine.model.tile;

import jgame.JGObject;
import gameEngine.model.purchase.PurchaseInfo;

/**
 * Used to create a temporary barrier in the path (ex. a puddle of water that kills enemies until it dries up)
 * @author: Harris Osserman
 */

public class TemporaryBarrier extends JGObject implements PurchaseInfo{
    private String barrierName, image;
    private double damage, attackSpeed, range, x, y, cost, recyclePrice;
    private String description;
    
    public TemporaryBarrier(String name, boolean unique_id, double x, double y, int collisionid, String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        

        this.barrierName = name;        
        this.image = gfxname;
    }
        
    @Override
    public String getItemName () {
        return barrierName;
    }

    @Override
    public double getX () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getY () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getDamage () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getAttackSpeed () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAttackMode () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRange () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getCost () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public double getRecyclePrice () {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getDescription () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getImage () {
        // TODO Auto-generated method stub
        return null;
    }

}
