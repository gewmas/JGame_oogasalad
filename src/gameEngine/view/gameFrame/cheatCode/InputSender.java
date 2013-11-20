package gameEngine.view.gameFrame.cheatCode;

import gameEngine.view.View;

public class InputSender {
    private View view;

    public InputSender(View view){
        this.view = view;
        
    }
    
    public boolean execute(String cheat){
  
        return view.activateCheat(cheat);
    }
}
