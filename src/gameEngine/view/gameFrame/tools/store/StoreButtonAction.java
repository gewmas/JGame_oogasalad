package gameEngine.view.gameFrame.tools.store;

/**
 * @author lalitamaraj
 *         Used to define actions for the various button actions.
 *         Reduces the number of parameters that need to be passed to StoreItemButton because
 *         all the behavior can be defined outside of the Button in the executeAction method
 *         and then passted to the StoreItemButton
 */
public interface StoreButtonAction {

    public void executeAction ();

}
