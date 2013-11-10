package gameEngine.view;

import gameEngine.model.TowerInfo;
import java.util.HashMap;
import java.util.Map;


/**
 * Used to implement the Mediator design pattern. Facilitates
 * communication between view components.
 * The components that interact with the mediator implement
 * the Colleage interface.
 * For each method defined in the mediator, the colleague
 * interface has a corresponding method and those colleagues that
 * are impacted by the actions described in the methods should implement
 * their own behavior for said methods.
 * 
 * When a method is called on the mediator, the mediator calls
 * the corresponding method on the colleagues that are impacted.
 * 
 * 
 * @author Lalita Maraj
 * 
 */
public class Mediator {

    private Map<String, Colleague> colleagues;

    public Mediator () {
        colleagues = new HashMap<String, Colleague>();

    }

    /**
     * a map is used to store all the colleagues the mediator liasons
     * with. Key strings are stored in the MediatorConstants class and
     * shuld be used to both add and get colleagues.
     * 
     * @param name key of colleague
     * @param colleague Colleague to be added
     */
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
        colleagues.get(ColleagueKeys.GAME.toString()).placeTower(towerInfo);
        colleagues.get(ColleagueKeys.GAMEFRAME.toString()).placeTower(towerInfo);
    }

    /**
     * Used by display information about a tower
     * on the Tower Info Panel
     * 
     * @param tower
     */
    public void displayTowerInfo (TowerInfo tower) {
        colleagues.get(ColleagueKeys.INFOPANEL.toString()).displayTowerInfo(tower);

    }

    /**
     * Executes the actions on the view components
     * that are impacted by the purchase of a tower
     */
    public void purchaseTower () {
        colleagues.get(ColleagueKeys.GAMEFRAME.toString()).purchaseTower();
    }

    /**
     * Updates the enabled status of store items.
     */
    public void updateStoreStatus () {
        colleagues.get(ColleagueKeys.STOREOPTIONS.toString()).updateStoreStatus();
    }

}
