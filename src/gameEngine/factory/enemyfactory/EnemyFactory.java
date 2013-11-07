package gameEngine.factory;

import java.util.HashMap;
import gameEngine.model.Enemy;
import gameEngine.model.Model;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * @Author Fabio Berger
 */
public class EnemyFactory extends SpriteFactory {

    
    public EnemyFactory(JSONArray jsonArray) {
            super(jsonArray);
    }
    
//    public Enemy createFromId(String id)
//    {
//        JSONObject currEnemy = getTypes().get(id);
//        Enemy enemy = new Enemy(currEnemy.getDouble("gold"), currEnemy.getDouble("life"), currEnemy.getDouble("speed"), id, true, 50.0, 50.0, 2, currEnemy.getString("image"));
//        return enemy;
//    }
    

}
