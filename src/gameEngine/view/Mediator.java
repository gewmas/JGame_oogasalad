package gameEngine.view;

import gameEngine.model.TowerInfo;
import java.util.HashMap;
import java.util.Map;


public class Mediator {

    private Map<String, Colleague> colleagues;

    public Mediator () {
        colleagues = new HashMap<String, Colleague>();

    }

    public void addColleague (String name, Colleague colleague) {
        colleagues.put(name, colleague);
    }

    /**
     * Notifies all colleagues that need to be updated
     * when a user is trying to purchase a tower
     * 
     * @param towername
     */
    public void placeTower (TowerInfo towerInfo) {
        colleagues.get(MediatorConstants.GAME_KEY).placeTower(towerInfo);
        colleagues.get("view").placeTower(towerInfo);
    }

    public void displayTowerInfo (TowerInfo tower) {
        colleagues.get(MediatorConstants.INFO_PANEL_KEY).displayTowerInfo(tower);

    }
    
    public void purchaseTower(){
        colleagues.get("view").purchaseTower();
    }
    
    

}
