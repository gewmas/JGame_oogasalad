package gameEngine.view;

import gameEngine.controller.Controller;

public class EngineView {
    private Frame gameFrame;
    private Controller controller;
   
    
    public EngineView(Controller controller){
        this.controller = controller;
    }
}
