package gameEngine.cheats;

import gameEngine.model.Model;
import gameEngine.model.enemy.Enemy;

public class CheatParser {
    
    private Model model;
    
    public CheatParser(Model model) {
        this.model = model;
    }
    
    public Cheat parse(String cheat) {
        String[] cheatArgs = cheat.split(" ");
        String code = cheatArgs[0];
        
        if(code.equals("add_gold")) {
            return new AddGoldCheat(cheat, model);
        } else if(code.equals("add_lives")) {
            return new AddLivesCheat(cheat, model);
        } else if(code.equals("kill_all")) {
            return new ClearAllCheat(cheat, model);
        } else if(code.equals("win_game")) {
            return new WinGameCheat(cheat, model);
        } else if (code.equals("lose_game")) {
            return new LoseGameCheat(cheat, model);
        }
        return null;
    }

}
