package temporary.buff;

import temporary.TemporaryEnemy;

public class SlowBuff extends Buff {

    public SlowBuff (String name,
                     boolean unique_id,
                     double x,
                     double y,
                     int collisionid,
                     String gfxname,
                     int expire,
                     IBuffable target) {
        super(name, unique_id, x, y, collisionid, gfxname, expire, target );
        // TODO Auto-generated constructor stub
    }

    @Override
    public void buffOn (IBuffable target) {
        ((ISlowBuffable) target).slowBuffOn(this,-5);
        
    }

    @Override
    public void buffOff (IBuffable target) {
        ((ISlowBuffable) target).slowBuffOff(this,5);
    }

}
