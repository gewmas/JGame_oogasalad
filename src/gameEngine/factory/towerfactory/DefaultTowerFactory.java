
package gameEngine.factory.towerfactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.tower.DefaultTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;


/**
 * 
 * @author Yuhua Fabio
 *         TowerFactory can create different types of Tower when called by the create() method
 * 
 */

public class DefaultTowerFactory extends TowerFactory {
	int attackMode;
	/**
     * Specialty is a special skill for current Tower.
     * The Tower can shoot Bullet with Spcialty and have special effect on Enemy with the same Specialty.
     */
	int specialty;

	public DefaultTowerFactory (JSONObject currTower) {
		super(currTower);

		this.attackMode = currTower.getInt(GameEngineConstant.TOWER_ATTACK_MODE);
		try{
			this.specialty = currTower.getInt(GameEngineConstant.TOWER_SPECIALTY);
		}catch(Exception e){
			this.specialty = 0;
		}

		addDescription();
	}

	public void addDescription(){
		super.addDescription();
		purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_MODE, String.valueOf(attackMode));
		purchaseInfo.addToMap(GameEngineConstant.TOWER_SPECIALTY, String.valueOf(specialty));
		
		purchaseInfo.addToMap(GameEngineConstant.TOWER_DAMAGE, String.valueOf(damage));
		purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_SPEED, String.valueOf(attackSpeed));
		
	}

	@Override
	public Tower create (int x, int y) {
		Tower tower =
				(Tower) new DefaultTower(damage, attackSpeed, attackMode, specialty, range, cost, sellPrice, description, type, id, true,
						x, y, GameEngineConstant.TOWER_CID, image,
						purchaseInfo);
		return tower;
	}

}
