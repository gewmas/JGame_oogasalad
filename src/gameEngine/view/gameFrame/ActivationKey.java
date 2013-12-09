package gameEngine.view.gameFrame;

/**
 * @author Lalita Maraj
 *Enum class to formally define what key presses 
 *the game listens for
 */
public enum ActivationKey {
    CHEAT('C'),
    FRAME('F');

    private char activationKey;

    ActivationKey (char activationKey) {
        this.activationKey = activationKey;
    }

    public char getKeyChar () {
        return activationKey;
    }

}
