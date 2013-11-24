package gameEngine.factory.temporaryBarrier;

import gameEngine.model.Model;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.temporaryBarrier.TemporaryBarrier;
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
        
    @Override
    public Map<String, String> getInfo () {
        return info;
    }
    
    @Override
    public String getObjectType () {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName () {
        // TODO Auto-generated method stub
        return null;
    }

}

