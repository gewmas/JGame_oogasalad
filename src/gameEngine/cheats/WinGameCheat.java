package gameEngine.cheats;

import gameEngine.model.Model;

public class WinGameCheat extends Cheat {

    public WinGameCheat (String command, Model model) {
        super(command, model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean execute() {
        model.getGameInfo().SetIsWin(true);
        model.setCheatToWin(true);
        return true;
    }

}
