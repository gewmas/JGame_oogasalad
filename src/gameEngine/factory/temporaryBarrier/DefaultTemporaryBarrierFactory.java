package gameEngine.factory.temporaryBarrier;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.temporaryBarrier.TemporaryBarrier;
import gameEngine.model.tower.DefaultTower;
import gameEngine.model.tower.Tower;
/**
 * @author Harris Osserman
 * Child class of TemporaryBarrierFactory
 * It implements the create() method
 */
public class DefaultTemporaryBarrierFactory extends TemporaryBarrierFactory {

    public DefaultTemporaryBarrierFactory (String name,
                                           String gfxname,
                                           int damage,
                                           int cost,
                                           int expire,
                                           String description) {
        super(name, gfxname, damage, cost, expire, description);
    }

    @Override
    public TemporaryBarrier create (int x, int y) {
        TemporaryBarrier temporaryBarrier =
             new TemporaryBarrier(name, gfxname, damage, cost, expire, 
                                  description, x, y);
        return temporaryBarrier;
    }

}
