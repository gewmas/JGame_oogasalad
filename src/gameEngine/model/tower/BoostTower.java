package gameEngine.model.tower;

/**
 * 
 * @author Yuhua
 *
 * BoostTower will boost several properties of Tower in range by boostFactor
 * Reverse back when the BoostTower is sold
 */

public class BoostTower extends Tower {

    public BoostTower (String name,
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
