package gameEngine.cheats;

import gameEngine.model.Model;

public class AddGoldCheat extends Cheat {

    public AddGoldCheat (String command, Model model) {
        super(command, model);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public boolean execute() {
        int amt = params.get(0);
        System.out.println("Adding gold: "+amt);
        model.getGameInfo().addGold(amt);
        return true;
    }
    

    
}
