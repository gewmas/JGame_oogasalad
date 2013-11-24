package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
import gameEngine.model.tower.BoostTower;
import gameEngine.model.tower.MagicTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;


public class BoostTowerFactory extends TowerFactory {
    
    double boostFactor;

    public BoostTowerFactory (JSONObject currTower) {
        super(currTower);
        
        this.boostFactor = currTower.getDouble(Constant.TOWER_BOOST_FACTOR);
        
        addDescription();
    }

    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(Constant.TOWER_BOOST_FACTOR, String.valueOf(boostFactor));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new BoostTower(damage, attackSpeed, range, cost, sellPrice, description, boostFactor, 
                                       type, id, true, x, y, Constant.TOWER_CID, image,
                                       purchaseInfo);
        return tower;
    }

    

}
