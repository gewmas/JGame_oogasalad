package gameEngine.factory.towerfactory;

import gameEngine.constant.GameEngineConstant;
import gameEngine.model.tower.MultipleShootingTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;


public class MultipleShootingTowerFactory extends DefaultTowerFactory {
    int attackAmount;

    public MultipleShootingTowerFactory (JSONObject currTower) {
        super(currTower);

        attackAmount = currTower.getInt(GameEngineConstant.TOWER_ATTACK_AMOUNT);

        addDescription();
    }

    @Override
    public void addDescription () {
        super.addDescription();
        purchaseInfo.addToMap(GameEngineConstant.TOWER_ATTACK_AMOUNT, String.valueOf(attackAmount));
    }

    @Override
    public Tower create (int x, int y) {
        Tower tower =
                new MultipleShootingTower(damage, attackSpeed, attackMode, attackAmount, range,
                                          cost, sellPrice, description,
                                          type, id, true,
                                          x, y, GameEngineConstant.TOWER_CID, image,
                                          purchaseInfo);
        return tower;
    }
}
