package gameEngine.model.purchase;

import gameEngine.constant.GameEngineConstant;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jiaran, Yuhua, Harris all tower and tower factory
 * implements TowerInfo so that front end people can 
 * get tower info properly without exposing tower and 
 * towerfactory.
 *
 */
public class PurchaseInfo {
    private String name, image, type;
    private String description;
    private int cost,x,y;
    private Map<String, String> info;
    
    public PurchaseInfo(String type, String name, String image, String description, int cost) {
        this.type = type;
        this.name = name;
        this.image = image;
        this.description = description;
        this.cost = cost;
        info = new LinkedHashMap<String, String>();
        createMap();
    }
    
    public void createMap() {
        addToMap(GameEngineConstant.PURCHASE_INFO_TYPE, type);
        addToMap(GameEngineConstant.PURCHASE_INFO_NAME, name);
        addToMap(GameEngineConstant.PURCHASE_INFO_DESCRIPTION, description);
        addToMap(GameEngineConstant.PURCHASE_INFO_COST, ((Integer)cost).toString());
        addToMap(GameEngineConstant.PURCHASE_INFO_IMAGE, image);
        addToMap("X",((Integer)x).toString());
        addToMap("Y",((Integer)y).toString());
    }
    
    public void addToMap(String key, String value) {
        info.put(key, value);
    }
    
    public Map<String, String> getInfo() {
        return info;
    }
    
}
