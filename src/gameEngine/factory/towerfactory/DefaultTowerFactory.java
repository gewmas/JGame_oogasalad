
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

    public DefaultTowerFactory (JSONObject currTower) {
        super(currTower);
        
        this.attackMode = currTower.getInt(GameEngineConstant.TOWER_ATTACK_MODE);
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_MODE, String.valueOf(attackMode));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new DefaultTower(damage, attackSpeed, attackMode, range, cost, sellPrice, description, type, id, true,
                                         x, y, GameEngineConstant.TOWER_CID, image,
                                         purchaseInfo);
        return tower;
    }

}
