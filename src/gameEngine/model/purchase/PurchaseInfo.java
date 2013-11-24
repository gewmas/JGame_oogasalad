package gameEngine.model.purchase;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Jiaran, Yuhua, Harris all tower and tower factory
 * implements TowerInfo so that front end people can 
 * get tower info properly without exposing tower and 
 * towerfactory.
 *
 */
public abstract class PurchaseInfo {
    private String name, image, type;
    private String description;
    private Integer cost;
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
        addToMap("Type", type);
        addToMap("Name", name);
        addToMap("Description", description);
        addToMap("Cost", cost.toString());
        addToMap("Image", image);
    }
    
    public void addToMap(String key, String value) {
        info.put(key, value);
    }
    
    public Map<String, String> getInfo() {
        return info;
    }
    
}
