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
                     TemporaryEnemy enemy) {
        super(name, unique_id, x, y, collisionid, gfxname, expire, enemy);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void buffOn (TemporaryEnemy enemy) {
        enemy.changeSpeed(-5);
    }

    @Override
    public void buffOff (TemporaryEnemy enemy) {
        enemy.changeSpeed(5);
    }

}
