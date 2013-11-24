
package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
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
        
        this.attackMode = currTower.getInt("attackMode");
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(Constant.TOWER_ATTACK_MODE, String.valueOf(attackMode));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new DefaultTower(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, type, id, true,
                                         x, y, Constant.TOWER_CID, image,
                                         purchaseInfo);
        return tower;
    }

    
    
  

}
