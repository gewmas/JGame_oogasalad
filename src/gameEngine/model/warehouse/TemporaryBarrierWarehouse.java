package gameEngine.model.warehouse;

import gameEngine.factory.enemyfactory.EnemyFactory;
import gameEngine.factory.enemyfactory.NormalEnemyFactory;
import gameEngine.factory.temporaryBarrier.DefaultTemporaryBarrierFactory;
import gameEngine.factory.temporaryBarrier.TemporaryBarrierFactory;
import gameEngine.factory.towerfactory.TowerFactory;
import gameEngine.model.GameInfo;
import gameEngine.model.Model;
import gameEngine.parser.Parser;
import gameEngine.parser.JSONLibrary.JSONArray;
import gameEngine.parser.JSONLibrary.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * Warehouse for temporary barriers
 * @author: Harris Osserman
 * 
 */

public class TemporaryBarrierWarehouse extends Warehouse{
    private JSONArray jsonArray;
    private Map<String, TemporaryBarrierFactory> temporaryBarrierFactories;

    public TemporaryBarrierWarehouse (Parser parser) {
        this.jsonArray = parser.getJSONArray("temporaryBarrierType");
        temporaryBarrierFactories = new HashMap<String, TemporaryBarrierFactory>();

        //loop through all kinds of temporary barriers
        for(int k=0; k<jsonArray.length(); k++) {
            JSONObject obj = jsonArray.getJSONObject(k);
            TemporaryBarrierFactory tbf  = new DefaultTemporaryBarrierFactory(obj.getString("id"), obj.getString("image"), obj.getInt("damage"),
                                                                        obj.getInt("cost"), obj.getInt("expire"), obj.getString("description"));
            temporaryBarrierFactories.put(obj.getString("id"), tbf);
        }
    }
    
    public boolean create(int x, int y, String name,GameInfo g){
        TemporaryBarrierFactory tbf = temporaryBarrierFactories.get(name);
        
        if(Integer.valueOf(tbf.getInfo().get("Cost")) <= g.getGold()){
            g.loseGold(Integer.parseInt(tbf.getInfo().get("Cost")));
            tbf.create(x, y);
            return true;
        }
        return false;
    }

    public List<TemporaryBarrierFactory> getTemporaryBarrierFactory () {
        List<TemporaryBarrierFactory> tbf = new ArrayList<TemporaryBarrierFactory>();

        for (Entry<String, TemporaryBarrierFactory> entry : temporaryBarrierFactories.entrySet()) {
            tbf.add(entry.getValue());
        }
        return tbf;
    }
    
    
    
}
