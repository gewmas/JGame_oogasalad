package gameEngine.factory;

import gameEngine.model.Enemy;
import gameEngine.model.Level;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;

public class SpriteFactory implements FactoryInterface {
    
    private JSONObject jsonObject;
    private Level level;
    private HashMap<String, JSONObject> types;
    
    public SpriteFactory(JSONObject jsonObject, Level lvl) {
            setTypes(new HashMap<String, JSONObject>());
            this.jsonObject = jsonObject;
            this.setLevel(lvl);
            initialize();
    }
    
    @Override
    public void initialize() {
        JSONArray enemyList = jsonObject.getJSONArray("enemyType");
        for (int i = 0; i < enemyList.length(); i++) {
            JSONObject currEnemy = enemyList.getJSONObject(i);
            getTypes().put(currEnemy.getString("id"), currEnemy);
        }
    }
    
    public int getNumberOfEnemyTypes() 
    {
        return getTypes().size();
    }

    public void setTypes (HashMap<String, JSONObject> types) {
        this.types = types;
    }

    public HashMap<String, JSONObject> getTypes () {
        return types;
    }

    public void setLevel (Level level) {
        this.level = level;
    }

    public Level getLevel () {
        return level;
    }

}
