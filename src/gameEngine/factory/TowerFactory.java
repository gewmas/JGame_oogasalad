package gameEngine.factory;

import java.util.HashMap;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;

/**
 * 
 * @author Yuhua
 * TowerFactory create library of Tower for View to choose
 *
 */
public class TowerFactory {
    private JSONObject jsonObject;
    private HashMap<String, JSONArray> towerType;
    
    public TowerFactory(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
    }
    
    public void initialize() {
        
            
    }
}
