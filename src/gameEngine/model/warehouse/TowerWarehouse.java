package gameEngine.model.warehouse;

import gameEngine.factory.towerfactory.DefaultTowerFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.GameInfo;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


/**
 * 
 * @author Jiaran
 *         similar to tower warehouse. Using to create all types of enemy using enemy id.
 * 
 */

public class TowerWarehouse implements Warehouse {

    private JSONArray jsonArray;
    Map<String, TowerFactory> towers;

    public TowerWarehouse (Parser parser) {
        jsonArray = parser.getJSONArray("towerType");
        towers = new HashMap<String, TowerFactory>();

        // loop through all kinds
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

    public void create (String name) {
        TowerFactory towerFactory = towers.get(name);
        towerFactory.create();
    }
    // Jiaran edit, something's wrong please contact.
    //wenxin I cast it to int
    public boolean create(int x, int y, String name,GameInfo g){
        TowerFactory towerFactory = towers.get(name);
        if(towerFactory.getCost()<=g.getGold()){
            g.loseGold((int) towerFactory.getCost());
            towerFactory.create(x, y);
            return true;
        }
        else
            return false;
    }
    
    public List<TowerFactory> getTowerFactory (){
        List<TowerFactory> towerFactory = new ArrayList<TowerFactory>();
        
        for(Entry<String, TowerFactory> entry : towers.entrySet()){
            towerFactory.add(entry.getValue());
        }
        
        return towerFactory;
    }

}
