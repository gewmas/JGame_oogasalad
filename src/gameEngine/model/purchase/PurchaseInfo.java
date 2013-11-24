package gameEngine.model.purchase;

import java.util.Map;

/**
 * @author Jiaran and Yuhua all tower and tower factory
 * implements TowerInfo so that front end people can 
 * get tower info properly without exposing tower and 
 * towerfactory.
 *
 */
public interface PurchaseInfo {
    public Map<String, String> getInfo();
    public String getObjectType();
    public String getName();
}
