package gameEngine.factory;

import gameEngine.model.Enemy;
import gameEngine.model.Level;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;

public class SpriteFactory implements FactoryInterface {
    
    private JSONArray jsonArray;
    private Level level;
    private HashMap<String, JSONObject> types;
    
    public SpriteFactory(JSONArray jsonArray, Level lvl) {
            setTypes(new HashMap<String, JSONObject>());
            this.jsonArray = jsonArray;
            this.setLevel(lvl);
            initialize();
    }
    
    @Override
    public void initialize() {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currSprite = jsonArray.getJSONObject(i);
            getTypes().put(String.valueOf(currSprite.getInt("id")), currSprite);
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
