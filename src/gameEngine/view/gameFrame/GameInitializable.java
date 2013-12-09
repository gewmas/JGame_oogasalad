package gameEngine.view.gameFrame;

/**
 * @author Lalita Maraj
 *The flow of the game is controlled by the JGame object.
 *Therefore, control needs to be handed to the JGame to initialize certain (Swing) objects
 *The JGame instance calls the initialize method on the GameInitializable objects 
 *when the game starts. 
 */
public interface GameInitializable {
    /**
     * Method called on an object implementing the GameInitializable  interface to signal
     * to it that it should initialize its contnets since the game has started
     */
    public void initialize ();
}
