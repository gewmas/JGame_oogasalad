package gameEngine.model.tower;

/**
 * 
 * @author Yuhua
 *
 * Freeze Tower would slow down all enemies in range by slowFactor
 * The enemies get back normal speed when out of range
 * 
 */
public class FreezeTower extends Tower {

    public FreezeTower (String name,
                        boolean unique_id,
                        double x,
                        double y,
                        int collisionid,
                        String gfxname) {
        super(name, unique_id, x, y, collisionid, gfxname);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void sell () {
        // TODO Auto-generated method stub

    }

    @Override
    public void upgrade () {
        // TODO Auto-generated method stub

    }

    @Override
    public void setAttackMode (int attackMode) {
        // TODO Auto-generated method stub

    }

}
