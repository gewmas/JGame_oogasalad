package gameEngine.factory.temporaryBarrier;
import gameEngine.model.purchase.PurchaseInfo;
import gameEngine.model.temporaryBarrier.TemporaryBarrier;

/**
 * @author Harris
 * Stores the data for a temporary barrier.  
 * It has an abstract method create(), which is called by a child class to create a new temporary barrier
 */
public abstract class TemporaryBarrierFactory extends PurchaseInfo{
    protected static final String TYPE = "Temporary Barrier";
    protected String name, gfxname, description;
    protected int damage, cost, expire;
    
    public TemporaryBarrierFactory (String name, String gfxname, int damage, int cost, int expire, String description) {
        super(TYPE, name, gfxname, description, cost);
        super.addToMap("Damage", String.valueOf(damage));
        super.addToMap("Expire", String.valueOf(expire));
        this.name = name;
        this.gfxname = gfxname;
        this.description = description;
        this.damage = damage;
        this.cost = cost;
        this.expire = expire;
        this.description = description;
    }

    public abstract TemporaryBarrier create (int x, int y);
        

}

