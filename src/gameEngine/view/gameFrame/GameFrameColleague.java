package gameEngine.view.gameFrame;

import gameEngine.factory.towerfactory.TowerFactory;


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
public interface GameFrameColleague {

    /**
     * Allows a colleage to add a mediator that they can use
     * to communicate with view components
     * 
     * @param mediator facilitates communication between view components
     */


}
