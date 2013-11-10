package gameEngine.view;

import gameEngine.controller.Controller;
import gameEngine.view.initialization.InitializationFrame;


public class EngineView {
    private View gameFrame;
    private Frame initializationFrame;
    private Controller controller;

    public EngineView (Controller controller) {
        this.controller = controller;
        gameFrame = new View(controller, this);
        initializationFrame = new InitializationFrame(controller, this);

    }

    public void showGame () {
        gameFrame.showGame();
    }

    public void selectNewGame () {
        gameFrame.dispose();
        initializationFrame = new InitializationFrame(controller, this);
    }

}
