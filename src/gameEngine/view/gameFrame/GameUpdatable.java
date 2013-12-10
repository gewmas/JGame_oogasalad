package gameEngine.view.gameFrame;

/**
 * @author Lalita Maraj
 *The flow of the game is controlled by the JGame object.
 * Some objects need to be continously updated as the game progresses.
 * Rather than pass the entire object to the game to tell the respective objects that they needed
 * to be updated, the GameUpdatable interface only exposes a few methods that 
 * should be called throughout the course of the game
 */
public interface GameUpdatable {

    /**
     * Method that signals to the object implementing GameUpdatable interface that it needs 
     * to update itself
     */
    public void update ();
    /**
     * Method that signals to object it needs to execute the necessary actions associated wtih
     * ending a game
     */
    public void endGame();
}
