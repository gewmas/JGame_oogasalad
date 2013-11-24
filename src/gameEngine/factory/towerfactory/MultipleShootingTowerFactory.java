package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
import gameEngine.model.tower.DefaultTower;
import gameEngine.model.tower.MultipleShootingTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;

public class MultipleShootingTowerFactory extends DefaultTowerFactory {
    int attackAmount;
    
    public MultipleShootingTowerFactory (JSONObject currTower) {
        super(currTower);
        
        this.attackAmount = currTower.getInt(Constant.TOWER_ATTACK_AMOUNT);
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(Constant.TOWER_ATTACK_AMOUNT, String.valueOf(attackAmount));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new MultipleShootingTower(damage, attackSpeed, attackMode, attackAmount, range, cost, sellPrice, description, 
                                                  type, id, true,
                                         x, y, Constant.TOWER_CID, image,
                                         purchaseInfo);
        return tower;
    }
}
