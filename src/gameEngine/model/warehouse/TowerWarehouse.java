package gameEngine.model.warehouse;

import gameEngine.Constant.Constant;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.GameInfo;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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

public class TowerWarehouse extends Warehouse {

    private JSONArray jsonArray;
    Map<String, TowerFactory> towers;

    public TowerWarehouse (Parser parser) throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException {
        jsonArray = parser.getJSONArray(Constant.TOWER_TYPE);
        towers = new HashMap<String, TowerFactory>();

        // loop through all kinds
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currTower = jsonArray.getJSONObject(i);
            String name = currTower.getString(Constant.PURCHASE_INFO_NAME);
            String type = currTower.getString(Constant.PURCHASE_INFO_TYPE);
            TowerFactory towerFactory = null;
            
            /**
             * Now it create DefaultTowerFactory no matter what
             * Later should create different TowerFactory according to the Json File
             * 
             * reflection - http://stackoverflow.com/questions/6310730/using-reflection-java-reflection-constructor-newinstance
             */
            Class<?> fooClass = null;
            try {
                fooClass = Class.forName("gameEngine.factory.towerfactory."+type+"Factory");
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            Constructor<?>[] fooCtrs = fooClass.getConstructors();
            Class<?>[] types = fooCtrs[0].getParameterTypes();
            Object[] params = new Object[types.length];
            params[0] = types[0].cast(currTower);
            towerFactory = (TowerFactory)fooCtrs[0].newInstance(params);
         
            
            towers.put(name, towerFactory);
        }
    }

//    public void create (String name) {
//        TowerFactory towerFactory = towers.get(name);
//        towerFactory.create();
//    }

    // Jiaran edit, something's wrong please contact.
    //wenxin I cast it to int
    public boolean create(int x, int y, String name,GameInfo g){
        TowerFactory towerFactory = towers.get(name);
        if(towerFactory.getCost()<=g.getGold()){
            g.loseGold((int) towerFactory.getCost());
            towerFactory.create(x, y);
            return true;
        }
        else return false;
    }

    public List<TowerFactory> getTowerFactory () {
        List<TowerFactory> towerFactory = new ArrayList<TowerFactory>();

        for (Entry<String, TowerFactory> entry : towers.entrySet()) {
            towerFactory.add(entry.getValue());
        }

        return towerFactory;
    }

}
