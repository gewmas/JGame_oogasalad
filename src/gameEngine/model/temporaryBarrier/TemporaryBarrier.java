package gameEngine.model.temporaryBarrier;

import java.util.HashMap;
import java.util.Map;
import jgame.JGObject;
import gameEngine.constant.GameEngineConstant;
import gameEngine.model.bullet.Bullet;
import gameEngine.model.enemy.Enemy;
import gameEngine.model.purchase.PurchaseInfo;

/**
 * Used to create a temporary barrier in the path (ex. a puddle of water that kills enemies until it dries up)
 * @author: Harris Osserman
 */

public class TemporaryBarrier extends JGObject {
    private String barrierName, image;
    private double x, y, damage;
    private String description;
    private int cost, expire;
    private HashMap<String, String> info;
    private double currentTime, endTime;

    
    public TemporaryBarrier(String name, String gfxname, double damage, int cost, int expire, String description, double x, double y) {
        super(name, true, x, y, GameEngineConstant.BULLET_CID, gfxname);
        this.description = description;
        this.barrierName = name;        
        this.image = gfxname;
        this.expire = expire;
        this.cost = cost;
        this.damage = damage;
        this.currentTime = System.currentTimeMillis();
        this.endTime = this.currentTime + 1000 * this.expire;
    }
    
    @Override
    public void move () {
        if(System.currentTimeMillis() >= endTime) {
            this.remove();
        }
    }

}
