package gameEngine.view.gameFrame;

import gameEngine.view.gameFrame.inputFrame.InputFrame;

/**
 * Allows the user to enter cheats
 * @author lalita maraj
 *
 */
public class CheatFrame implements KeyActivationItem {
    private InputFrame inputFrame;

    public CheatFrame (InputFrame inputFrame) {
        this.inputFrame = inputFrame;
    }

    @Override
    public void activate () {
        inputFrame.display();

    }

    @Override
    public ActivationKey getActivationKey () {
        
        return ActivationKey.CHEAT;
    }

}
