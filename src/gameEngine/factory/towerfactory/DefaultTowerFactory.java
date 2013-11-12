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


    public DefaultTowerFactory (JSONObject currTower) {
        this.id = currTower.getString("id");
        this.image = currTower.getString("image");

        this.x = currTower.getDouble("x");
        this.y = currTower.getDouble("y");
        this.damage = currTower.getDouble("damage");
        this.attackSpeed = currTower.getDouble("attackSpeed");
        this.range = currTower.getDouble("range");
        this.cost = currTower.getDouble("cost");
        this.recyclePrice = currTower.getDouble("recyclePrice");
    }

    @Override
    public Tower create () {
        Tower tower =
                (Tower) new DefaultTower(damage, attackSpeed, range, cost, recyclePrice, id, true,
                                         x, y, Constant.TOWER_CID, image);
        return tower;
    }


    @Override
    public Tower create (int x, int y) {
        Tower tower =
                (Tower) new DefaultTower(damage, attackSpeed, range, cost, recyclePrice, id, true,
                                         x, y, Constant.TOWER_CID, image);
        return tower;
    }

}
