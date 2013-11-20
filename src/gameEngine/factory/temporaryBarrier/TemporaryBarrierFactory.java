package gameEngine.factory.temporaryBarrier;

import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.tile.TemporaryBarrier;
import gameEngine.model.tile.Tile;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import helpers.Coordinate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author Harris
 * Stores the data for a temporary barrier.  
 * It has an abstract method create(), which is called by a child class to create a new temporary barrier
 */
public abstract class TemporaryBarrierFactory implements PurchaseInfo{
    private String barrierName, image;
    private double x, y;
    private String description;
    private int cost, expire, damage;
    private HashMap<String, String> info;
        
    public TemporaryBarrierFactory (String name, String gfxname, int damage, int cost, int expire, String description) {
        this.description = description;
        this.barrierName = name;        
        this.image = gfxname;
        this.expire = expire;
        this.cost = cost;
        this.damage = damage;
        info = new HashMap<String, String>();
        info.put("description", this.description);
        info.put("name", this.barrierName);
        info.put("image", this.image);
        info.put("cost", this.cost + "");
        info.put("damage", this.damage + "");
        info.put("expire", this.expire + "");
    }

    public abstract TemporaryBarrier create (int x, int y);
        
    public int getExpire() {
        return expire;
    }
    
    @Override
    public String getItemName () {
        return barrierName;
    }
    
    @Override
    public double getDamage () {
        return damage;
    }
    
    @Override
    public double getAttackSpeed () {
        return 0;
    }
    
    @Override
    public double getRange () {
        return 0;
    }
    
    @Override
    public int getCost () {
        return cost;
    }
    
    @Override
    public double getRecyclePrice () {
        //You can't recycle a temporary barrier (ex. a puddle of water or a ball of fire)
        return 0;
    }
    
    @Override
    public String getDescription () {
        return description;
    }
    
    @Override
    public String getImage () {
        return image;
    }
    
    @Override
    public Map<String, String> getInfo () {
        return info;
    }

}

