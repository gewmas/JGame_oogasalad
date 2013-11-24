package gameEngine.factory.towerfactory;

import gameEngine.Constant.Constant;
import gameEngine.model.tower.MagicTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;

public class MagicTowerFactory extends DefaultTowerFactory {

    double magicFactor;
    int magic;
    
    public MagicTowerFactory (JSONObject currTower) {
        super(currTower);
        
        this.magicFactor = currTower.getDouble(Constant.TOWER_MAGIC_FACTOR);
        this.magic = currTower.getInt(Constant.TOWER_MAGIC);
        
        addDescription();
    }


    public void addDescription(){
        super.addDescription();
        purchaseInfo.addToMap(Constant.TOWER_MAGIC_FACTOR, String.valueOf(magicFactor));
    }
    
    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new MagicTower(damage, attackSpeed, attackMode, range, cost, sellPrice, description, magicFactor, magic, 
                                       type, id, true, x, y, Constant.TOWER_CID, image,
                                       purchaseInfo);
        return tower;
    }

}
