package gameEngine.model;

import gameEngine.factory.TowerFactory;
import java.util.HashMap;
import java.util.Map;

public class TowerWarehouse {

    Map<String, TowerFactory> towers;
    
    public TowerWarehouse(){
        towers = new HashMap<String,TowerFactory>();
    }
    
}
