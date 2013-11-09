package gameEngine.factory.towerfactory;

import gameAuthoring.Writeable;
import gameEngine.Constant.Constant;
import gameEngine.model.tower.DefaultTower;
import gameEngine.model.tower.Tower;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.io.File;
import java.io.IOException;
import java.nio.CharBuffer;


/**
 * 
 * @author Yuhua Fabio
 *         TowerFactory can create different types of Tower when called by the create() method
 * 
 */
public class DefaultTowerFactory implements TowerFactory, Readable, Writeable {

    String id;
    String image;

    double x;
    double y;

    double damage;
    double attackSpeed;
    double range;

    double cost;
    double recyclePrice;

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
    public int read (CharBuffer arg0) throws IOException {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public File write () {
        // TODO Auto-generated method stub
        return null;
    }

}
