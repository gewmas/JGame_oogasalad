package gameEngine.model.warehouse;

import gameEngine.factory.towerfactory.DefaultTowerFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Yuhua
 *
 * TowerWarehouse store all possible TowerFactory that can create different kind of tower
 * View can look at this TowerWarehouse for all types of Tower
 * 
 */

public class TowerWarehouse implements Warehouse{

    private JSONArray jsonArray;
    Map<String, TowerFactory> towers;
    
    public TowerWarehouse(Parser parser){
        jsonArray = parser.getJSONArray("towerType");
        towers = new HashMap<String,TowerFactory>();
        
        // loop through all kinds of towers
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currTower = jsonArray.getJSONObject(i);
            String name = currTower.getString("id");
            
            /*
             * Now it create DefaultTowerFactory no matter what
             * Later should create different TowerFactory according to the Json File 
             */
            TowerFactory towerFactory = (TowerFactory) new DefaultTowerFactory(currTower);
            
            towers.put(name, towerFactory);
        }
    }
    
    public void create(String name){
        TowerFactory towerFactory = towers.get(name);
        towerFactory.createTower();
    }
    
}
