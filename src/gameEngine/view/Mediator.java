package gameEngine.view;

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
    public void purchaseTower (String towername) {
        colleagues.get(MediatorConstants.GAME_KEY).purchaseTower(towername);
    }

    public void displayTowerInfo (String displayInfo) {
        colleagues.get(MediatorConstants.INFO_PANEL_KEY).displayInfo(displayInfo);

    }

}
