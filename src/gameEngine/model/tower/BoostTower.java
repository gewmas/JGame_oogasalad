package gameEngine.model.tower;

import gameEngine.model.Detector;
import gameEngine.model.enemy.Enemy;

/**
 * 
 * @author Yuhua
 *
 * BoostTower will boost several properties of Tower in range by boostFactor
 * Reverse back when the BoostTower is sold
 */

public class BoostTower extends Tower {

	private Detector<DefaultTower> detector;
	private double boostFactor;

	public BoostTower (String name,
			boolean unique_id,
			double x,
			double y,
			int collisionid,
			String gfxname) {
		super(name, unique_id, x, y, collisionid, gfxname);

		this.detector = new Detector<DefaultTower>(this.eng, DefaultTower.class);
	}

	@Override
	public void sell () {
		// TODO Auto-generated method stub

	}

	@Override
	public void upgrade () {
		boostFactor += 1.0;
	}

	@Override
	public void downgrade(){
		boostFactor -= 1.0;
	}

	@Override
	public void upgrade(double factor) {
		boostFactor *= factor;
	}

	@Override
	public void downgrade(double factor) {
		boostFactor /= factor;
	}

	@Override
	public void setAttackMode (int attackMode) {
		// TODO Auto-generated method stub

	}



}
