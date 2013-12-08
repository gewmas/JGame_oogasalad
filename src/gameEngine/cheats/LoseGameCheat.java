package gameEngine.cheats;

import gameEngine.model.Model;

public class LoseGameCheat extends Cheat {

    public LoseGameCheat (String command, Model model) {
        super(command, model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean execute() {
        model.getGameInfo().setLife(0);
        return true;
    }

}
