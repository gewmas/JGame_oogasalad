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
public class EnemyFactory implements FactoryInterface {

    private JSONObject jsonObject;
    
    private HashMap<String, JSONObject> enemyTypes;
    
    public EnemyFactory(JSONObject jsonObject) {
            enemyTypes = new HashMap<String, JSONObject>();
            this.jsonObject = jsonObject;
            initialize();
    }
    
    @Override
    public void initialize() {
        JSONArray enemyList = jsonObject.getJSONArray("enemyType");
        for (int i = 0; i < enemyList.length(); i++) {
            JSONObject currEnemy = enemyList.getJSONObject(i);
            enemyTypes.put(currEnemy.getString("id"), currEnemy);
        }
    }
    
    public Enemy createEnemyWithId(String id)
    {
        JSONObject currEnemy = enemyTypes.get(id);
        Enemy enemy = new Enemy(currEnemy.get("gold"), currEnemy.get("life"), currEnemy.get("speed"), this, id, true, 150.0, 150.0, 2.0, id);
        return enemy;
    }

    

}
