package gameEngine.view.gameFrame.tools.store;

/**
 * @author Lalita Maraj
 *         Used to define actions for the various button actions.
 *         Reduces the number of parameters that need to be passed to StoreItemButton because
 *         all the behavior can be defined outside of the Button in the executeAction method
 *         and then passted to the StoreItemButton.
 *         Allows us to implement the Strategy Design Pattern
 */
public interface StoreButtonAction {

    public void executeAction ();

}
