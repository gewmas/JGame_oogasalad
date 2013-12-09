package gameEngine.view.gameFrame;

import gameEngine.view.gameFrame.inputFrame.InputFrame;


public class CheatFrame implements KeyActivationItem {
    private final String ACTIVATION_KEY = "C";
    private InputFrame inputFrame;

    public CheatFrame (InputFrame inputFrame) {
        this.inputFrame = inputFrame;
    }

    @Override
    public void activate () {
        inputFrame.display();

    }

    @Override
    public String getActivationKey () {
        // TODO Auto-generated method stub
        return ACTIVATION_KEY;
    }

}
