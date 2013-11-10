package gameEngine.view;

import gameEngine.model.TowerInfo;


/**
 * Interface that can be implemented by components of the view.
 * Colleages work with a mediator to impleent the Mediator
 * design pattern.
 * Whenever a colleage needs to communicate with
 * another component in the view, it does so via the mediator.
 * The methods below are called by the mediator on colleagues.
 * If a colleague is impacted by the actions described in the following method
 * names, the colleage should implement behavior for that method.
 * 
 * @author Lalita Maraj
 * 
 */
public interface Colleague {

    /**
     * Allows a colleage to add a mediator that they can use
     * to communicate with view components
     * 
     * @param mediator facilitates communication between view components
     */
    
    /**
     * Behavior a colleague should execute after a tower has been placed
     * 
     * @param towerInfo datastructure used to store tower information
     */
    public void placeTower (TowerInfo towerInfo);

    /**
     * Behavior a colleague should execute after a tower has been purchased
     */
    public void purchaseTower ();

    /**
     * Used primarily by the TowerInfo panel to display tower information
     * 
     * @param towerInfo datastructure used to store tower information
     */
    public void displayTowerInfo (TowerInfo towerInfo);

    /**
     * Used primarily by the TowerStorePanel to update the status of 
     * the TowerStoreButton.
     * If the user has enough money to purchase the tower, the button will
     * be enabled. 
     * Otherwise, the button will be disabled.
     */
    public void updateStoreStatus ();

}
