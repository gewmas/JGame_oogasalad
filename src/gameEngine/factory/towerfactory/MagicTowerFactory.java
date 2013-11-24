package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
import gameEngine.model.tower.MagicTower;
import gameEngine.model.tower.MultipleShootingTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;

public class MagicTowerFactory extends DefaultTowerFactory {

    double magicFactor;
    int magic;
    
    public MagicTowerFactory (JSONObject currTower) {
        super(currTower);
        
        this.magicFactor = currTower.getDouble("magicFactor");
        this.magic = currTower.getInt("magic");
        
        addDescription();
    }


    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap("Magic Factor", String.valueOf(magicFactor));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new MagicTower(damage, attackSpeed, attackMode, range, cost, recyclePrice, description, magicFactor, magic, 
                                       type, id, true, x, y, Constant.TOWER_CID, image,
                                       purchaseInfo);
        return tower;
    }

}
