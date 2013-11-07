package gameEngine.factory.towerfactory;

import java.util.HashMap;
import gameEngine.model.Enemy;
import gameEngine.model.tower.Tower;
import gameEngine.model.tower.DefaultTower;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * 
 * @author Yuhua Fabio
 * TowerFactory can create different types of Tower when called by the create() method
 * 
 */
public class DefaultTowerFactory implements TowerFactory{
    
    String id;
    String image; 
    
    double x;
    double y;
    
    double damage;
    double attackSpeed;
    double range;

    double cost;
    double recyclePrice;
    
    public DefaultTowerFactory(JSONObject currTower) {
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
    public Tower createTower () {
        Tower tower = (Tower) new DefaultTower(damage, attackSpeed, range, cost, recyclePrice, id, true, x, y, 1, image);
        return tower;
    }

    



}
