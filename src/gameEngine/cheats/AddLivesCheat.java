package gameEngine.cheats;

import gameEngine.model.Model;

public class AddLivesCheat extends Cheat {

    public AddLivesCheat (String command, Model model) {
        super(command, model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean execute() {
        int amt = params.get(0);
        model.getGameInfo().addLife(amt);
        return true;
    }
    

    
}
