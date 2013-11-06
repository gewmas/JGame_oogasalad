package gameEngine.factory;

import java.util.HashMap;
import gameEngine.model.Enemy;
import gameEngine.model.Level;
import gameEngine.model.Tower;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * 
 * @author Yuhua
 * TowerFactory create library of Tower for View to choose
 *
 */
public class TowerFactory extends SpriteFactory {
    
    public TowerFactory(JSONObject jsonObject, Level level) {
        super(jsonObject, level);
    }

    public Tower createFromId(String id)
    {
        JSONObject currTower = getTypes().get(id);
        Tower tower = new Tower(currTower.getDouble("damage"), currTower.getDouble("attackSpeed"), currTower.getDouble("range"), currTower.getDouble("cost"), currTower.getDouble("recyclePrice"), getLevel(), id, true, 100.0, 100.0, 1, currTower.getString("img"));
        return tower;
    }


}
