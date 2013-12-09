package gameEngine.view.gameFrame;

/**
 * @author Lalita Maraj
 *JGame is the central location for listening to key presses.
 *KeyActivatioItem is an interface that objects can use and
 *be passed to the JGame class so that whenever the key press is made, the
 *JGame can activate the object
 */
public interface KeyActivationItem {
    
    /**
     * The behavior that should occur once the key press is made
     */
    public void activate ();
}
